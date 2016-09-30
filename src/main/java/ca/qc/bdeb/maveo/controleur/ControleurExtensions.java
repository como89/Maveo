package ca.qc.bdeb.maveo.controleur;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

/**
 * Created by C A T A on 2016-09-29.
 */
// TODO ceci n'est pas un controleur, car un controleur a les instances des éléments qu'il contrôle (simplement renommer)?
// TODO moyen de faire ça sans instanciacion avec des méthodes static ? Ex. AccesExtensions.getExtensionsAudio() (String[String[2]] / Data
// http://stackoverflow.com/questions/1568762/accessing-members-of-items-in-a-jsonarray-with-java
// http://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
public class ControleurExtensions {

    final String CHEMIN_FICHIER_EXTENSIONS = "src/main/java/ca/qc/bdeb/maveo/modele/ExtensionsMaveo.json"; // pourrait être dans Target

    public ControleurExtensions() {
        ouvrirFichierJson();
    }


    public void ouvrirFichierJson() {
        JSONParser jsonParser = new JSONParser();

        try {
            System.out.println(new File("").getAbsolutePath());
            Object obj = jsonParser.parse(new FileReader(new File("").getAbsolutePath() + "/" + CHEMIN_FICHIER_EXTENSIONS));

            JSONObject a = new JSONObject();
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray tabExtensionsAudio = (JSONArray) jsonObject.get("ExtensionsAudio");

            for (int i = 0; i < tabExtensionsAudio.size(); i++) {
                JSONObject o = (JSONObject) tabExtensionsAudio.get(i);
                String desc = (String) o.get("Texte");
                String regEx = (String) o.get("Extension");
                System.out.println("Desc = " + desc + " Ext = " + regEx);
            }
            String author = (String) jsonObject.get("Author");
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            System.out.println("Name: " + tabExtensionsAudio);
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

}
