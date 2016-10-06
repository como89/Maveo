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
    public final String NOM_JSON_EXTENSIONS_OUVERTURE = "ExtensionsOuverture";
    public final String DESCRIPTION = "Description";
    public final String EXTENSIONS = "Extensions";


    URL urlFichierExtensions;

    ArrayList<FileChooser.ExtensionFilter> listeFiltresOuverture;


    public AccesExtensions() {
        this.urlFichierExtensions = getClass().getClassLoader().getResource(NOM_RESSOURCE_FICHIER_EXTENSIONS);
        ouvrirFichierExtensions();
    }

    /**
     * Lit le fichier d'extensions audio et vidéo
     */
    void ouvrirFichierExtensions() {
        JSONParser jsonParser = new JSONParser();
        listeFiltresOuverture = new ArrayList<FileChooser.ExtensionFilter>();

        try {
            File file = new File(urlFichierExtensions.toURI());

            JSONObject jsonObject =
                    (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));

            JSONObject jsonObjectExtensionsOuverture = (JSONObject) jsonObject.get(NOM_JSON_EXTENSIONS_OUVERTURE);

            String description = (String) jsonObjectExtensionsOuverture.get(DESCRIPTION);

            JSONArray tabExtensionsMedia = (JSONArray) jsonObjectExtensionsOuverture.get(EXTENSIONS);

            FileChooser.ExtensionFilter extensionFilterTmp =
                    new FileChooser.ExtensionFilter(description,
                            tabExtensionsMedia);
            listeFiltresOuverture.add(extensionFilterTmp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FileChooser.ExtensionFilter> getListeFiltresOuverture() {
        return listeFiltresOuverture;
    }

    public URL getUrlFichierExtensions() {
        return urlFichierExtensions;
    }

    public void setUrlFichierExtensions(URL urlFichierExtensions) {
        this.urlFichierExtensions = urlFichierExtensions;
    }
}
