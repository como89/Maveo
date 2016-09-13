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
     * @return true, si l'exécution a eu succès, false si pas de succès.
     */
    public boolean demarrer(String pathFichier) {
        return mediaPlayer.startMedia(pathFichier);
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
     * Méthode pour savoir si la musique est en lecture.
     *
     * @return true, en lecture, false n'est pas en lecture.
     */
    public boolean enCoursDeLecture() {
        return mediaPlayer.isPlaying();
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
