package ca.qc.bdeb.maveo.modele.playlist;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 1379708 on 2016-10-11.
 */
public class PlaylistIO {

    FileOpener fileOpener;

    /**
     * Constructeur par défaut qui ne prend aucun paramètre
     */
    public PlaylistIO() {
        fileOpener = new FileOpener();
    }

    /**
     * Ouvre une fenêtre de sauvegarde de Playlist
     */
    public void sauvegarderPlaylist(Stage context, Playlist playlist) {

        JSONObject obj = new JSONObject();
        JSONArray listeChemins = new JSONArray();
        JSONArray listeNomsMedia = new JSONArray();
        ArrayList<Media> listMed = playlist.getListeMedia();
        for (Media media : listMed) {
            listeNomsMedia.add(media.getTitre());
            listeChemins.add(media.getPathMedia());
        }

        obj.put("Media", listeNomsMedia);
        obj.put("Liste", listeChemins);

        try {
            File file = fileOpener.afficherFenetreSauvegardePlaylist(context);

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

    /**
     * Affiche une fenêtre d'ouverture de playlist
     */
    public Playlist ouvrirPlaylist(Stage context) {
        ArrayList<String> listePathsMedia = new ArrayList<String>();
        ArrayList<String> listeNomsMedia = new ArrayList<String>();
        ArrayList<Media> listeMedia = new ArrayList<Media>();

        FileOpener fo = new FileOpener();
        File file = fo.activerOuverturePlaylist(context);

        Playlist playlist = null;
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

                playlist = new Playlist(file.getName());

                playlist.getListeMedia().addAll(listeMedia);


            } catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playlist;
    }

}
