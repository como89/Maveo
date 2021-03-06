package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.util.UtilLib;
import ca.qc.bdeb.maveo.vue.ComposantVideo;
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


    static final String PATH = "res/video_samples/example.mp4";
    static GestionnaireVideo gestionnaireVideo;

    @Override
    public void start(Stage stage) throws Exception {
        UtilLib.chargerLibSysteme();
        final Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        //ComposantVideo composantVideo = new ComposantVideo(pane);
        //gestionnaireVideo = new GestionnaireVideo(composantVideo);
        gestionnaireVideo.setCheminFichier(PATH);
    }

    @Before
    public void before() {
        gestionnaireVideo.preparerMedia();
    }

    @Test
    public void testPlayVideo() throws ExecutionException, InterruptedException {
        gestionnaireVideo.jouerMedia();
        Thread.sleep(1000);
        Assert.assertTrue(gestionnaireVideo.enLecture());
    }

    @Test
    public void testArret() throws ExecutionException, InterruptedException {
        gestionnaireVideo.jouerMedia();
        Thread.sleep(500);
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
        gestionnaireVideo.jouerMedia();
        Thread.sleep(500);
        gestionnaireVideo.pause();
        Thread.sleep(500);
        boolean enLecture = gestionnaireVideo.enLecture();
        Assert.assertFalse(enLecture);
    }

    @Test
    public void testVolume() {
        gestionnaireVideo.jouerMedia();
        int volume = gestionnaireVideo.getVolume();
        int volumeModifie = volume + 100;
        gestionnaireVideo.setVolume(volumeModifie);
        Assert.assertEquals(volumeModifie, gestionnaireVideo.getVolume());
        //Retour au volume initial
        gestionnaireVideo.setVolume(volume);
    }

    @Test
    public void testTemps() throws ExecutionException, InterruptedException {
        gestionnaireVideo.jouerMedia();
        gestionnaireVideo.pause();
        gestionnaireVideo.setPosition(0.30f);
        long pourcentage_courant = 30L;
        long pourcentage_total = 100L;
        long tempsEcoule = gestionnaireVideo.getTempsTotal() * pourcentage_courant / pourcentage_total;
        assertEquals(tempsEcoule, gestionnaireVideo.getTempsEcoule());
        long tempsTotal = gestionnaireVideo.getTempsTotal();
        Assert.assertTrue(gestionnaireVideo.getTempsRestant() <= tempsTotal);
    }

    @Test
    public void testFormat3GP() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.3gp");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatFLV() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.flv");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatMKV() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.mkv");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatMPG() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.mpg");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatWEBM() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.webm");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatMOV() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.mov");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatMP4() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.mp4");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }

    @Test
    public void testFormatWMV() {
        gestionnaireVideo.setCheminFichier("res/video_samples/example.wmv");
        boolean success = gestionnaireVideo.preparerMedia();
        Assert.assertTrue(success);
    }



    @Test
    public void getVolume() throws Exception {
gestionnaireVideo.setVolume(25);
Assert.assertEquals(gestionnaireVideo.getVolume(), 25);
    }

    @Test
    public void setVolume() throws Exception {
        gestionnaireVideo.setVolume(25);
        Assert.assertEquals(gestionnaireVideo.getVolume(), 25);

    }

    @Test
    public void getTempsRestant() throws Exception {

    }

    @Test
    public void getTempsTotal() throws Exception {

    }

    @Test
    public void getTempsEcoule() throws Exception {

    }

    @Test
    public void setPosition() throws Exception {

    }

    @Test
    public void addMediaPlayerEventListener() throws Exception {

    }

    @Test
    public void cacherAfficherSousTitres() throws Exception {

    }

    @Test
    public void chargerSousTitres() throws Exception {

    }

}