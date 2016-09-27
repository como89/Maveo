package ca.qc.bdeb.maveo.modele;

import javafx.stage.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class FileOpener {

    final String CHEMIN_FICHIER_EXTENSIONS = "src/main/java/ca.qc.bdev.maveo/modele/ExtensionsMaveo"; // pourrait être dans Target

    FileChooser fileChooser;

    public FileOpener() {
        fileChooser = new FileChooser();
    }


    private void ouvrirJSON() {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(CHEMIN_FICHIER_EXTENSIONS));


            JSONObject jsonObject = (JSONObject) obj;

            String name = (String) jsonObject.get("Name");
            String author = (String) jsonObject.get("Author");
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            System.out.println("Name: " + name);
            System.out.println("Author: " + author);
            System.out.println("\nCompany List:");

          /*  Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Active la fenêtre d'ouverture d'un fichier.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File activerOuvertureFichier(Window parent) {
        return fileChooser.showOpenDialog(parent);
    }
}
