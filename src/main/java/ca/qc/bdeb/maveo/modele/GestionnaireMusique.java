package ca.qc.bdeb.maveo.modele;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;

/**
 * @author Nicholas
 * @since 08/09/2016
 */
public class GestionnaireMusique {

    private MediaPlayer mediaPlayer;

    public GestionnaireMusique() {
        AudioMediaPlayerComponent audioEcouteur = new AudioMediaPlayerComponent();
        mediaPlayer = audioEcouteur.getMediaPlayer();
    }

    /**
     * Méthode qui permet de démarrer une musique selon le path du fichier.
     *
     * @param pathFichier - Le chemin du fichier audio.
     */
    public void demarer(String pathFichier) {
        mediaPlayer.startMedia(pathFichier);
    }

    /**
     * Méthode qui permet d'arrêter la musique.
     */
    public void arreter() {
        mediaPlayer.stop();
    }

    /**
     * Méthode qui permet de mettre en pause la musique.
     */
    public void pause() {
        mediaPlayer.pause();
    }

    /**
     * Méthode pour savoir si la musique est en pause.
     *
     * @return true, en pause, false n'est pas en pause.
     */
    public boolean enPause() {
        return !mediaPlayer.canPause();
    }

    /**
     * Méthode qui permet de reprendre la musique.
     */
    public void reprendre() {
        mediaPlayer.play();
    }

    /**
     * Méthode pour obtenir le débit du volume.
     * @return un pourcentage du volume entre 0 et 200.
     */
    public int getVolume() {
        return mediaPlayer.getVolume();
    }

    /**
     * Méthode qui permet de modifier le volume de la musique.
     *
     * @param volume - Un pourcentage entre 0 et 200.
     */
    public void setVolume(int volume) {
        mediaPlayer.setVolume(volume);
    }

    /**
     * Méthode pour obtenir le temps restant d'une musique.
     *
     * @return le temps restant de la musique en millisecondes.
     */
    public long getTempsRestant() {
        return mediaPlayer.getLength() - mediaPlayer.getTime();
    }

    /**
     * Méthode pour obtenir le temps écoulé de la musique.
     *
     * @return le temps écoulé de la musique en millisecondes.
     */
    public long getTempsEcoule() {
        return mediaPlayer.getTime();
    }
}
