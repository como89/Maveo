package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.MenuEditionControleur;
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

        // Lecteur média
        GestionnaireMusique gestionMusique = new GestionnaireMusique();

        MenuEditionControleur menuEditionControleur = new MenuEditionControleur();
        menuEditionControleur.ajouterMainFrame(mainFrame);
        menuEditionControleur.ajouterFileOpener(fileOpener);
        menuEditionControleur.ajouterGestionnaireMusique(gestionMusique);


    }
}
