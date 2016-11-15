package ca.qc.bdeb.maveo.modele.gestionnaires;


import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.paroles.ChartLyricsClient;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v1;
import org.farng.mp3.id3.AbstractID3v2;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.DefaultMediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

import java.io.*;

/**
 * Cette classe permet de pouvoir gérer une musique (Jouer, pause, stop, volume et la position).
 *
 * @author Nicholas
 * @doc http://caprica.github.io/vlcj/javadoc/3.0.0/uk/co/caprica/vlcj/player/MediaPlayer.html
 */
class GestionnaireMusique extends GestionnaireMedia {
    public String lyrics;

    private MediaPlayer mediaPlayer;
    private AudioMediaPlayerComponent audioEcouteur;

    private String cheminFichier;

    public GestionnaireMusique() {
        audioEcouteur = new AudioMediaPlayerComponent();
        mediaPlayer = audioEcouteur.getMediaPlayer();

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


    public String recupererTags() {
        try {
            String title = "";
            String artist = "";
            MP3File file = new MP3File(cheminFichier);
            AbstractID3v2 id3V2 = file.getID3v2Tag();
            AbstractID3v1 id3v1 = file.getID3v1Tag();

            if (id3V2 != null) {
                title = id3V2.getSongTitle();
                artist = id3V2.getLeadArtist();
            } else if (id3v1 != null){
                title = id3v1.getSongTitle();
                artist = id3v1.getLeadArtist();
            }

            System.out.println("--------------------TITLE + ARTIST : " + title + " " + artist);
            System.out.println(file.getID3v2Tag());

            recupererParoles(title, artist);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        }
        return lyrics;

    }

    private String recupererParoles(String title, String artist) {
        String paroles = "";
        ChartLyricsClient clc = new ChartLyricsClient();
        try {
            lyrics = clc.getSongLyrics(artist, title).lyrics;
            paroles = lyrics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paroles;
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
     * Crée une classe Media avec les données du média en cours : titre, artiste, paroles
     *
     * @return classe Media avec les données courantes du média : titre, artiste, paroles
     */
    public Media recupererMedia() {
        Media media = null;

        MP3File file = null;
        try {
            file = new MP3File(cheminFichier);
            AbstractID3v2 id3V2 = file.getID3v2Tag();
            String titre = id3V2.getSongTitle();
            String artiste = id3V2.getLeadArtist();
            String paroles = recupererParoles(artiste, titre);
            media = new Media(titre, artiste, paroles);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        }

        return media;
    }
}
