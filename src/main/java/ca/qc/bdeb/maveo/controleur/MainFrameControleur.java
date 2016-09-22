package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MainFrameControleur {

    boolean isFreeMutexLockSliderProgression = true;

    boolean isFreeMutexLockSliderVolume = false;


    public MainFrameControleur() {
    }

    // vue MainFrame
    MainFrame mainFrame;

    // modele FileOpener
    FileOpener fileOpener;

    // Gestionnaire média
    GestionnaireMusique gestionnaireMusique;

    /**
     * Ajoute la fenêtre principale au contrôleur
     *
     * @param mainFrame la fenêtre principale
     */
    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.addEventHandlerBtnPlay(new BtnJouerPauseEventHandler());
        this.mainFrame.addEventHandlerOuvrirFichier(new MenuItemOuvrirEventHandler());
        this.mainFrame.addChangeListenerSliderProgression(new SliderPositionChangeListener());
        this.mainFrame.addChangeListenerSliderVolume(new SliderVolumeChangeListener());
        this.mainFrame.getSliderVolume().setValue(this.mainFrame.getSliderVolume().getMax());
        this.mainFrame.getSliderProgression().setDisable(true);
        this.mainFrame.getBtnJouerPause().setDisable(true);

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
     * Ajoute un gestionnaire de média
     *
     * @param gestionMusique gesionnaire de média à ajouter
     */
    public void ajouterGestionnaireMusique(GestionnaireMusique gestionMusique) {
        this.gestionnaireMusique = gestionMusique;
        isFreeMutexLockSliderVolume = true;
    }


    /**
     * Fixe la position du média en cours
     *
     * @param positionPourcentage la nouvelle position, en pourcentage
     */
    void fixerSliderPosition(float positionPourcentage) {
        gestionnaireMusique.setPosition(positionPourcentage);
    }

    /**
     * Fixe le volume pour le média.
     *
     * @param volumePourcentage - Le volume en pourcentage
     */
    void fixerVolumePosition(int volumePourcentage) {
        if (isFreeMutexLockSliderVolume) {
            gestionnaireMusique.setVolume(volumePourcentage);
        }
    }


    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'ouverture de fichier
     */
    class MenuItemOuvrirEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            File fichier = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
            if (fichier != null) {
                gestionnaireMusique.setCheminFichier(fichier.getAbsolutePath());
                // mainFrame.getLabelNomChanson().setText(fichier.getName());
                gestionnaireMusique.preparerMedia();
                gestionnaireMusique.addMediaPlayerEventEventListener(new LecteurMediaEventListener());
                mainFrame.getBtnJouerPause().setDisable(false);
                mainFrame.getSliderProgression().setDisable(false);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton de Jouer/Pause
     */
    class BtnJouerPauseEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (gestionnaireMusique.enLecture()) {
                gestionnaireMusique.pause();
                mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);
            } else {
                gestionnaireMusique.jouerMedia();
                mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_PAUSE);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'arrêt
     */
    class BtnArreterEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
           /* gestionnaireMusique.arreter();
            mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);*/
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de progression
     */
    class SliderPositionChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (isFreeMutexLockSliderProgression) {
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
            isFreeMutexLockSliderProgression = false;
            double position = v;
            double multiplier = 100;
            mainFrame.getSliderProgression().setValue(position * multiplier);
            isFreeMutexLockSliderProgression = true;
        }

        /**
         * Remet le slider de position au debut lorsque le média a terminé de jouer
         *
         * @param mediaPlayer le MediaPlayer dans lequel le média s'est arrêté
         */
        @Override
        public void finished(MediaPlayer mediaPlayer) {
            isFreeMutexLockSliderProgression = false;
            mainFrame.getSliderProgression().setValue(mainFrame.getSliderProgression().getMin());
            mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);
            isFreeMutexLockSliderProgression = true;
        }
    }
}
