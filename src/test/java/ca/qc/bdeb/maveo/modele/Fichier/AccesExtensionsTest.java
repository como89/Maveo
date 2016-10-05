package ca.qc.bdeb.maveo.modele.Fichier;

import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by C A T A on 2016-10-04.
 */
public class AccesExtensionsTest {

    private ArrayList<FileChooser.ExtensionFilter> listeFiltresOuverture;
    private URL urlFichierExtensions;
    private AccesExtensions accesExtensions;

    @Test
    public void ouvrirFichierExtensions() throws Exception {
        accesExtensions = new AccesExtensions();
        lireFichierExtensions(accesExtensions);

        Assert.assertTrue(comparerDeuxArrayLists(accesExtensions.getListeFiltresOuverture(), listeFiltresOuverture));

    }

    @Test
    public void getListeFiltresOuverture() throws Exception {

    }

    @Test
    public void getListeFiltresVideo() throws Exception {

    }

    @Test
    public void getUrlFichierExtensions() throws Exception {

    }

    @Test
    public void setUrlFichierExtensions() throws Exception {

    }

    void lireFichierExtensions(AccesExtensions accesExtensions) {
        JSONParser jsonParser = new JSONParser();
        listeFiltresOuverture = new ArrayList<FileChooser.ExtensionFilter>();

        try {
            urlFichierExtensions = getClass().getClassLoader().getResource(accesExtensions.NOM_RESSOURCE_FICHIER_EXTENSIONS);
            File file = new File(urlFichierExtensions.toURI());

            JSONObject jsonObject =
                    (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));

            JSONObject jsonObjectExtensionsOuverture =
                    (JSONObject) jsonObject.get(accesExtensions.NOM_JSON_EXTENSIONS_OUVERTURE);

            String description = (String) jsonObjectExtensionsOuverture.get(accesExtensions.DESCRIPTION);

            JSONArray tabExtensionsMedia = (JSONArray) jsonObjectExtensionsOuverture.get(accesExtensions.EXTENSIONS);

            FileChooser.ExtensionFilter extensionFilterTmp =
                    new FileChooser.ExtensionFilter(description,
                            tabExtensionsMedia);
            listeFiltresOuverture.add(extensionFilterTmp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prend deux ArrayList en paramètre et compare pour savoir si elle sont exactement identiques. L'ordre des
     * éléments, ainsi que leur contenu est important.
     *
     * @param liste1 La première liste
     * @param liste2 La deuxième liste
     * @return true si listes identiques, sinon false
     */
    boolean comparerDeuxArrayLists(ArrayList<FileChooser.ExtensionFilter> liste1,
                                   ArrayList<FileChooser.ExtensionFilter> liste2) {
        boolean sontIdentiques = true;

        // On effectue une copie de la première liste pour ne pas altérer le contenu de celle-ci
        ArrayList<FileChooser.ExtensionFilter> copieListe1 = new ArrayList<FileChooser.ExtensionFilter>(liste1);




        for (Object filtre : liste2) {
            // Si l'item trouvé dans la deuxième liste ne se retrouve pas dans la première liste alors arrête la boucle
            if (!copieListe1.remove(filtre)) {
                sontIdentiques = false;
                break;
            }
        }

        // Si le tous les éléments de la deuxième liste se retrouvent dans la première liste on vérifie qu'il n'y a pas
        // des éléments de plus dans la première liste
        if (sontIdentiques) {
            sontIdentiques = copieListe1.isEmpty();
        }

        return sontIdentiques;
    }


    private boolean verifierContenuFiltresIdentique(FileChooser.ExtensionFilter filtre1, FileChooser.ExtensionFilter filtre2) {
        boolean contenuIdentique = true;

        if (filtre1.getDescription() != filtre2.getDescription()) {
            contenuIdentique = false;
        }

        // Ne vérifiepas pour les doublons (on ne devrait pas avoir des doublons dans les fichier d'extensions)
        if (!(filtre1.getExtensions().containsAll(filtre2.getExtensions()) &&
                filtre1.getExtensions().size() == filtre2.getExtensions().size())) {
            contenuIdentique = false;
        }


        return contenuIdentique;
    }

}