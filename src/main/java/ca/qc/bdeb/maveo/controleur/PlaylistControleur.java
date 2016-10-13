package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.modele.playlist.Playlist;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by WuTchanKi on 2016-10-11.
 */
public class PlaylistControleur {
    MainFrame mainframe;
    LecteurMediaControleur controleurLecteurMedia;
    Playlist playList;
    ObservableList<String> listTitle;

    //Ces deux variables sont temporaires.
    private final String PLAYLIST_NAME = "PlayList";
    private final int PLAYLIST_ID = 0;

    FileOpener fileOpener;

    public PlaylistControleur() {
        listTitle = FXCollections.observableList(new ArrayList<String>());
    }

    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainframe = mainFrame;
        this.mainframe.addEventHandlerCreatePlaylist(new MenuCreatePlaylistEventHandler());
        this.mainframe.addEventHandlerOpenPlaylist(new OpenPlaylistEventHandler());
        this.mainframe.addEventHandlerAddMediaInPlayList(new MenuAddToPlaylistEventHandler());
        this.mainframe.addEventHandlerSavePlaylist(new MenuSavePlaylistEventHandler());
        this.mainframe.addEventHandlerPlayListSelected(new PlayListItemSelectedEventHandler());
        mainframe.getListPlayList().setItems(listTitle);
        fileOpener = new FileOpener();
    }

    public void ajouterControleurLecteurMedia(LecteurMediaControleur controleurLecteurMedia) {
        this.controleurLecteurMedia = controleurLecteurMedia;
    }

    class MenuSavePlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            if (playList == null) {
                playList = new Playlist("test", 2);
            } else {
                playList.setIdPlaylist(3);
            }

            JSONObject obj = new JSONObject();
            obj.put("ID", playList.getIdPlaylist());
            obj.put("LENGTH", playList.recupererLongueurListe());
            JSONArray list = new JSONArray();
            JSONArray listeMedia = new JSONArray();
            ArrayList<Media> listMed = playList.getListeMedia();
            for (Media media : listMed) {
                listeMedia.add(media.getTitre());
                list.add(media.getPathMedia());
            }
            // list.add(playList.getListeMedia());
            obj.put("Media", listeMedia);
            obj.put("Liste", list);


            try {
                File file = fileOpener.afficherFenetreSauvegardePlaylist(mainframe.getFenetre());

                if (file != null) {
                    PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
                    FileWriter fw;
                    fw = new FileWriter(file.getAbsolutePath());
                    fw.write(obj.toJSONString());
                    fw.flush();
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    Media getMediaFromFile(Stage stage) {
        File file = fileOpener.activerOuvertureMedia(stage);
        Media media = null;
        if (file != null) {
            media = new Media(file.getName(), file.getAbsolutePath());
        }
        return media;
    }

    /**
     * Déclencheur qui s'active lorsqu'un utilisateur appuie sur le menu de la création d'une playlist.
     */
    class MenuCreatePlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            playList = new Playlist(PLAYLIST_NAME, PLAYLIST_ID);
        }
    }

    class MenuAddToPlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (playList == null) {
                playList = new Playlist("TestAdd", 5);
            }

            Media media = getMediaFromFile((Stage) mainframe.getFenetre());
            playList.ajouterMediaListe(media);
            listTitle.add(media.getTitre());
        }
    }

    class OpenPlaylistEventHandler implements EventHandler<ActionEvent> {

        ArrayList<String> listePathsMedia = new ArrayList<String>();
        ArrayList<String> listeNomsMedia = new ArrayList<String>();
        ArrayList<Media> listeMedia = new ArrayList<Media>();

        @Override
        public void handle(ActionEvent event) {
            FileOpener fo = new FileOpener();
            fo.activerFiltresPlaylist();
            File file = fo.activerOuverturePlaylist(mainframe.getFenetre());
            if (file != null) {
                JSONParser jsonParser = new JSONParser();

                try {
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file.getAbsolutePath()));

                    String nomJsonMedia = "Media";
                    String pathJsonMedia = "Liste";
                    String idJsonMedia = "ID";

                    long idPlaylist = (Long) jsonObject.get(idJsonMedia);

                    JSONArray jsonArrayPathsMedia = (JSONArray) jsonObject.get(pathJsonMedia);
                    String pathMediaTmp;
                    // Récupère les chemins absolus des fichiers
                    for (int i = 0; i < jsonArrayPathsMedia.size(); i++) {
                        pathMediaTmp = (String) jsonArrayPathsMedia.get(i);
                        listePathsMedia.add(pathMediaTmp);
                    }

                    JSONArray jsonArrayNomMedia = (JSONArray) jsonObject.get(nomJsonMedia);
                    String nomMediaTmp;
                    // Récupère les noms des chansons
                    for (int i = 0; i < jsonArrayNomMedia.size(); i++) {
                        nomMediaTmp = (String) jsonArrayNomMedia.get(i);
                        listeNomsMedia.add(nomMediaTmp);
                    }


                    Iterator<String> itChemins = listePathsMedia.iterator();
                    Iterator<String> itNomsFichiers = listeNomsMedia.iterator();
                    // Crée la liste de média à partir des lsites des chemins et noms
                    while (itChemins.hasNext() && itNomsFichiers.hasNext()) {
                        listeMedia.add(new Media(itNomsFichiers.next(), itChemins.next()));
                    }

                    Playlist playlist = new Playlist(file.getName(), (int) idPlaylist);

                    playlist.getListeMedia().addAll(listeMedia);

                    listTitle.clear();

                    for (Media media : listeMedia) {
                        listTitle.add(media.getTitre());
                    }

                    playList = playlist;

                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Évènement lorsqu'on clique sur un item dans la playlist.
     */
    class PlayListItemSelectedEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
            if (gestionnaireMedia != null && gestionnaireMedia.enLecture()) {
                gestionnaireMedia.arreter();
            }
            String mediaName = mainframe.getListPlayList().getSelectionModel().getSelectedItem();
            Media media = playList.getMediaByName(mediaName);
            gestionnaireMedia = GestionnaireFactory.createInstance(media, mainframe);
            gestionnaireMedia.preparerMedia();
            gestionnaireMedia.addMediaPlayerEventListener(controleurLecteurMedia);
            mainframe.getBtnJouerPause().setDisable(false);
            mainframe.getSliderProgression().setDisable(false);
        }
    }
}
