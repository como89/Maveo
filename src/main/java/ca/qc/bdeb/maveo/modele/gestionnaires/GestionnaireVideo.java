package ca.qc.bdeb.maveo.modele.gestionnaires;

import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

/**
 * Created by nicholas on 26/09/16.
 */
public class GestionnaireVideo extends GestionnaireMedia {

    private DirectMediaPlayerComponent directMediaPlayerComponent;
    private DirectMediaPlayer embeddedMediaPlayer;

    private String linkFichier;

    public GestionnaireVideo() {
        // directMediaPlayerComponent = new DirectMediaPlayerComponent();
        //embeddedMediaPlayer = directMediaPlayerComponent.getMediaPlayer();
    }


    @Override
    public boolean preparerMedia() {
        return embeddedMediaPlayer.prepareMedia(linkFichier);
    }

    @Override
    public void arreter() {
        embeddedMediaPlayer.stop();
    }

    @Override
    public void pause() {
        embeddedMediaPlayer.pause();
    }

    @Override
    public boolean enLecture() {
        return false;
    }

    @Override
    public void jouerMedia() {

    }

    @Override
    public int getVolume() {
        return 0;
    }

    @Override
    public void setVolume(int volume) {

    }

    @Override
    public long getTempsRestant() {
        return 0;
    }

    @Override
    public long getTempsTotal() {
        return 0;
    }

    @Override
    public long getTempsEcoule() {
        return 0;
    }

    @Override
    public void setPosition(float position) {

    }

    @Override
    public void addMediaPlayerEventEventListener(MediaPlayerEventListener mediaPlayerEventListener) {

    }
}
