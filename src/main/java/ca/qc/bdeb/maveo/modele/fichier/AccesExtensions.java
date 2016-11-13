package ca.qc.bdeb.maveo.modele.fichier;

import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by C A T A on 2016-10-04.
 */

public class AccesExtensions {

    public final String NOM_RESSOURCE_FICHIER_EXTENSIONS = "ExtensionsMaveo.json";
    public final String CLE_JSON_EXTENSIONS_MEDIA = "ExtensionsOuvertureMedia";
    public final String CLE_JSON_EXTENSIONS_PLAYLIST = "ExtensionsPlaylist";
    public final String CLE_JSON_EXTENSIONS_PAROLES = "ExtensionsParoles";
    public final String CLE_DESCRIPTION = "Description";
    public final String CLE_EXTENSIONS = "Extensions";

    public final int INDICE_LISTE_FILTRES_MEDIA_TOUS_FICHIERS_MEDIA = 0;
    public final int INDICE_LISTE_FILTRES_MEDIA_FICHIERS_AUDIO = 1;
    public final int INDICE_LISTE_FILTRES_MEDIA_FICHIERS_VIDEO = 2;

    public final int INDICE_LISTE_FILTRES_PLAYLIST_FICHIERS_MAVEO = 0;
    public final int INDICE_LISTE_FILTRES_PLAYLIST_FICHIERS_JSON = 1;

    InputStream streamFichierExtension;

    ArrayList<FileChooser.ExtensionFilter> listeFiltresMedia;
    ArrayList<FileChooser.ExtensionFilter> listeFiltresPlaylist;
    ArrayList<FileChooser.ExtensionFilter> listeFiltresParoles;


    public AccesExtensions() {
        this.streamFichierExtension = getClass().getClassLoader().getResourceAsStream(NOM_RESSOURCE_FICHIER_EXTENSIONS);
        ouvrirFichierExtensions();
    }

    /**
     * Lit le fichier d'extensions audio et vidéo
     */
    void ouvrirFichierExtensions() {
        JSONParser jsonParser = new JSONParser();
        listeFiltresMedia = new ArrayList<FileChooser.ExtensionFilter>();
        listeFiltresPlaylist = new ArrayList<FileChooser.ExtensionFilter>();
        listeFiltresParoles = new ArrayList<FileChooser.ExtensionFilter>();

        try {
            JSONObject jsonObject =
                    (JSONObject) jsonParser.parse(new InputStreamReader(streamFichierExtension));

            JSONArray jsonArrayExtensionsMedia = (JSONArray) jsonObject.get(CLE_JSON_EXTENSIONS_MEDIA);

            JSONObject jsonObjectExtensionsMedia;
            String description;
            JSONArray tabExtensionsMedia;
            FileChooser.ExtensionFilter extensionFilterTmp;
            for (int i = 0; i < jsonArrayExtensionsMedia.size(); i++) {
                jsonObjectExtensionsMedia = (JSONObject) jsonArrayExtensionsMedia.get(i);
                description = (String) jsonObjectExtensionsMedia.get(CLE_DESCRIPTION);
                tabExtensionsMedia = (JSONArray) jsonObjectExtensionsMedia.get(CLE_EXTENSIONS);
                extensionFilterTmp =
                        new FileChooser.ExtensionFilter(description, tabExtensionsMedia);
                listeFiltresMedia.add(extensionFilterTmp);
            }



            JSONArray jsonArrayExtensionsPlaylist = (JSONArray) jsonObject.get(CLE_JSON_EXTENSIONS_PLAYLIST);

            JSONObject jsonObjectExtensionsPlaylist;
            JSONArray tabExtensionsPlaylist;
            for (int i = 0; i < jsonArrayExtensionsPlaylist.size(); i++) {
                jsonObjectExtensionsPlaylist = (JSONObject) jsonArrayExtensionsPlaylist.get(i);
                description = (String) jsonObjectExtensionsPlaylist.get(CLE_DESCRIPTION);
                tabExtensionsPlaylist = (JSONArray) jsonObjectExtensionsPlaylist.get(CLE_EXTENSIONS);
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
        return null;
    }

    public void setUrlFichierExtensions(URL urlFichierExtensions) {

    }
}