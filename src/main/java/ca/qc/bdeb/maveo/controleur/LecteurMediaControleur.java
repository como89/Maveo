package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.MainFrame;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 * Created by nicholas on 12/10/16.
 */
public class LecteurMediaControleur extends MediaPlayerEventAdapter {

    boolean isFreeMutexLockSliderPosition = true;

    public LecteurMediaControleur() {

    }

    MainFrame mainFrame;

    /**
     * Ajoute le mainframe au controleur.
     *
     * @param mainFrame - Le mainFrame.
     */
    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

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

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
        mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
        mainFrame.getBtnArreter().setDisable(true);
    }

    /**
     * Remet le slider de position au debut lorsque le média a terminé de jouer
     *
     * @param mediaPlayer le MediaPlayer dans lequel le média s'est arrêté
     */
    @Override
    public void finished(MediaPlayer mediaPlayer) {
        isFreeMutexLockSliderPosition = false; // active le verrou sur le slider de position
        mainFrame.getSliderProgression().setValue(mainFrame.getSliderProgression().getMin());
        mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
        mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
        isFreeMutexLockSliderPosition = true; // libère le verrou sur le slieder de position
    }
}
