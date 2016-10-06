package ca.qc.bdeb.maveo.modele.fichier;

import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by C A T A on 2016-10-04.
 */

public class AccesExtensions {

    public final String NOM_RESSOURCE_FICHIER_EXTENSIONS = "ExtensionsMaveo.json";
    public final String NOM_JSON_EXTENSIONS_MEDIA = "ExtensionsOuvertureMedia";
    public final String NOM_JSON_EXTENSIONS_PLAYLIST = "ExtensionsPlaylist";
    public final String DESCRIPTION = "Description";
    public final String EXTENSIONS = "Extensions";

    public final int INDICE_LISTE_FILTRES_MEDIA_TOUS_FICHIERS_MEDIA = 0;
    public final int INDICE_LISTE_FILTRES_MEDIA_FICHIERS_AUDIO = 1;
    public final int INDICE_LISTE_FILTRES_MEDIA_FICHIERS_VIDEO = 2;

    public final int INDICE_LISTE_FILTRES_PLAYLIST_FICHIERS_MAVEO = 0;
    public final int INDICE_LISTE_FILTRES_PLAYLIST_FICHIERS_JSON = 1;

    URL urlFichierExtensions;

    ArrayList<FileChooser.ExtensionFilter> listeFiltresMedia;
    ArrayList<FileChooser.ExtensionFilter> listeFiltresPlaylist;


    public AccesExtensions() {
        this.urlFichierExtensions = getClass().getClassLoader().getResource(NOM_RESSOURCE_FICHIER_EXTENSIONS);
        ouvrirFichierExtensions();
    }

    /**
     * Lit le fichier d'extensions audio et vidéo
     */
    void ouvrirFichierExtensions() {
        JSONParser jsonParser = new JSONParser();
        listeFiltresMedia = new ArrayList<FileChooser.ExtensionFilter>();
        listeFiltresPlaylist = new ArrayList<FileChooser.ExtensionFilter>();

        try {
            File file = new File(urlFichierExtensions.toURI());

            JSONObject jsonObject =
                    (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));

            JSONArray jsonArrayExtensionsMedia = (JSONArray) jsonObject.get(NOM_JSON_EXTENSIONS_MEDIA);

            JSONObject jsonObjectExtensionsMedia;
            String description;
            JSONArray tabExtensionsMedia;
            FileChooser.ExtensionFilter extensionFilterTmp;
            for (int i = 0; i < jsonArrayExtensionsMedia.size(); i++) {
                jsonObjectExtensionsMedia = (JSONObject) jsonArrayExtensionsMedia.get(i);
                description = (String) jsonObjectExtensionsMedia.get(DESCRIPTION);
                tabExtensionsMedia = (JSONArray) jsonObjectExtensionsMedia.get(EXTENSIONS);
                extensionFilterTmp =
                        new FileChooser.ExtensionFilter(description, tabExtensionsMedia);
                listeFiltresMedia.add(extensionFilterTmp);
            }



            JSONArray jsonArrayExtensionsPlaylist = (JSONArray) jsonObject.get(NOM_JSON_EXTENSIONS_PLAYLIST);

            JSONObject jsonObjectExtensionsPlaylist;
            JSONArray tabExtensionsPlaylist;
            for (int i = 0; i < jsonArrayExtensionsPlaylist.size(); i++) {
                jsonObjectExtensionsPlaylist = (JSONObject) jsonArrayExtensionsPlaylist.get(i);
                description = (String) jsonObjectExtensionsPlaylist.get(DESCRIPTION);
                tabExtensionsPlaylist = (JSONArray) jsonObjectExtensionsPlaylist.get(EXTENSIONS);
                extensionFilterTmp = new FileChooser.ExtensionFilter(description, tabExtensionsPlaylist);
                listeFiltresPlaylist.add(extensionFilterTmp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FileChooser.ExtensionFilter> getListeFiltresMedia() {
        return listeFiltresMedia;
    }

    public ArrayList<FileChooser.ExtensionFilter> getListeFiltresPlaylist() {
        return listeFiltresPlaylist;
    }

    public URL getUrlFichierExtensions() {
        return urlFichierExtensions;
    }

    public void setUrlFichierExtensions(URL urlFichierExtensions) {
        this.urlFichierExtensions = urlFichierExtensions;
    }
}