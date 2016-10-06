package ca.qc.bdeb.maveo.vue;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.*;

/**
 * Created by nicholas on 05/10/16.
 */
public class ComposantVideoTest extends ApplicationTest {

    private ComposantVideo composant;

    private final float WIDTH = 600;
    private final float HEIGHT = 400;

    private final float WIDTH_ADD = 300;
    private final float HEIGHT_ADD = 400;

    private Pane videoPane;

    @Override
    public void start(Stage stage) throws Exception {
        videoPane = new Pane();
        composant = new ComposantVideo();
        Scene scene = new Scene(videoPane, WIDTH, HEIGHT);
        stage.setScene(scene);
    }

    @Test
    public void testComposantAllInitialised() {
        composant.setVideoPane(videoPane);
        Assert.assertNotNull(composant.imageView);
        Assert.assertNotNull(composant.writableImage);
        PixelWriter pixel = composant.getPixWriter();
        Assert.assertNotNull(pixel);
    }

    @Test
    public void testFitImage() throws ExecutionException, InterruptedException {
        composant.setVideoPane(videoPane);
        final float width = (float) videoPane.getWidth();
        final float height = (float) videoPane.getHeight();
        composant.fitImageWithSize(width + WIDTH_ADD,
                height + HEIGHT_ADD);
        final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                future.complete(true);
            }
        }, 20, TimeUnit.MILLISECONDS);
        if (future.get()) {
            System.out.println(width + WIDTH_ADD);
            System.out.println(composant.imageView.getFitWidth());
            Assert.assertTrue(width + WIDTH_ADD == (float) composant.imageView.getFitWidth());
            Assert.assertTrue(height + HEIGHT_ADD == (float) videoPane.getHeight());
        }
    }
}