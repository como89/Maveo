package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.io.*;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MainFrameControleur {

    static boolean isFreeMutexLockSliderVolume = false;

    public MainFrameControleur() {
    }

    // vue MainFrame
    MainFrame mainFrame;

    // modele FileOpener
    FileOpener fileOpener;

    LecteurMediaControleur controleurLecteurMedia;

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
     * Ajoute le controleur d'évènement pour le lecteur média.
     *
     * @param lecteurMediaEventListener - Controleur d'évènement du lecteur média.
     */
    public void ajouterControleurLecteurMedia(LecteurMediaControleur lecteurMediaEventListener) {
        this.controleurLecteurMedia = lecteurMediaEventListener;
    }

    /**
     * Fixe la position du média en cours
     *
     * @param positionPourcentage la nouvelle position, en pourcentage
     */
    void fixerSliderPosition(float positionPourcentage) {
        GestionnaireFactory.getCurrentInstance().setPosition(positionPourcentage);
    }

    /**
     * Fixe le volume pour le média.
     *
     * @param volumePourcentage - Le volume en pourcentage
     */
    void fixerVolumePosition(int volumePourcentage) {
        GestionnaireFactory.getCurrentInstance().setVolume(volumePourcentage);
    }

    Media getMediaFromFile() {
        File file = fileOpener.activerOuvertureMedia(mainFrame.getFenetre());
        Media media = null;
        if (file != null) {
            media = new Media(file.getName(), file.getPath());
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
                GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
                if (gestionnaireMedia != null && gestionnaireMedia.enLecture()) {
                    gestionnaireMedia.arreter();
                }
                gestionnaireMedia = GestionnaireFactory.createInstance(media, mainFrame);
                gestionnaireMedia.preparerMedia();
                gestionnaireMedia.addMediaPlayerEventListener(controleurLecteurMedia);
                mainFrame.getBtnJouerPause().setDisable(false);
                mainFrame.getSliderProgression().setDisable(false);
                mainFrame.getTraitProgress().setDisable(false);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton de Jouer/Pause
     */
    class BtnJouerPauseEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
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
            GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
            gestionnaireMedia.arreter();
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de progression
     */
    class SliderPositionChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (controleurLecteurMedia.isFreeMutexLockSliderPosition) {
                float position = newValue.floatValue();
                float diviseur = 100;
                fixerSliderPosition(position / diviseur);
                mainFrame.getTraitProgress().setProgress(position / diviseur
                        + LecteurMediaControleur.PROGRESS_BAR_POSITION_CORRECTION);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de volume
     */
    class SliderVolumeChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            int volumePourcentage = newValue.intValue();
            if (isFreeMutexLockSliderVolume) {
                isFreeMutexLockSliderVolume = false;
                fixerVolumePosition(volumePourcentage);
                isFreeMutexLockSliderVolume = true;
            }
            mainFrame.getProgressVolume().setProgress(volumePourcentage / 100.0);
        }
    }
}
