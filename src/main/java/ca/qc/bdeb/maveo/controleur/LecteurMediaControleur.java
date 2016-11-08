package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.application.Platform;
import sun.applet.Main;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 * Created by nicholas on 12/10/16.
 */
public class LecteurMediaControleur extends MediaPlayerEventAdapter {

    boolean isFreeMutexLockSliderPosition = true;

    static final double PROGRESS_BAR_POSITION_CORRECTION = 0.003;

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
    public void positionChanged(final MediaPlayer mediaPlayer, final float v) {
        isFreeMutexLockSliderPosition = false;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double position = v;
                double multiplier = 100;

                long dureeEcoule = mediaPlayer.getTime();
                long dureeTotale = mediaPlayer.getLength();
                long dureeRestant = mediaPlayer.getLength() - mediaPlayer.getTime();

                String texteDureeEcoulee = convertirDuree(dureeEcoule);
                String texteDureeTotale = convertirDuree(dureeTotale);
                String texteDureeRestant = convertirDuree(dureeRestant);
                mainFrame.getLabelTempsEcoule().setText(texteDureeEcoulee);
                mainFrame.getLabelTempsTotal().setText(texteDureeRestant + " / " + texteDureeTotale);
                mainFrame.getSliderProgression().setValue(position * multiplier);
                mainFrame.getTraitProgress().setProgress(position + PROGRESS_BAR_POSITION_CORRECTION);

                isFreeMutexLockSliderPosition = true;
            }
        });
    }

    private String convertirDuree(long dureeAConvertir) {

        long secondes = (dureeAConvertir / 1000) % 60;
        long minute = (dureeAConvertir / (1000 * 60)) % 60;

        return String.format("%02d:%02d", minute, secondes);

    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        stopMedia();
    }

    /**
     * Remet le slider de position au debut lorsque le média a terminé de jouer
     *
     * @param mediaPlayer le MediaPlayer dans lequel le média s'est arrêté
     */
    @Override
    public void finished(MediaPlayer mediaPlayer) {
        stopMedia();
    }

    void stopMedia() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                isFreeMutexLockSliderPosition = false; // active le verrou sur le slider de position
                mainFrame.getSliderProgression().setValue(mainFrame.getSliderProgression().getMin());
                mainFrame.getTraitProgress().setProgress(0);
                mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
                mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
                mainFrame.getLabelTempsEcoule().setText(MainFrame.DEFAULT_TEMPS_ECOULE);
                mainFrame.getLabelTempsTotal().setText(MainFrame.DEFAULT_TEMPS_TOTAL);
                mainFrame.getBtnArreter().setDisable(true);
                isFreeMutexLockSliderPosition = true; // libère le verrou sur le slieder de position
            }
        });
    }
}
