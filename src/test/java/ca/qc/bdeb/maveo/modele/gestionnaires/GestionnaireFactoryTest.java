package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.util.UtilLib;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 13/10/16.
 */
public class GestionnaireFactoryTest extends ApplicationTest {

    static Media media = new Media("test", "res/test.mp4");
    static GestionnaireMedia gestionnaireMedia;

    public final double HEIGHT_STAGE = 200;
    public final double WIDTH_STAGE = 400;


    MainFrame mainFrame;

    @Override
    public void start(Stage stage) throws Exception {
        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = loader.getController();
        mainFrame.getPanelEcran().resize(WIDTH_STAGE, HEIGHT_STAGE);
        Scene scene = new Scene(page, WIDTH_STAGE, HEIGHT_STAGE);
        stage.setScene(scene);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
        UtilLib.chargerLibSysteme();
    }

    @Before
    public void before() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gestionnaireMedia = GestionnaireFactory.createInstance(media, mainFrame);
            }
        });
    }

    @Test
    public void getCurrentInstance() throws Exception {
        Assert.assertEquals(gestionnaireMedia, GestionnaireFactory.getCurrentInstance());
    }

    @Test
    public void createInstance() throws Exception {
        Assert.assertNotNull(gestionnaireMedia);
    }
}