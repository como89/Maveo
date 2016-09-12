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
public class ControleurMenu {

    // vue MainFrame
    private MainFrame mainFrame;

    // modele FileOpener
    private FileOpener fileOpener;

    private GestionnaireMusique gestionMusique;

    public ControleurMenu(MainFrame mainFrame, FileOpener fileOpener) {
        this.mainFrame = mainFrame;
        this.fileOpener = fileOpener;
        this.mainFrame.addOuvrirFichierListener(new OuvrirFichierListener());
    }

    public void ajouterGestionnaireMusique(GestionnaireMusique gestionMusique) {
        this.gestionMusique = gestionMusique;
    }


    public void activerOuvertureFichier(Component parent) {
        fileOpener.activerOuvertureFichier(parent);
    }

    class OuvrirFichierListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cheminFichier = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
            gestionMusique.demarer(cheminFichier);
        }
    }

}
