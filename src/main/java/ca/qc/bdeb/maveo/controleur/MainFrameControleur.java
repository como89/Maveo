package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.vue.MainFrame;
import ca.qc.bdeb.maveo.vue.TypeComposant;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MainFrameControleur {

    public MainFrameControleur() {

    }

    // vue MainFrame
    private MainFrame mainFrame;

    // modele FileOpener
    private FileOpener fileOpener;

    // Gestionnaire média
    private GestionnaireMusique gestionMusique;


    /**
     * Ajoute la fenêtre principale au contrôleur
     *
     * @param mainFrame la fenêtre principale
     */
    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.addEventHandlerBtnPlay(new BtnJouerPauseEventHandler());
        this.mainFrame.addEventHandlerOuvrirFichier(new MenuItemOuvrirEventHandler());
    }

    /**
     * Ajoute un FileOpener au contrôleur
     *
     * @param fileOpener le FileOpener à ajouter
     */
    public void ajouterFileOpener(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    /**
     * Ajoute un gestionnaire de média
     *
     * @param gestionMusique gesionnaire de média à ajouter
     */
    public void ajouterGestionnaireMusique(GestionnaireMusique gestionMusique) {
        this.gestionMusique = gestionMusique;
    }

    /**
     * Active l'ouverture d'un fichier
     *
     * @param parent fenêtre dans laquelle la fenêtre d'ouverture du fichier s'affiche
     */
    public void activerOuvertureFichier(Window parent) {
        fileOpener.activerOuvertureFichier(parent);
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'ouverture de fichier
     */
    class MenuItemOuvrirEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            File fichier = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
            gestionMusique.setCheminFichier(fichier.getAbsolutePath());
            // mainFrame.getLabelNomChanson().setText(fichier.getName());
            gestionMusique.preparerMedia();
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton de Jouer/Pause
     */
    class BtnJouerPauseEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (gestionMusique.enLecture()) {
                gestionMusique.pause();
                mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);
            } else {
                gestionMusique.jouerMedia();
                mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_PAUSE);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'arrêt
     */
    class BtnArreterEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
           /* gestionMusique.arreter();
            mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);*/
        }
    }
}
