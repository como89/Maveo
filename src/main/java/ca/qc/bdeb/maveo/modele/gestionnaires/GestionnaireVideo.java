package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.vue.ComposantVideo;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

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
}
