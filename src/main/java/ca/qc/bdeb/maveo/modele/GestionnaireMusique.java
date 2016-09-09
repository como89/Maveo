package ca.qc.bdeb.maveo.modele;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;

/**
 * Created by nicholas on 08/09/16.
 */
public class GestionnaireMusique {

    private MediaPlayer mediaPlayer;

    public GestionnaireMusique() {
        AudioMediaPlayerComponent audioEcouteur = new AudioMediaPlayerComponent();
        mediaPlayer = audioEcouteur.getMediaPlayer();
    }

    public void demarer(String pathFichier) {
        mediaPlayer.startMedia(pathFichier);
    }

    public void arreter() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void reprendre() {
        mediaPlayer.play();
    }

    public int getVolume() {
        return mediaPlayer.getVolume();
    }


}
