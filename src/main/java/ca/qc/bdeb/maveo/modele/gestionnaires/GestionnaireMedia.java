package ca.qc.bdeb.maveo.modele.gestionnaires;

import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

/**
 * Created by nicholas on 26/09/16.
 */
public abstract class GestionnaireMedia {

    GestionnaireMedia() {
    }

    /**
     * Retourne le chemin absolu du fichier média en cours
     *
     * @return le chemin absolu du fichier média en cours
     */
    public abstract String getCheminFichier();

    /**
     * Fixe le chemin du fichier média
     *
     * @param cheminFichier chaîne de caractères contenant le chemin absolu du fichier
     */
    public abstract void setCheminFichier(String cheminFichier);


    /**
     * Méthode qui permet de préparer le média sans le jouer
     *
     * @return Retourne true, si le fichier existe, false, le fichier n'existe pas.
     */
    public abstract boolean preparerMedia();

    /**
     * Méthode qui permet d'arrêter la musique.
     */
    public abstract void arreter();

    /**
     * Méthide qui permet de vider les ressources du média. (Buffer)
     */
    public abstract void release();

    /**
     * Méthode qui permet de mettre en pause la musique.
     */
    public abstract void pause();

    /**
     * Méthode pour savoir si la musique est en lecture.
     *
     * @return true si en lecture, false si pas en lecture
     */
    public abstract boolean enLecture();

    /**
     * Méthode qui permet de jouer la musique.
     */
    public abstract void jouerMedia();

    /**
     * Méthode pour obtenir le débit du volume.
     *
     * @return un pourcentage du volume entre 0 et 200.
     */
    public abstract int getVolume();

    /**
     * Méthode qui permet de modifier le volume de la musique.
     * Le volume est un percentage du vrai volume. Toute valeur dépassant 100
     * peut causer de la distorsion audio.
     *
     * @param volume - Un pourcentage entre 0 et 200.
     */
    public abstract void setVolume(int volume);

    /**
     * Méthode pour obtenir le temps restant d'une musique.
     *
     * @return le temps restant de la musique en millisecondes.
     */
    public abstract long getTempsRestant();

    /**
     * Méthode pour obtenir le temps total d'une musique.
     *
     * @return le temps total de la musique en millisecondes.
     */
    public abstract long getTempsTotal();

    public abstract String recupererTags();

    /**
     * Méthode pour obtenir le temps écoulé de la musique.
     *
     * @return le temps écoulé de la musique en millisecondes.
     */
    public abstract long getTempsEcoule();

    /**
     * Fixe la position du média
     *
     * @param position valeur de la position, pourcentage. Ex. 0.15 c'est 15%
     */
    public abstract void setPosition(float position);

    /**
     * Ajoute un Listener au lecteur média
     *
     * @param mediaPlayerEventListener le Listener à ajouter au lecteur média
     */
    public abstract void addMediaPlayerEventListener(MediaPlayerEventListener mediaPlayerEventListener);
}
