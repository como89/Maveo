package ca.qc.bdeb.maveo.modele.Fichier;

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
    public final String NOM_TAB_EXTENSIONS_AUDIO = "ExtensionsAudio";
    public final String NOM_TAB_EXTENSIONS_VIDEO = "ExtensionsVideo";
    public final String DESCRIPTION = "Description";
    public final String EXTENSION = "Extension";


    private URL urlFichierExtensions;

    ArrayList<FileChooser.ExtensionFilter> listeFiltresAudio;
    ArrayList<FileChooser.ExtensionFilter> listeFiltresVideo;


    public AccesExtensions() {
        this.urlFichierExtensions = getClass().getClassLoader().getResource("ExtensionsMaveo.json");
        ouvrirFichierExtensions();
    }

    /**
     * Lit le fichier d'extensions audio et vidéo
     */
    void ouvrirFichierExtensions() {
        JSONParser jsonParser = new JSONParser();
        listeFiltresAudio = new ArrayList<FileChooser.ExtensionFilter>();
        listeFiltresVideo = new ArrayList<FileChooser.ExtensionFilter>();

        try {
            File file = new File(urlFichierExtensions.toURI());

            JSONObject jsonObject =
                    (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));

            JSONArray tabExtensionsAudio = (JSONArray) jsonObject.get(NOM_TAB_EXTENSIONS_AUDIO);
            JSONObject ligneJson = new JSONObject();

            // Remplit la liste des extensions audio
            FileChooser.ExtensionFilter extensionFilterTmp;
            for (int i = 0; i < tabExtensionsAudio.size(); i++) {
                ligneJson = (JSONObject) tabExtensionsAudio.get(i);
                extensionFilterTmp =
                        new FileChooser.ExtensionFilter((String) ligneJson.get(DESCRIPTION),
                                (String) ligneJson.get(EXTENSION));
                listeFiltresAudio.add(extensionFilterTmp);
            }

            JSONArray tabExtensionsVideo = (JSONArray) jsonObject.get(NOM_TAB_EXTENSIONS_VIDEO);

            // Remplit la liste des extensions vidéo
            for (int i = 0; i < tabExtensionsVideo.size(); i++) {
                ligneJson = (JSONObject) tabExtensionsVideo.get(i);
                extensionFilterTmp = new FileChooser.ExtensionFilter((String) ligneJson.get(DESCRIPTION),
                        (String) ligneJson.get(EXTENSION));

                listeFiltresVideo.add(extensionFilterTmp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FileChooser.ExtensionFilter> getListeFiltresAudio() {
        return listeFiltresAudio;
    }

    public ArrayList<FileChooser.ExtensionFilter> getListeFiltresVideo() {
        return listeFiltresVideo;
    }

    public URL getUrlFichierExtensions() {
        return urlFichierExtensions;
    }

    public void setUrlFichierExtensions(URL urlFichierExtensions) {
        this.urlFichierExtensions = urlFichierExtensions;
    }
}
