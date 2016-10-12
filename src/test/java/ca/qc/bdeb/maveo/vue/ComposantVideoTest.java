package ca.qc.bdeb.maveo.vue;

import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

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
        Scene scene = new Scene(videoPane, WIDTH, HEIGHT);
        composant = new ComposantVideo(videoPane);
        stage.setScene(scene);
    }

    @Test
    public void testComposantAllInitialised() {
        Assert.assertNotNull(composant.videoView);
        Assert.assertNotNull(composant.writableImage);
        PixelWriter pixel = composant.getPixWriter();
        Assert.assertNotNull(pixel);
    }

    @Test
    public void testFitImage() {
        float width = (float) videoPane.getWidth();
        float height = (float) videoPane.getHeight();
        float widthModify = width + WIDTH_ADD;
        float heightModify = height + HEIGHT_ADD;

        videoPane.resize(widthModify, heightModify);
        composant.fitImageWithSize(widthModify, heightModify);
        Assert.assertTrue(widthModify == (float) composant.videoView.getFitWidth());
        Assert.assertTrue(heightModify == (float) composant.videoView.getFitHeight());
    }
}