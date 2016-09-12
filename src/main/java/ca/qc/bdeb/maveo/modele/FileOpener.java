package ca.qc.bdeb.maveo.modele;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class FileOpener {

    JFileChooser fileChooser;

    public FileOpener() {
        fileChooser = new JFileChooser();
    }


    /*
    Active la fenêtre d'ouverture d'un fichier.
     */
    public String activerOuvertureFichier(Component parent) {

        int returnVal = 0;
        returnVal = fileChooser.showOpenDialog(parent);

        String cheminFichier = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            cheminFichier = file.getAbsolutePath();
        }
        return cheminFichier;
    }
}
