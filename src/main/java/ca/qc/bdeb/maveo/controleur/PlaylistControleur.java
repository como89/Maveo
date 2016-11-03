package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.modele.playlist.Playlist;
import ca.qc.bdeb.maveo.modele.playlist.PlaylistIO;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by WuTchanKi on 2016-10-11.
 */
public class PlaylistControleur {
    MainFrame mainframe;
    LecteurMediaControleur controleurLecteurMedia;
    Playlist playList;
    ObservableList<String> listTitle;
    PlaylistIO playlistIO;

    //Ces deux variables sont temporaires.
    private final String PLAYLIST_NAME = "PlayList";

    private final int TAILLE_MINIMALE_PLAYLIST_ACTIVATION_BOUTONS_SUIVANT_PRECEDENT = 2;

    FileOpener fileOpener;

    public PlaylistControleur() {
        listTitle = FXCollections.observableList(new ArrayList<String>());
    }

    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainframe = mainFrame;
        this.mainframe.addEventHandlerOpenPlaylist(new OpenPlaylistEventHandler());
        this.mainframe.addEventHandlerAddMediaInPlayList(new MenuAddToPlaylistEventHandler());
        this.mainframe.addEventHandlerSavePlaylist(new MenuSavePlaylistEventHandler());
        this.mainframe.addEventHandlerPlayListSelected(new PlayListItemSelectedEventHandler());
        this.mainframe.addEventHandlerBoutonSuivant(new BoutonSuivantEventHandler());
        this.mainframe.addEventHandlerBoutonPrecedent(new BoutonPrecedentEventHandler());
        this.mainframe.addEventHandlerHidePlaylist(new MenuHidePlaylistEventHandler());
        mainframe.getListPlayList().setItems(listTitle);
        fileOpener = new FileOpener();
    }

    public void ajouterControleurLecteurMedia(LecteurMediaControleur controleurLecteurMedia) {
        this.controleurLecteurMedia = controleurLecteurMedia;
    }

    class MenuSavePlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            sauvegarderPlaylist();
        }
    }

    /**
     * Ajoute un PlayliatIO
     *
     * @param plistIo le PlaylistIO à ajouter
     */
    public void ajouterPlaylistIo(PlaylistIO plistIo) {
        this.playlistIO = plistIo;
    }

    /**
     * Ouvre une fenêtre de sauvegarde de Playlist
     */
    void sauvegarderPlaylist() {
        playlistIO.sauvegarderPlaylist((Stage) mainframe.getFenetre(), playList);
    }


    /**
     * Affiche fenêtre ouverture média
     *
     * @param stage le contexte où afficher la fenêtre d'ouverture
     * @return le média ouvert (null si pas de média sélectionné)
     */
    Media getMediaFromFile(Stage stage) {
        File file = fileOpener.activerOuvertureMedia(stage);
        Media media = null;
        if (file != null) {
            media = new Media(file.getName(), file.getAbsolutePath());
        }
        return media;
    }

    public class MenuHidePlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            cacherPlaylist();
        }
    }

    private void cacherPlaylist() {


        if (mainframe.getPlaylistPane().isVisible()) {
            mainframe.getPlaylistPane().setVisible(false);
            mainframe.getMenuItemHidePlaylist().setText("Show Playlist");
        } else {
            mainframe.getPlaylistPane().setVisible(true);
            mainframe.getMenuItemHidePlaylist().setText("Hide Playlist");
        }


    }


    class MenuAddToPlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ajouterAlaPlaylist();
        }
    }

    /**
     * Ouvre une fenêtre d'ouverture de fichier média que l'utilisateur peut utiliser pour ajouter un item à la playlist
     */
    private void ajouterAlaPlaylist() {

        // patron singleton
        if (playList == null) {
            playList = new Playlist(PLAYLIST_NAME);
        }

        // Ajoute le fichier sélectionné par l'utilisateur à la playlist
        Media media = getMediaFromFile((Stage) mainframe.getFenetre());
        if (media != null) {
            playList.ajouterMediaListe(media);
            listTitle.add(media.getTitre());
        }

        // Si la taille de la palylist permet l'utilisation des boutons suivant et précédent, active ces boutons
        if (playList.getLongueurListe() >= TAILLE_MINIMALE_PLAYLIST_ACTIVATION_BOUTONS_SUIVANT_PRECEDENT) {
            mainframe.getBoutonSuivant().setDisable(false);
            mainframe.getBoutonPrecedent().setDisable(false);
        }
    }

    class OpenPlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ouvrirPlaylist();
        }

    }

    /**
     * Affiche une fenêtre d'ouverture de playlist. Remplace la liste des tires avec la nouvelle
     * liste si
     */
    private void ouvrirPlaylist() {
        playList = playlistIO.ouvrirPlaylist((Stage) mainframe.getFenetre());

        if (playList != null) {
            listTitle.clear();
            for (Media media : playList.getListeMedia()) {
                listTitle.add(media.getTitre());
            }
            mainframe.getBoutonPrecedent().setDisable(false);
            mainframe.getBoutonSuivant().setDisable(false);
        }
    }

    /**
     * Les actions à effecctuer lorsqu'un item a été sélectionné dans la playlist:
     * 1. Arrête la lecture de média en cours, si c'est le cas
     * 2. Prépare le nouveau média sélectionné
     * 3. Joue le nouveau média sélectionné
     * 4. Active le bouton Jouer/Pause
     * 5. Active la barre de position
     */
    private void effectuerActionsSelectionItemPlaylist() {
        GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
        if (gestionnaireMedia != null && gestionnaireMedia.enLecture()) {
            gestionnaireMedia.arreter();
        }
        String mediaName = mainframe.getListPlayList().getSelectionModel().getSelectedItem();
        if (playList != null && mediaName != null) {
            Media media = playList.getMediaByName(mediaName);

            gestionnaireMedia = GestionnaireFactory.createInstance(media, mainframe);
            gestionnaireMedia.preparerMedia();
            gestionnaireMedia.addMediaPlayerEventListener(controleurLecteurMedia);
            mainframe.getBtnJouerPause().setDisable(false);
            mainframe.getSliderProgression().setDisable(false);
        }
    }

    /**
     * Évènement qui se déclenche lorsqu'on clique sur un item dans la playlist.
     */
    class PlayListItemSelectedEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            effectuerActionsSelectionItemPlaylist();
        }
    }


    /**
     * Évènement qui se déclenche lorsque on clique sur le bouton suivant:
     * 1. Vérifie où est rendu à la dans la playlist. Si on est rendu à la fin, enregistre qu'on doit sélectionner le
     * premier élément de la liste. Sinon, enregistre qu'on doit sélectionner l'élément suivant.
     * 2. Sélectionne l'élément approprié dans la playlist.
     * 3. Appelle la méthode qui effectue les actions de sélection d'item dans playlist.
     */
    class BoutonSuivantEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            selectionnerIndiceSuivant();

        }
    }

    /**
     * Évènement qui se déclenche lorsque on clique sur le bouton précédent:
     * 1. Vérifie où est rendu à la dans la playlist. Si on est rendu au debut, enregistre qu'on doit sélectionner le
     * dernier élément de la liste. Sinon, enregistre qu'on doit sélectionner l'élément précédent.
     * 2. Sélectionne l'élément approprié dans la playlist.
     * 3. Appelle la méthode qui effectue les actions de sélection d'item dans playlist.
     */
    class BoutonPrecedentEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            selectionnerIndicePrecedent();

        }
    }

    private void selectionnerIndicePrecedent() {
        ListView<String> lvwPlaylist = mainframe.getListPlayList();
        int indiceSelectionne = lvwPlaylist.getSelectionModel().getSelectedIndex();

        // Par défaut, sélectionne une décrementation de l'indice actuel
        int nouveauIndiceASelectionner = indiceSelectionne - 1;

        // Si l'indice de sélection est au début de la playlist, sélectionne le dernier élémnt
        if (indiceSelectionne == 0) {
            nouveauIndiceASelectionner = lvwPlaylist.getItems().size() - 1;
        }
        mainframe.getListPlayList().getSelectionModel().select(nouveauIndiceASelectionner);
        effectuerActionsSelectionItemPlaylist();
    }


    private void selectionnerIndiceSuivant() {
        ListView<String> lvwPlaylist = mainframe.getListPlayList();
        int indiceSelectionne = lvwPlaylist.getSelectionModel().getSelectedIndex();

        // Par défaut, sélectionne une incrémentation de l'indice actuel
        int nouveauIndiceASelectionner = indiceSelectionne + 1;

        // Si l'indice de sélection est arrivé à la fin de la Playlist, sélectionne le premier item
        if (indiceSelectionne >= lvwPlaylist.getItems().size() - 1) {
            nouveauIndiceASelectionner = 0;
        }
        mainframe.getListPlayList().getSelectionModel().select(nouveauIndiceASelectionner);
        effectuerActionsSelectionItemPlaylist();
    }

}
