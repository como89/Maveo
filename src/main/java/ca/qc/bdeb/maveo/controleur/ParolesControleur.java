package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.paroles.ChartLyricsClient;
import ca.qc.bdeb.maveo.modele.paroles.ParolesIO;
import ca.qc.bdeb.maveo.modele.tags.Tags;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

import java.awt.*;

import static java.awt.Color.*;

/**
 * Created by C A T A on 2016-11-15.
 */
public class ParolesControleur {
    MainFrame mainFrame;
    ParolesIO parolesIO;

    /**
     * Constructeur par défaut qui ne prend aucun paramètre
     */
    public ParolesControleur() {
        parolesIO = new ParolesIO();
    }


    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.addEventHandlerMenuItemMediaOpenLyric(new MenuItemMediaOpenLyricHandler());
        this.mainFrame.addEventHandlerMenuItemMediaSaveLyric(new MenuItemMediaSaveLyricHandler());
    }
        /**
     * Cette méthode permet de charger les paroles, soit grâce au fichier média, dans un fichier .maveop ou
     * le programme demande à l'utilisateur les informations nécessaire pour charger les paroles.
     */
    private void chargerLyric() {
        GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
        if (gestionnaireMedia instanceof GestionnaireMusique) {
            Media media = null;
            GestionnaireMusique gestionnaireMusique = (GestionnaireMusique) gestionnaireMedia;
            if (mainFrame.openQuestionDialog()) {
                media = parolesIO.afficherFenetreOuvertureFichierParoles(mainFrame.getFenetre());
            } else {
                Tags tags = gestionnaireMusique.getTags();
                if (tags == null) {
                    tags = mainFrame.openRequestInformation();
                }
                String paroles = recupererParoles(tags.getTitle(), tags.getArtist());
                media = new Media(tags.getTitle(), tags.getArtist(), gestionnaireMusique.getCheminFichier(), paroles);
            }
            // Afficher les paroles
            if(media != null) {
             //   mainFrame.getScrollPane().setVisible(true);
            //    mainFrame.getLyricTitle().setText(media.getTitre());
             //   mainFrame.getLyricText().setText(media.getParolesMedia());
            }
        }
    }

    /**
     * Récupère les paroles du média en cours
     *
     * @param title  titre du média
     * @param artist artiste du média
     * @return paroles du média
     */
    private String recupererParoles(String title, String artist) {
        String paroles = "";
        try {
            paroles = ChartLyricsClient.getSongLyrics(artist, title).lyrics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paroles;
    }

    class MenuItemMediaOpenLyricHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            chargerLyric();
        }
    }

    class MenuItemMediaSaveLyricHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
            if (gestionnaireMedia instanceof GestionnaireMusique) {
                GestionnaireMusique gestionnaireMusique = (GestionnaireMusique) gestionnaireMedia;
                Tags tags = gestionnaireMusique.getTags();
                Media media = new Media(tags.getTitle(),
                        tags.getArtist(),
                        gestionnaireMusique.getCheminFichier(),
                        recupererParoles(tags.getTitle(), tags.getArtist()));
                parolesIO.afficherFenetreSauvegardeParoles(mainFrame.getFenetre(), media);
            }
        }
    }
}
