package ca.qc.bdeb.maveo.modele.fichier;

import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by C A T A on 2016-10-04.
 */
public class AccesExtensionsTest {

    private ArrayList<FileChooser.ExtensionFilter> listeFiltresMedia;
    ArrayList<FileChooser.ExtensionFilter> listeFiltresPlaylist;
    private URL urlFichierExtensions;
    private AccesExtensions accesExtensions;

    @Before
    public void setUp() throws Exception {
        accesExtensions = new AccesExtensions();
    }

    @Test
    public void ouvrirFichierExtensions() throws Exception {

        lireFichierExtensions(accesExtensions);

        Assert.assertTrue(comparerDeuxArrayLists(accesExtensions.getListeFiltresMedia(), listeFiltresMedia));
        Assert.assertTrue(comparerDeuxArrayLists(accesExtensions.getListeFiltresPlaylist(), listeFiltresPlaylist));
    }


    @Test
    public void getListeFiltresOuverture() throws Exception {
        Assert.assertEquals(accesExtensions.listeFiltresMedia, accesExtensions.getListeFiltresMedia());
    }

    @Test
    public void getUrlFichierExtensions() throws Exception {
        Assert.assertEquals(accesExtensions.getUrlFichierExtensions(), accesExtensions.urlFichierExtensions);
    }

    @Test
    public void setUrlFichierExtensions() throws Exception {
        urlFichierExtensions = new URL("http://www.google.ca");
        accesExtensions.setUrlFichierExtensions(urlFichierExtensions);
        Assert.assertEquals(urlFichierExtensions, accesExtensions.getUrlFichierExtensions());
        ;
    }

    void lireFichierExtensions(AccesExtensions accesExtensions) {
        JSONParser jsonParser = new JSONParser();
        listeFiltresMedia = new ArrayList<FileChooser.ExtensionFilter>();
        listeFiltresPlaylist = new ArrayList<FileChooser.ExtensionFilter>();
        try {
            urlFichierExtensions = getClass().getClassLoader().getResource(accesExtensions.NOM_RESSOURCE_FICHIER_EXTENSIONS);

            File file = new File(urlFichierExtensions.toURI());

            JSONObject jsonObject =
                    (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));

            JSONArray jsonArrayExtensionsMedia = (JSONArray) jsonObject.get(accesExtensions.NOM_JSON_EXTENSIONS_MEDIA);

            JSONObject jsonObjectExtensionsMedia;
            String description;
            JSONArray tabExtensionsMedia;
            FileChooser.ExtensionFilter extensionFilterTmp;
            for (int i = 0; i < jsonArrayExtensionsMedia.size(); i++) {
                jsonObjectExtensionsMedia = (JSONObject) jsonArrayExtensionsMedia.get(i);
                description = (String) jsonObjectExtensionsMedia.get(accesExtensions.DESCRIPTION);
                tabExtensionsMedia = (JSONArray) jsonObjectExtensionsMedia.get(accesExtensions.EXTENSIONS);
                extensionFilterTmp =
                        new FileChooser.ExtensionFilter(description, tabExtensionsMedia);
                listeFiltresMedia.add(extensionFilterTmp);
            }

            JSONArray jsonArrayExtensionsPlaylist = (JSONArray) jsonObject.get(accesExtensions.NOM_JSON_EXTENSIONS_PLAYLIST);

            JSONObject jsonObjectExtensionsPlaylist;
            JSONArray tabExtensionsPlaylist;
            for (int i = 0; i < jsonArrayExtensionsPlaylist.size(); i++) {
                jsonObjectExtensionsPlaylist = (JSONObject) jsonArrayExtensionsPlaylist.get(i);
                description = (String) jsonObjectExtensionsPlaylist.get(accesExtensions.DESCRIPTION);
                tabExtensionsPlaylist = (JSONArray) jsonObjectExtensionsPlaylist.get(accesExtensions.EXTENSIONS);
                extensionFilterTmp = new FileChooser.ExtensionFilter(description, tabExtensionsPlaylist);
                listeFiltresPlaylist.add(extensionFilterTmp);
            }

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

        Iterator<FileChooser.ExtensionFilter> it1 = liste1.iterator();
        Iterator<FileChooser.ExtensionFilter> it2 = liste2.iterator();


        while (it1.hasNext() && it2.hasNext() && sontIdentiques == true) {
            sontIdentiques = verifierContenuFiltresIdentique(it1.next(), it2.next());
        }

        return sontIdentiques;
    }


    private boolean verifierContenuFiltresIdentique(FileChooser.ExtensionFilter filtre1, FileChooser.ExtensionFilter filtre2) {
        boolean contenuIdentique = true;

        if (!filtre1.getDescription().equals(filtre2.getDescription())) {
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