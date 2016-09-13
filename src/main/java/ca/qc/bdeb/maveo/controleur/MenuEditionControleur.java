package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.vue.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MenuEditionControleur {

    // vue MainFrame
    private MainFrame mainFrame;

    // modele FileOpener
    private FileOpener fileOpener;

    // Gestionnaire média
    private GestionnaireMusique gestionMusique;

    public MenuEditionControleur() {  }

    /**
     * Ajoute la fenêtre principale au contrôleur
     * @param mainFrame la fenêtre principale
     */
    public void ajouterMainFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.mainFrame.addOuvrirFichierListener(new OuvrirFichierListener());
    }

    /**
     * Ajoute un FileOpener au contrôleur
     * @param fileOpener le FileOpener à ajouter
     */
    public void ajouterFileOpener(FileOpener fileOpener){
        this.fileOpener = fileOpener;
    }

    /**
     * Ajoute un gestionnaire de média
     * @param gestionMusique gesionnaire de média à ajouter
     */
    public void ajouterGestionnaireMusique(GestionnaireMusique gestionMusique) {
        this.gestionMusique = gestionMusique;
    }

    /**
     * Active l'ouverture d'un fichier
     * @param parent fenêtre dans laquelle la fenêtre d'ouverture du fichier s'affiche
     */
    public void activerOuvertureFichier(Component parent) {
        fileOpener.activerOuvertureFichier(parent);
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'ouerture de fichier
     */
    class OuvrirFichierListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cheminFichier = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
            gestionMusique.demarrer(cheminFichier);
        }
    }
}
