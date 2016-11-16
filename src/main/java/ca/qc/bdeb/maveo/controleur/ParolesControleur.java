package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.paroles.ParolesIO;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by C A T A on 2016-11-15.
 */
public class ParolesControleur {

    MainFrame mainFrame;
    ParolesIO parolesIO;
    Media media;

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

    class MenuItemMediaOpenLyricHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            media = parolesIO.afficherFenetreOuvertureFichierParoles(mainFrame.getFenetre());
        }
    }


    class MenuItemMediaSaveLyricHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            media = GestionnaireFactory.getCurrentInstance().recupererMedia();
            parolesIO.afficherFenetreSauvegardeParoles(mainFrame.getFenetre(), media);
        }
    }
}
