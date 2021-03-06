package ca.qc.bdeb.maveo.modele.gestionnaires;


import ca.qc.bdeb.maveo.modele.tags.Tags;
import ca.qc.bdeb.maveo.modele.tags.TagsIO;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.DefaultMediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

/**
 * Cette classe permet de pouvoir gérer une musique (Jouer, pause, stop, volume et la position).
 *
 * @author Nicholas
 * @doc http://caprica.github.io/vlcj/javadoc/3.0.0/uk/co/caprica/vlcj/player/MediaPlayer.html
 */
public class GestionnaireMusique extends GestionnaireMedia {

    private MediaPlayer mediaPlayer;
    private AudioMediaPlayerComponent audioEcouteur;

    private String cheminFichier;
    private TagsIO tagsIO;
    private Tags tags;

    GestionnaireMusique() {
        audioEcouteur = new AudioMediaPlayerComponent();
        mediaPlayer = audioEcouteur.getMediaPlayer();
        tagsIO = new TagsIO();
    }

    /**
     * Méthode qui permet de préparer le média sans le jouer
     *
     * @return Retourne true, si le fichier existe, false, le fichier n'existe pas.
     */
    public boolean preparerMedia() {
        return mediaPlayer.prepareMedia(cheminFichier);
    }

    /**
     * Retourne le chemin absolu du fichier média en cours
     *
     * @return le chemin absolu du fichier média en cours
     */
    public String getCheminFichier() {
        return cheminFichier;
    }

    /**
     * Fixe le chemin du fichier média
     *
     * @param cheminFichier chaîne de caractères contenant le chemin absolu du fichier
     */
    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    /**
     * Méthode qui permet d'arrêter la musique.
     */
    public void arreter() {
        mediaPlayer.stop();
    }

    /**
     * Méthide qui permet de vider les ressources du média. (Buffer)
     */
    public void release() {
        DefaultMediaPlayer defaultMediaPlayer = (DefaultMediaPlayer) mediaPlayer;
        defaultMediaPlayer.release();
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
    public boolean enLecture() {
        return mediaPlayer.isPlaying();
    }

    /**
     * Méthode qui permet de jouer la musique.
     */
    public void jouerMedia() {
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
     * Le volume est un percentage du vrai volume. Toute valeur dépassant 100
     * peut causer de la distorsion audio.
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
     *
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

    /**
     * Fixe la position du média
     *
     * @param position valeur de la position, pourcentage. Ex. 0.15 c'est 15%
     */
    public void setPosition(float position) {
        mediaPlayer.setPosition(position);
    }

    /**
     * Ajoute un Listener au lecteur média
     *
     * @param mediaPlayerEventListener le Listener à ajouter au lecteur média
     */
    public void addMediaPlayerEventListener(MediaPlayerEventListener mediaPlayerEventListener) {
        mediaPlayer.addMediaPlayerEventListener(mediaPlayerEventListener);
    }

    /**
     * Méthode qui permet de récupérer les tags du média.
     * @return Retourne null, si aucun tag existe.
     */
    public Tags getTags() {
        tags = tagsIO.getTagsFromMedia(cheminFichier);
        return tags;
    }
}
