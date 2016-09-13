package ca.qc.bdeb.maveo.modele;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;

/**
 * @author Nicholas
 * @since 08/09/2016
 * @doc http://caprica.github.io/vlcj/javadoc/3.0.0/uk/co/caprica/vlcj/player/MediaPlayer.html
 */
public class GestionnaireMusique {

    private MediaPlayer mediaPlayer;

    private String cheminFichier;

    public GestionnaireMusique() {
        AudioMediaPlayerComponent audioEcouteur = new AudioMediaPlayerComponent();
        mediaPlayer = audioEcouteur.getMediaPlayer();
    }

    /**
     * Méthode qui permet de démarrer une musique selon le path du fichier.
     *
     * @return true, si l'exécution a eu succès, false si pas de succès.
     */
    public boolean demarrer() {
        return mediaPlayer.startMedia(cheminFichier);
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
     * @return true si en lecture, false si pas en lecture
     */
    public boolean enLecture(){
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
     *
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
     * Méthode pour obtenir le temps total d'une musique.
     * @return le temps total de la musique en millisecondes.
     */
    public long getTempsTotal() {
        return mediaPlayer.getLength();
    }

    /**
     * Méthode pour obtenir le temps écoulé de la musique.
     *
     * @return le temps écoulé de la musique en millisecondes.
     */
    public long getTempsEcoule() {
        return mediaPlayer.getTime();
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }
}
