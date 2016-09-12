package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.ControleurMenu;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.util.UtilLib;
import ca.qc.bdeb.maveo.vue.MainFrame;

public class MainClass {
    public static void main(String[] args) {

        //chargement des libs
        UtilLib.chargerLibSysteme();

        // vue
        MainFrame mainFrame = new MainFrame();

        // modele
        FileOpener fileOpener = new FileOpener();

        ControleurMenu controleurMenu = new ControleurMenu(mainFrame, fileOpener);

        GestionnaireMusique gestionMusique = new GestionnaireMusique();

        controleurMenu.ajouterGestionnaireMusique(gestionMusique);
    }
}
