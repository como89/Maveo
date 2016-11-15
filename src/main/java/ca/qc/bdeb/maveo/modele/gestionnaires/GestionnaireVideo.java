package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.paroles.ChartLyricsClient;
import ca.qc.bdeb.maveo.vue.ComposantVideo;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import uk.co.caprica.vlcj.player.DefaultMediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

import java.io.IOException;

/**
 * @author Nicholas
 * @doc http://caprica.github.io/vlcj/javadoc/3.10.1/uk/co/caprica/vlcj/player/direct/DirectMediaPlayer.html
 */
class GestionnaireVideo extends GestionnaireMedia {

    private DirectMediaPlayer embeddedMediaPlayer;
    private ComposantVideo composantVideo;

    private String linkFichier;

    public GestionnaireVideo(ComposantVideo composantVideo) {
        this.composantVideo = composantVideo;
        this.embeddedMediaPlayer = composantVideo.getMediaPlayer();
    }

    @Override
    public String getCheminFichier() {
        return linkFichier;
    }

    @Override
    public void setCheminFichier(String linkFichier) {
        this.linkFichier = linkFichier;
    }

    @Override
    public boolean preparerMedia() {
        return embeddedMediaPlayer.prepareMedia(linkFichier);
    }

    @Override
    public void arreter() {
        embeddedMediaPlayer.stop();
        composantVideo.clearPixelWriter();
    }

    /**
     * Méthode qui permet de vider les ressources du média. (Buffer)
     */
    @Override
    public void release() {
        DefaultMediaPlayer defaultMediaPlayer = (DefaultMediaPlayer) embeddedMediaPlayer;
        defaultMediaPlayer.release();
    }

    @Override
    public void pause() {
        embeddedMediaPlayer.pause();
    }

    @Override
    public boolean enLecture() {
        return embeddedMediaPlayer.isPlaying();
    }

    @Override
    public void jouerMedia() {
        embeddedMediaPlayer.play();
    }

    @Override
    public int getVolume() {
        return embeddedMediaPlayer.getVolume();
    }

    @Override
    public void setVolume(int volume) {
        embeddedMediaPlayer.setVolume(volume);
    }

    @Override
    public long getTempsRestant() {
        return embeddedMediaPlayer.getLength() - embeddedMediaPlayer.getTime();
    }

    @Override
    public long getTempsTotal() {
        return embeddedMediaPlayer.getLength();
    }

    @Override
    public long getTempsEcoule() {
        return embeddedMediaPlayer.getTime();
    }

    @Override
    public void setPosition(float position) {
        embeddedMediaPlayer.setPosition(position);
    }

    @Override
    public void addMediaPlayerEventListener(MediaPlayerEventListener mediaPlayerEventListener) {
        embeddedMediaPlayer.addMediaPlayerEventListener(mediaPlayerEventListener);
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
            file = new MP3File(linkFichier);
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

    /**
     * Récupère les paroles du média en cours
     *
     * @param title  titre du média
     * @param artist artiste du média
     * @return paroles du média
     */
    private String recupererParoles(String title, String artist) {
        String paroles = "";
        ChartLyricsClient clc = new ChartLyricsClient();
        try {
            paroles = clc.getSongLyrics(artist, title).lyrics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paroles;
    }
}
