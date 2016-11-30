package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.util.UtilLib;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by nicholas on 05/10/16.
 */
public class ComposantVideoTest extends ApplicationTest {

    private ComposantVideo composant;

    private final float WIDTH = 600;
    private final float HEIGHT = 400;

    private final float WIDTH_ADD = 300;
    private final float HEIGHT_ADD = 400;

    MainFrame mainFrame;

    private Pane videoPane;

    @Override
    public void start(Stage stage) throws Exception {
        UtilLib.chargerLibSysteme();
        videoPane = new Pane();
        Scene scene = new Scene(videoPane, WIDTH, HEIGHT);
        //composant = new ComposantVideo(videoPane);
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

    @Test
    public void testRafraichirVideo() throws IOException {


        URL ressource = getClass().getClassLoader().getResource("MainStage.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = loader.getController();
      //  mainFrame.getPanelEcran().resize(WIDTH, HEIGHT);

        UtilLib.chargerLibSysteme();

        MainFrame mainFrame = loader.getController();
        BorderPane panelTest = new BorderPane();
        panelTest.setBackground(Background.EMPTY);
        panelTest.resize(200, 200);
     //   mainFrame.setPanelEcran(panelTest);


        final int width_vid = 1280;
        final int height_vid = 720;
        Media media = new Media("Exemple", "res/video_samples/example.mkv");

        GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();

        gestionnaireMedia = GestionnaireFactory.createInstance(media, mainFrame);
        gestionnaireMedia.preparerMedia();

        gestionnaireMedia.jouerMedia();

        // Si la vidéo n'est pas en lecture, attendre qu'il se mette en lecture
        while (!gestionnaireMedia.enLecture()) {
            // Attendre que la vidéo se mette en lecture
        }

        gestionnaireMedia.pause();

        /**
         * Attendre que les variables de dimensions dans le framework s'initialisent avant de redimensionner
         *  la vidéo afin qu'elle respecte son rapport d'aspect.
         */
        while (GestionnaireFactory.getComposantVideo().getMediaPlayer().getVideoDimension() == null) {
            // Attendre que les variables de dimensions dans le framework s'initialisent
        }

        // Redimensionne la vidéo afin qu'elle respecte son rapport d'aspect original
        GestionnaireFactory.getComposantVideo().rafraichirVideo();
        Dimension dim = GestionnaireFactory.getComposantVideo().getMediaPlayer().getVideoDimension();

        Assert.assertNotEquals(200, dim.getHeight());
        Assert.assertNotEquals(200, dim.getWidth());
    }


}