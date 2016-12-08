package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.vue.ComposantVideo;
import uk.co.caprica.vlcj.player.DefaultMediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

import java.io.File;

/**
 * @author Nicholas
 * @doc http://caprica.github.io/vlcj/javadoc/3.10.1/uk/co/caprica/vlcj/player/direct/DirectMediaPlayer.html
 */
public class GestionnaireVideo extends GestionnaireMedia {

    private DirectMediaPlayer embeddedMediaPlayer;
    private ComposantVideo composantVideo;

    public boolean isEstSousTitresDesactives() {
        return estSousTitresDesactives;
    }

    boolean estSousTitresDesactives = false;

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


    public void cacherAfficherSousTitres(){


        if(!estSousTitresDesactives){

            embeddedMediaPlayer.setSpu(-1);
            estSousTitresDesactives = true;


        }
        else{


            embeddedMediaPlayer.setSpu(2);


            estSousTitresDesactives = false;
        }

    }

    public void chargerSousTitres(File file) {
        embeddedMediaPlayer.setSubTitleFile(file);
    }
}
