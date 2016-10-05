package ca.qc.bdeb.maveo.modele.Fichier;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class FileOpener {

    FileChooser fileChooser;
    AccesExtensions accesExtensions;

    public FileOpener() {
        fileChooser = new FileChooser();
        accesExtensions = new AccesExtensions();
        activerFiltresOuverture();
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

    /**
     * Active les filtres d'ouverture de fichier
     */
    public void activerFiltresOuverture() {
        fileChooser.getExtensionFilters().addAll(accesExtensions.getListeFiltresOuverture());
    }
}
