package ca.qc.bdeb.maveo.modele;

import ca.qc.bdeb.maveo.vue.MainFrame;

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
    public void activerOuvertureFichier(Component parent) {

        int returnVal =0;
       returnVal = fileChooser.showOpenDialog(parent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getName() + "." );
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }

}
