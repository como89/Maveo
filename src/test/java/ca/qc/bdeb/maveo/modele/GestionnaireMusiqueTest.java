package ca.qc.bdeb.maveo.modele;

import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.util.UtilLib;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.util.concurrent.*;

public class GestionnaireMusiqueTest {

    private static final String TXT_LINK_MUSIQUE_TEST = "res/Tokyo.mp3";

    private GestionnaireMusique gestionMusique;

    @Before
    public void loadBefore() {
        UtilLib.chargerLibSysteme();
        gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
    }


    @org.junit.Test
    public void testPreparerMedia() throws Exception {
        boolean success = gestionMusique.preparerMedia();
        Assert.assertTrue(success);
    }

    @org.junit.Test
    public void testArreter() throws Exception {
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
        }, 50, TimeUnit.MILLISECONDS);
        Assert.assertFalse(future.get());
    }

    @org.junit.Test
    public void testJouerMedia() throws Exception {
        gestionMusique.preparerMedia();
        gestionMusique.jouerMedia();
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {

            @Override
            public void run() {
                future.complete(gestionMusique.enLecture());
            }
        }, 50, TimeUnit.MILLISECONDS);

        Assert.assertTrue(future.get());
    }

    @org.junit.Test
    public void testPause() throws Exception {
        gestionMusique.preparerMedia();
        gestionMusique.jouerMedia();
        gestionMusique.pause();
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {

            @Override
            public void run() {
                future.complete(gestionMusique.enLecture());
            }
        }, 50, TimeUnit.MILLISECONDS);
        Assert.assertFalse(future.get());
    }

    @org.junit.Test
    public void testVolume() throws Exception {
        gestionMusique.preparerMedia();
        int volume = gestionMusique.getVolume();
        int volumeModifie = volume + 50;
        gestionMusique.setVolume(volumeModifie);
        Assert.assertEquals(volumeModifie, gestionMusique.getVolume());
        //Retour au volume initial
        gestionMusique.setVolume(volume);
    }

    @org.junit.Test
    public void testTemps() throws Exception {
        gestionMusique.preparerMedia();
        gestionMusique.jouerMedia();
        gestionMusique.pause();
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {

            @Override
            public void run() {
                future.complete(true);
            }
        }, 80, TimeUnit.MILLISECONDS);
        if (future.get()) {
            gestionMusique.setPosition(0.16f);
            long tempsEcoule = gestionMusique.getTempsTotal() * 16 / 100;
            Assert.assertEquals(tempsEcoule, gestionMusique.getTempsEcoule());
            long tempsTotal = gestionMusique.getTempsTotal();
            Assert.assertTrue(gestionMusique.getTempsRestant() <= tempsTotal);
        }
    }

    @After
    public void unloadAfter() {
        gestionMusique.arreter();
    }
}