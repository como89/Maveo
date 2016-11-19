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
     * Active la fenêtre d'ouverture de média.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher.
     * @return le fichier sélectionné ou null si aucune sélection n'a été effectuée.
     */
    public File activerOuvertureMedia(Window parent) {
        activerFiltresMedia();
        return fileChooser.showOpenDialog(parent);
    }

    /**
     * Active la fenêtre d'ouverture d'un fichier playlist.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher.
     * @return le fichier sélectionné ou null si aucune sélection n'a été effectuée.
     */
    public File activerOuverturePlaylist(Window parent) {
        activerFiltresPlaylist();
        return fileChooser.showOpenDialog(parent);
    }

    /**
     * Active la fenêtre d'ouverture d'un fichier paroles.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher.
     * @return le fichier sélectionné ou null si aucune sélection n'a été effectuée.
     */
    public File activerOuvertureParoles(Window parent) {
        activerFiltresParoles();
        return fileChooser.showOpenDialog(parent);
    }

    /**
     * Active la fenêtre de sauveagarde de média.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher.
     * @return le fichier sélectionné ou null si aucune sélection n'a été effectuée.
     */
    public File afficherFenetreSauvegardeMedia(Window parent) {
        activerFiltresMedia();
        return fileChooser.showSaveDialog(parent);
    }

    /**
     * Active la fenêtre de sauveagarde de playlist.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher.
     * @return le fichier sélectionné ou null si aucune sélection n'a été effectuée.
     */
    public File afficherFenetreSauvegardePlaylist(Window parent) {
        activerFiltresPlaylist();
        return fileChooser.showSaveDialog(parent);
    }

    /**
     * Affiche la fenêtre de sauvegarde de paroles.
     *
     * @param parent La composante dans laquelle la fenêtre d'ouverture doit s'afficher.
     * @return le fichier sélectionné ou null si aucune sélection n'a été effectuée.
     */
    public File afficherFenetreSauvegardeParoles(Window parent) {
        activerFiltresParoles();
        return fileChooser.showSaveDialog(parent);
    }

    /**
     * Active les filtres d'ouverture de fichier média.
     */
    void activerFiltresMedia() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(accesExtensions.getListeFiltresMedia());
    }

    /**
     * Active les filtres d'ouverture de fichier playlist.
     */
    void activerFiltresPlaylist() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(accesExtensions.getListeFiltresPlaylist());
    }

    /**
     * Active les fitres d'ouverture de fuchier paroles.
     */
    void activerFiltresParoles() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(accesExtensions.getListeFiltresParoles());
    }
}
