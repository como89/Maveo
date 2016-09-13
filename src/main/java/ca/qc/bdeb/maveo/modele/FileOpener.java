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


    /**
     * Active la fenêtre d'ouverture d'un fichier.
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File activerOuvertureFichier(Component parent) {

        int returnVal = 0;
        returnVal = fileChooser.showOpenDialog(parent);

        File fichier = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fichier = fileChooser.getSelectedFile();
        }
        return fichier;
    }
}
