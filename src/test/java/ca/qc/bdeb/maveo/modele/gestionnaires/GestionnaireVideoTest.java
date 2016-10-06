package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.util.UtilLib;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 06/10/16.
 */
public class GestionnaireVideoTest extends ApplicationTest {

    static final String FILE_PATH = "res/test.mp4";
    static GestionnaireVideo gestionnaireVideo;

    @Override
    public void start(Stage stage) throws Exception {
        UtilLib.chargerLibSysteme();
        final Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        gestionnaireVideo = new GestionnaireVideo(pane);
        gestionnaireVideo.setCheminFichier(FILE_PATH);
    }

    @Before
    public void before() {
        gestionnaireVideo.preparerMedia();
        gestionnaireVideo.jouerMedia();
    }

    @Test
    public void testPlayVideo() throws ExecutionException, InterruptedException {
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {

            @Override
            public void run() {
                future.complete(gestionnaireVideo.enLecture());
            }
        }, 200, TimeUnit.MILLISECONDS);
        Assert.assertTrue(future.get());
    }

    @Test
    public void testArret() throws ExecutionException, InterruptedException {
        gestionnaireVideo.arreter();
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                future.complete(gestionnaireVideo.enLecture());
            }
        }, 200, TimeUnit.MILLISECONDS);
        Assert.assertFalse(future.get());
    }

    @Test
    public void testPause() throws ExecutionException, InterruptedException {
        gestionnaireVideo.pause();
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {

            @Override
            public void run() {
                future.complete(gestionnaireVideo.enLecture());
            }

        }, 100, TimeUnit.MILLISECONDS);
        Assert.assertFalse(future.get());
    }

    @Test
    public void testVolume() {
        int volume = gestionnaireVideo.getVolume();
        int volumeModifie = volume + 100;
        gestionnaireVideo.setVolume(volumeModifie);
        Assert.assertEquals(volumeModifie, gestionnaireVideo.getVolume());
        //Retour au volume initial
        gestionnaireVideo.setVolume(volume);
    }

    @Test
    public void testTemps() throws ExecutionException, InterruptedException {
        gestionnaireVideo.pause();
        gestionnaireVideo.setPosition(0.20f);
        long pourcentage_courant = 20L;
        long pourcentage_total = 100L;
        long tempsEcoule = gestionnaireVideo.getTempsTotal() * pourcentage_courant / pourcentage_total;
        assertEquals(tempsEcoule, gestionnaireVideo.getTempsEcoule());
        long tempsTotal = gestionnaireVideo.getTempsTotal();
        Assert.assertTrue(gestionnaireVideo.getTempsRestant() <= tempsTotal);
    }


}