package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Window;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

import java.io.File;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MainFrameControleur {

    boolean isFreeMutexLockSlider = true;


    public MainFrameControleur() {
    }

    // vue MainFrame
    private MainFrame mainFrame;

    // modele FileOpener
    private FileOpener fileOpener;

    // Gestionnaire média
    private GestionnaireMusique gestionMusique;

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
        this.gestionMusique = gestionMusique;
    }

    /**
     * Active l'ouverture d'un fichier
     *
     * @param parent fenêtre dans laquelle la fenêtre d'ouverture du fichier s'affiche
     */
    public void activerOuvertureFichier(Window parent) {
        fileOpener.activerOuvertureFichier(parent);
    }

    /**
     * Fixe la position du média en cours
     *
     * @param positionPourcentage la nouvelle position, en pourcentage
     */
    void fixerSliderPosition(float positionPourcentage) {
        gestionMusique.setPosition(positionPourcentage);
    }


    void fixVolumePosition(int volumePourcentage) {
        gestionMusique.setVolume(volumePourcentage);
    }


    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'ouverture de fichier
     */
    class MenuItemOuvrirEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            File fichier = fileOpener.activerOuvertureFichier(mainFrame.getFenetre());
            gestionMusique.setCheminFichier(fichier.getAbsolutePath());
            // mainFrame.getLabelNomChanson().setText(fichier.getName());
            gestionMusique.preparerMedia();
            gestionMusique.addMediaPlayerEventEventListener(new LecteurMediaEventListener());
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton de Jouer/Pause
     */
    class BtnJouerPauseEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (gestionMusique.enLecture()) {
                gestionMusique.pause();
                mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);
            } else {
                gestionMusique.jouerMedia();
                mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_PAUSE);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'arrêt
     */
    class BtnArreterEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
           /* gestionMusique.arreter();
            mainFrame.getBtnJouerPause().setText(mainFrame.STR_BOUTON_JOUER);*/
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de progression
     */
    class SliderPositionChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (isFreeMutexLockSlider) {
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
            fixVolumePosition(newValue.intValue());
        }
    }

    class LecteurMediaEventListener implements MediaPlayerEventListener {

        @Override
        public void positionChanged(MediaPlayer mediaPlayer, float v) {
            isFreeMutexLockSlider = false;
            double position = v;
            double multiplier = 100;
            mainFrame.getSliderProgression().setValue(position * multiplier);
            isFreeMutexLockSlider = true;
        }

        @Override
        public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t libvlc_media_t, String s) {
        }

        @Override
        public void opening(MediaPlayer mediaPlayer) {
        }

        @Override
        public void buffering(MediaPlayer mediaPlayer, float v) {
        }

        @Override
        public void playing(MediaPlayer mediaPlayer) {

        }

        @Override
        public void paused(MediaPlayer mediaPlayer) {
        }

        @Override
        public void stopped(MediaPlayer mediaPlayer) {
        }

        @Override
        public void forward(MediaPlayer mediaPlayer) {
        }

        @Override
        public void backward(MediaPlayer mediaPlayer) {
        }

        @Override
        public void finished(MediaPlayer mediaPlayer) {
        }

        @Override
        public void timeChanged(MediaPlayer mediaPlayer, long l) {
        }

        @Override
        public void seekableChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void pausableChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void titleChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void snapshotTaken(MediaPlayer mediaPlayer, String s) {

        }

        @Override
        public void lengthChanged(MediaPlayer mediaPlayer, long l) {

        }

        @Override
        public void videoOutput(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void scrambledChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void elementaryStreamAdded(MediaPlayer mediaPlayer, int i, int i1) {

        }

        @Override
        public void elementaryStreamDeleted(MediaPlayer mediaPlayer, int i, int i1) {

        }

        @Override
        public void elementaryStreamSelected(MediaPlayer mediaPlayer, int i, int i1) {

        }

        @Override
        public void corked(MediaPlayer mediaPlayer, boolean b) {

        }

        @Override
        public void muted(MediaPlayer mediaPlayer, boolean b) {

        }

        @Override
        public void volumeChanged(MediaPlayer mediaPlayer, float v) {

        }

        @Override
        public void audioDeviceChanged(MediaPlayer mediaPlayer, String s) {

        }

        @Override
        public void chapterChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void error(MediaPlayer mediaPlayer) {

        }

        @Override
        public void mediaMetaChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void mediaSubItemAdded(MediaPlayer mediaPlayer, libvlc_media_t libvlc_media_t) {

        }

        @Override
        public void mediaDurationChanged(MediaPlayer mediaPlayer, long l) {

        }

        @Override
        public void mediaParsedChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void mediaFreed(MediaPlayer mediaPlayer) {

        }

        @Override
        public void mediaStateChanged(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void mediaSubItemTreeAdded(MediaPlayer mediaPlayer, libvlc_media_t libvlc_media_t) {

        }

        @Override
        public void newMedia(MediaPlayer mediaPlayer) {

        }

        @Override
        public void subItemPlayed(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void subItemFinished(MediaPlayer mediaPlayer, int i) {

        }

        @Override
        public void endOfSubItems(MediaPlayer mediaPlayer) {

        }
    }

}
