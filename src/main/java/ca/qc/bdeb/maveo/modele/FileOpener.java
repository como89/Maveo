package ca.qc.bdeb.maveo.modele;

import javafx.stage.*;
import org.json.simple.parser.JSONParser;

import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class FileOpener {

    FileChooser fileChooser;

    public FileOpener() {
        fileChooser = new FileChooser();
    }


    private void ouvrirJSON() {

        JSONParser parser = new JSONParser();




    }

    /**
     * Active la fenêtre d'ouverture d'un fichier.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File activerOuvertureFichier(Window parent) {
        return fileChooser.showOpenDialog(parent);
    }
}
