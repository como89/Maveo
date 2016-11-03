package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.application.Platform;
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
                mainFrame.getLabelTempsTotal().setText(texteDureeRestant + " / "+texteDureeTotale);
                mainFrame.getSliderProgression().setValue(position * multiplier);

                isFreeMutexLockSliderPosition = true;
            }
        });
    }

    private String convertirDuree(long dureeAConvertir) {

        long secondes = (dureeAConvertir / 1000) % 60;
        long minute = (dureeAConvertir / (1000 * 60)) % 60;

        String dureeAffichable = String.format("%02d:%02d", minute, secondes);
        return dureeAffichable;

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
