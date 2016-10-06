package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Playlist;
import ca.qc.bdeb.maveo.modele.fichier.AccesExtensions;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireVideo;
import ca.qc.bdeb.maveo.vue.MainFrame;
import com.google.common.io.Files;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MainFrameControleur {

    boolean isFreeMutexLockSliderPosition = true;

    boolean isFreeMutexLockSliderVolume = false;

    //Ces deux variables sont temporaires.
    private final String PLAYLIST_NAME = "PlayList";
    private final int PLAYLIST_ID = 0;


    public MainFrameControleur() {
    }

    // vue MainFrame
    MainFrame mainFrame;

    // modele FileOpener
    FileOpener fileOpener;

    // Gestionnaire média
    GestionnaireMedia gestionnaireMedia;

    Playlist playList;

    /**
     * Ajoute la fenêtre principale au contrôleur
     *
     * @param mainFrame la fenêtre principale
     */
    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.addEventHandlerBtnPlay(new BtnJouerPauseEventHandler());
        this.mainFrame.addEventHandlerBtnStop(new BtnArreterEventHandler());
        this.mainFrame.addEventHandlerOuvrirFichier(new MenuItemOuvrirEventHandler());
        this.mainFrame.addChangeListenerSliderProgression(new SliderPositionChangeListener());
        this.mainFrame.addChangeListenerSliderVolume(new SliderVolumeChangeListener());
        this.mainFrame.addEventHandlerCreatePlaylist(new MenuCreatePlaylistEventHandler());
        this.mainFrame.addEventHandlerOpenPlaylist(new OpenPlaylistEventHandler());
        this.mainFrame.addEventHandlerAddMediaInPlayList(new MenuAddToPlaylistEventHandler());

        this.mainFrame.getSliderVolume().setValue(this.mainFrame.getSliderVolume().getMax());
        this.mainFrame.getSliderProgression().setDisable(true);
        this.mainFrame.getBtnArreter().setDisable(true);
        this.mainFrame.getBtnJouerPause().setDisable(true);
        this.mainFrame.getBoutonPrecedent().setDisable(true);
        this.mainFrame.getBoutonSuivant().setDisable(true);

    }

    /**
     * Ajoute un FileOpener au contrôleur
     *
     * @param fileOpener le FileOpener à ajouter
     */
    public void ajouterFileOpener(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }


    /**
     * Fixe la position du média en cours
     *
     * @param positionPourcentage la nouvelle position, en pourcentage
     */
    void fixerSliderPosition(float positionPourcentage) {
        gestionnaireMedia.setPosition(positionPourcentage);
    }

    /**
     * Fixe le volume pour le média.
     *
     * @param volumePourcentage - Le volume en pourcentage
     */
    void fixerVolumePosition(int volumePourcentage) {
        if (isFreeMutexLockSliderVolume) {
            gestionnaireMedia.setVolume(volumePourcentage);
        }
    }

    Media getMediaFromFile() {
        File file = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
        Media media = null;
        if (file != null) {
            media = new Media(file.getName(), file.getAbsolutePath());
        }
        return media;
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'ouverture de fichier
     */
    class MenuItemOuvrirEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            Media media = getMediaFromFile();
            if (media != null) {
                isFreeMutexLockSliderVolume = true;

                AccesExtensions accesExtensions = new AccesExtensions();
                String extensionFichier = Files.getFileExtension(media.getPathMedia());

                // Cherche les extensions vidéo
                FileChooser.ExtensionFilter extensionFilter = accesExtensions.getListeFiltresMedia()
                        .get(accesExtensions.INDICE_LISTE_FILTRES_MEDIA_FICHIERS_VIDEO);

                extensionFichier = "*." + extensionFichier;

                // Si vidéo
                if (extensionFilter.getExtensions().contains(extensionFichier)) {
                    gestionnaireMedia = new GestionnaireVideo(mainFrame.getVideoPane());
                } else {
                    gestionnaireMedia = new GestionnaireMusique();
                }


                gestionnaireMedia.setCheminFichier(media.getPathMedia());

                //mainFrame.getLabelNomChanson().setText(fichier.getName());
                gestionnaireMedia.preparerMedia();
                gestionnaireMedia.addMediaPlayerEventEventListener(new LecteurMediaEventListener());
                mainFrame.getBtnJouerPause().setDisable(false);
                mainFrame.getSliderProgression().setDisable(false);
            }

        }

    }

    /**
     * Déclencheur qui s'active lorsqu'un utilisateur appuie sur le menu de la création d'une playlist.
     */
    class MenuCreatePlaylistEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            playList = new Playlist(PLAYLIST_NAME, PLAYLIST_ID);
        }
    }

    class MenuAddToPlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Media media = getMediaFromFile();
            playList.ajouterMediaListe(media);
        }
    }

    class OpenPlaylistEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            File file = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
            //verifier que c est bien un fichier .json
            //ouvrir nouvelle fenetre
            //lire fichier json et mettre dans un tableau ou liste.
            //afficherPlaylist

        }
    }


    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton de Jouer/Pause
     */
    class BtnJouerPauseEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (gestionnaireMedia.enLecture()) { // En lecture -> pause
                gestionnaireMedia.pause();
                mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
                mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
            } else { // En pause -> lecture
                gestionnaireMedia.jouerMedia();
                mainFrame.getBtnArreter().setDisable(false);
                mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPlay");
                mainFrame.getBtnJouerPause().getStyleClass().add("buttonPause");

            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'arrêt
     */
    class BtnArreterEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            gestionnaireMedia.arreter();
            mainFrame.getBtnArreter().setDisable(true);
            mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
            mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de progression
     */
    class SliderPositionChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (isFreeMutexLockSliderPosition) {
                float position = newValue.floatValue();
                float diviseur = 100;
                fixerSliderPosition(position / diviseur);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de volume
     */
    class SliderVolumeChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            fixerVolumePosition(newValue.intValue());
        }
    }

    /**
     * Classe qui implémente l'interface MediaPlayerEventListener pour avoir accès aux évènements nécessaires
     */
    class LecteurMediaEventListener extends MediaPlayerEventAdapter {

        /**
         * Lorsque la position du média a changé, le slider est ajusté en conséquence
         *
         * @param mediaPlayer le MediaPlayer dans lequel la position du média a changé
         * @param v           la position, en pourcentage. Ex. 0.15 est 15%
         */
        @Override
        public void positionChanged(MediaPlayer mediaPlayer, float v) {
            isFreeMutexLockSliderPosition = false;
            double position = v;
            double multiplier = 100;
            mainFrame.getSliderProgression().setValue(position * multiplier);
            isFreeMutexLockSliderPosition = true;
        }

        /**
         * Remet le slider de position au debut lorsque le média a terminé de jouer
         *
         * @param mediaPlayer le MediaPlayer dans lequel le média s'est arrêté
         */
        @Override
        public void finished(MediaPlayer mediaPlayer) {
            isFreeMutexLockSliderPosition = false;
            mainFrame.getSliderProgression().setValue(mainFrame.getSliderProgression().getMin());
            mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
            mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
            isFreeMutexLockSliderPosition = true;
        }


    }
}
