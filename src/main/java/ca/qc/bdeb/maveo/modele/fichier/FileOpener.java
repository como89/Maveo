package ca.qc.bdeb.maveo.modele.fichier;

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
        activerFiltresMedia();
    }

    /**
     * Active la fenêtre d'ouverture de média
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File activerOuvertureMedia(Window parent) {
        activerFiltresMedia();
        return fileChooser.showOpenDialog(parent);
    }

    /**
     * Active la fenêtre d'ouverture d'un fichier.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File activerOuverturePlaylist(Window parent) {
        activerFiltresPlaylist();
        return fileChooser.showOpenDialog(parent);
    }

    /**
     * Active la fenêtre de sauveagarde de média
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File afficherFenetreSauvegardeMedia(Window parent) {
        activerFiltresMedia();
        return fileChooser.showSaveDialog(parent);
    }

    /**
     * Active la fenêtre de sauveagarde de playlist
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher
     * @return le chemin du fichier à ouvrir
     */
    public File afficherFenetreSauvegardePlaylist(Window parent) {
        activerFiltresPlaylist();
        return fileChooser.showSaveDialog(parent);
    }

    /**
     * Active les filtres d'ouverture de fichier
     */
    public void activerFiltresMedia() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(accesExtensions.getListeFiltresMedia());
    }

    public void activerFiltresPlaylist() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(accesExtensions.getListeFiltresPlaylist());
    }
}
