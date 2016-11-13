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

    public final static String CLE_JSON_PLAYLIST_LISTE_CHEMINS_MEDIA = "CheminsMedia";
    public final static String CLE_JSON_PLAYLIST_LISTE_NOMS_MEDIA = "NomsMedia";

    FileOpener fileOpener;

    /**
     * Constructeur par défaut qui ne prend aucun paramètre
     */
    public PlaylistIO() {
        fileOpener = new FileOpener();
    }

    /**
     * Ouvre une fenêtre de sauvegarde de Playlist où l'utilisateur peut choisir de sauvegarder une playlist.
     * Le format de la sauvegarde est JSON, peu importe l'extension.
     *
     * @param context  le contexte (la fenêtre) dans lequel le FileChooser sera affiché.
     * @param playlist la liste de lecture à sauvegarder.
     */
    public void sauvegarderPlaylist(Stage context, Playlist playlist) {
        try {
            File file = fileOpener.afficherFenetreSauvegardePlaylist(context);

            if (file != null) {

                JSONObject obj = new JSONObject();
                JSONArray listeChemins = new JSONArray();
                JSONArray listeNomsMedia = new JSONArray();
                ArrayList<Media> listeMediaPlaylist = playlist.getListeMedia();
                for (Media media : listeMediaPlaylist) {
                    listeNomsMedia.add(media.getTitre());
                    listeChemins.add(media.getPathMedia());
                }

                obj.put(CLE_JSON_PLAYLIST_LISTE_NOMS_MEDIA, listeNomsMedia);
                obj.put(CLE_JSON_PLAYLIST_LISTE_CHEMINS_MEDIA, listeChemins);

                FileWriter fw = new FileWriter(file.getAbsolutePath());
                fw.write(obj.toJSONString());
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ouvre une fenêtre d'ouverture de playlist dans laquelle l'utilisateur peut choisir d'ouvrir une playlist.
     * La méthode lit le fichier choisi par l'utilisateur (si celui-ci en a choisi un) et crée un objet Playlist avec
     * l'information lue dedans. La méthode retourne l'objet Playlist crée.
     *
     * @param context le contexte (la fenêtre) dans lequel le FileChooser sera affiché.
     * @return l'objet Playlist crée. Null si l'utilisateur ne choisit pas de fichier
     */
    public Playlist ouvrirPlaylist(Stage context) {

        FileOpener fo = new FileOpener();
        File file = fo.activerOuverturePlaylist(context);

        Playlist playlist = null;
        if (file != null) {
            ArrayList<String> listePathsMedia = new ArrayList<String>();
            ArrayList<String> listeNomsMedia = new ArrayList<String>();
            ArrayList<Media> listeMedia = new ArrayList<Media>();

            JSONParser jsonParser = new JSONParser();

            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file.getAbsolutePath()));


                JSONArray jsonArrayPathsMedia = (JSONArray) jsonObject.get(CLE_JSON_PLAYLIST_LISTE_CHEMINS_MEDIA);
                String pathMediaTmp;
                // Récupère les chemins absolus des fichiers
                for (int i = 0; i < jsonArrayPathsMedia.size(); i++) {
                    pathMediaTmp = (String) jsonArrayPathsMedia.get(i);
                    listePathsMedia.add(pathMediaTmp);
                }

                JSONArray jsonArrayNomMedia = (JSONArray) jsonObject.get(CLE_JSON_PLAYLIST_LISTE_NOMS_MEDIA);
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
