package ca.qc.bdeb.maveo.modele.paroles;

import ca.qc.bdeb.maveo.modele.Media;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by C A T A on 2016-11-15.
 */
public class ParolesIOTest {
    private final static String CHEMIN_FICHIER_PAROLES_TEST = "res/tokyo_paroles.maveop";
    private final String NOM_DOSSIER_TEST_TMP = ".testTmp";
    private final String NOM_FICHIER_TEST = "tokyo_paroles_test_maveop";
    private final String NOM_CHANSON = "Space Oddity";
    private final String NOM_ARTISTE_CHANSON = "David Bowie";
    private final String CHEMIN_CHANSON = "/champignons/magiques/David Bowie - Space Odity.mp3";
    private final String PAROLES_CHANSON = "Ground Control to Major Tom\n" +
            "Ground Control to Major Tom\n" +
            "Take your protein pills and put your helmet on\n" +
            "Ground Control to Major Tom (ten, nine, eight, seven, six)\n" +
            "Commencing countdown, engines on (five, four, three)\n" +
            "Check ignition and may God's love be with you (two, one, liftoff)\n" +
            "This is Ground Control to Major Tom\n" +
            "You've really made the grade\n" +
            "And the papers want to know whose shirts you wear\n" +
            "Now it's time to leave the capsule if you dare\n" +
            "\"This is Major Tom to Ground Control\n" +
            "I'm stepping through the door\n" +
            "And I'm floating in a most peculiar way\n" +
            "And the stars look very different today\n" +
            "For here\n" +
            "Am I sitting in a tin can\n" +
            "Far above the world\n" +
            "Planet Earth is blue\n" +
            "And there's nothing I can do\n" +
            "Though I'm past one hundred thousand miles\n" +
            "I'm feeling very still\n" +
            "And I think my spaceship knows which way to go\n" +
            "Tell my wife I love her very much she knows\n" +
            "Ground Control to Major Tom\n" +
            "Your circuit's dead, there's something wrong\n" +
            "Can you hear me, Major Tom?\n" +
            "Can you hear me, Major Tom?\n" +
            "Can you hear me, Major Tom?\n" +
            "Can you \"Here am I floating 'round my tin can\n" +
            "Far above the moon\n" +
            "Planet Earth is blue\n" +
            "And there's nothing I can do\"";

    static ParolesIO parolesIO;

    @BeforeClass
    public static void beforeClass() {
        parolesIO = new ParolesIO();
    }

    @Test
    public void sauvegarderParoles() throws Exception {
        Media mediaOriginal = new Media(NOM_CHANSON, NOM_ARTISTE_CHANSON, CHEMIN_CHANSON, PAROLES_CHANSON);

        File dossierTest = new File(NOM_DOSSIER_TEST_TMP);
        String a = dossierTest.getAbsolutePath();

        dossierTest.mkdir();

        File fichierSauvegarde = new File(dossierTest.getAbsolutePath() + "/" + NOM_FICHIER_TEST);

        parolesIO.sauvegarderParoles(fichierSauvegarde, mediaOriginal);

        Media mediaATester = parolesIO.ouvrirFichierParoles(fichierSauvegarde);

        Assert.assertEquals(mediaOriginal.getTitre(), mediaATester.getTitre());
        Assert.assertEquals(mediaOriginal.getPathMedia(), mediaATester.getPathMedia());
        Assert.assertEquals(mediaOriginal.getParolesMedia(), mediaATester.getParolesMedia());

        supprimerDossier(dossierTest);

    }

    /**
     * Supprime un dossier passé par paramètre et tous les fichiers s'y trouvant.
     *
     * @param file dossier à supprimer
     * @throws IOException erreur lancée entre autre lorsqu'on a des droits insuffisants
     */
    public static void supprimerDossier(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then supprimerDossier it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive supprimerDossier
                    supprimerDossier(fileDelete);
                }

                //check the directory again, if empty then supprimerDossier it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then supprimerDossier it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }


    @Test
    public void ouvrirFichierParoles() throws Exception {
        Media mediaOriginal = parolesIO.ouvrirFichierParoles(new File(CHEMIN_FICHIER_PAROLES_TEST));
        Media mediaATester = new Media(NOM_CHANSON, NOM_ARTISTE_CHANSON, CHEMIN_CHANSON, PAROLES_CHANSON);

        Assert.assertEquals(mediaOriginal.getTitre(), mediaATester.getTitre());
        Assert.assertEquals(mediaOriginal.getPathMedia(), mediaATester.getPathMedia());
        Assert.assertEquals(mediaOriginal.getParolesMedia(), mediaATester.getParolesMedia());
    }

}