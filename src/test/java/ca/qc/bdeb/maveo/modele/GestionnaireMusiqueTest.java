package ca.qc.bdeb.maveo.modele;

import ca.qc.bdeb.maveo.util.UtilLib;
import org.junit.Assert;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

import java.util.concurrent.*;

/**
 * Created by Nicholas on 10/09/16.
 */
public class GestionnaireMusiqueTest {

    private static final String TXT_LINK_MUSIQUE_TEST = "res/Gaara.mp3";

    static {
        UtilLib.chargerLibSysteme();
    }

    @org.junit.Test
    public void testPreparerMedia() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        boolean success = gestionMusique.preparerMedia();
        Assert.assertTrue(success);
    }

    @org.junit.Test
    public void testArreter() throws Exception {
        final GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.preparerMedia();
        gestionMusique.jouerMedia();
        gestionMusique.arreter();
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                future.complete(gestionMusique.enLecture());
            }
        }, 10, TimeUnit.MILLISECONDS);
        Assert.assertFalse(future.get());
    }

    @org.junit.Test
    public void testPause() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.preparerMedia();
        gestionMusique.jouerMedia();
        Thread.currentThread().join(250);
        gestionMusique.pause();
        Thread.currentThread().join(10);
        Assert.assertFalse(gestionMusique.enLecture());
    }

    @org.junit.Test
    public void testJouerMedia() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.preparerMedia();
        gestionMusique.jouerMedia();

    }

    @org.junit.Test
    public void testVolume() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
      //  gestionMusique.demarrer();
        Thread.currentThread().join(10);
        int volume = gestionMusique.getVolume();
        int volumeModifie = volume + 50;
        gestionMusique.setVolume(volumeModifie);
        Thread.currentThread().join(10);
        Assert.assertEquals(volumeModifie, gestionMusique.getVolume());
        //Retour au volume initial
        gestionMusique.setVolume(volume);
    }

    @org.junit.Test
    public void testTemps() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
      //  gestionMusique.demarrer();
        Thread.currentThread().join(1200);
        Assert.assertTrue(gestionMusique.getTempsEcoule() >= 1000);
        long tempsTotal = gestionMusique.getTempsTotal();
        Assert.assertTrue(gestionMusique.getTempsRestant() <= tempsTotal);
    }
}