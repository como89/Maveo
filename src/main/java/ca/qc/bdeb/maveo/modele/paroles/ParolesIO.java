package ca.qc.bdeb.maveo.modele.paroles;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import javafx.stage.Window;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * Created by C A T A on 2016-11-13.
 */
public class ParolesIO {

    public final static String CLE_JSON_PAROLES_NOM_MEDIA = "NomMedia";
    public final static String CLE_JSON_PAROLES_ARTISTE_MEDIA = "ArtisteMedia";
    public final static String CLE_JSON_PAROLES_CHEMIN_MEDIA = "CheminMedia";
    public final static String CLE_JSON_PAROLES_PAROLES_MEDIA = "ParolesMedia";


    FileOpener fileOpener;

    /**
     * Constructeur par défaut qui ne prend aucun paramètre
     */
    public ParolesIO() {
        fileOpener = new FileOpener();
    }

    /**
     * Ouvre une fenêtre de sauvegarde de Paroles où l'utilisateur peut choisir de sauvegarder un fichier paroles.
     * Le format de la sauvegarde est JSON, peu importe l'extension.
     *
     * @param context le contexte (la fenêtre) dans lequel le FileChooser sera affiché.
     * @param media   le média à partir duquel le fichier de paroles sera crée
     */
    public void afficherFenetreSauvegardeParoles(Window context, Media media) {
        File file = fileOpener.afficherFenetreSauvegardeParoles(context);

        sauvegarderParoles(file, media);
    }

    /**
     * Enregistre les parones au chemin spécifié dans le paramètre
     */
    void sauvegarderParoles(File file, Media media) {

        if (file != null && media != null) {
            JSONObject objetPrincipal = new JSONObject();

            objetPrincipal.put(CLE_JSON_PAROLES_NOM_MEDIA, media.getTitre());
            objetPrincipal.put(CLE_JSON_PAROLES_ARTISTE_MEDIA, media.getArtiste());
            objetPrincipal.put(CLE_JSON_PAROLES_CHEMIN_MEDIA, media.getPathMedia());
            objetPrincipal.put(CLE_JSON_PAROLES_PAROLES_MEDIA, media.getParolesMedia());

            try {

                FileWriter fw = new FileWriter(file.getAbsolutePath());

                fw.write(objetPrincipal.toString());
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ouvre une fenêtre d'ouverture de paroles dans laquelle l'utilisateur peut choisir d'ouvrir un fichier paroles.
     * La méthode lit le fichier choisi par l'utilisateur (si celui-ci en a choisi un) et crée un objet Media avec
     * l'information lue dedans. La méthode retourne l'objet Media crée.
     *
     * @param context le contexte (la fenêtre) dans lequel le FileChooser sera affiché.
     * @return l'objet Media crée. Null si l'utilisateur ne choisit pas de fichier.
     */
    public Media afficherFenetreOuvertureFichierParoles(Window context) {
        Media media = null;

        FileOpener fo = new FileOpener();
        File file = fo.activerOuvertureParoles(context);

        media = ouvrirFichierParoles(file);
        return media;
    }

    /**
     * Affiche fenêtre ouverture fichier paroles
     *
     * @return média avec paroles
     */
    Media ouvrirFichierParoles(File file) {
        Media media = null;

        if (file != null) {
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file.getAbsolutePath()));

                media = new Media(
                        (String) jsonObject.get(CLE_JSON_PAROLES_NOM_MEDIA),
                        (String) jsonObject.get(CLE_JSON_PAROLES_ARTISTE_MEDIA),
                        (String) jsonObject.get(CLE_JSON_PAROLES_CHEMIN_MEDIA),
                        (String) jsonObject.get(CLE_JSON_PAROLES_PAROLES_MEDIA));

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return media;
    }

}
