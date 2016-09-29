package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;

/**
 * Created by C A T A on 2016-09-12.
 */
public class MainFrameControleurTest extends ApplicationTest {

    MainFrameControleur mainFrameControleur;
    MainFrame mainFrame;
    GestionnaireMusique gestionnaireMusique;
    FileOpener fileOpener;
    float floatTest;


    private void setTestingFloatNumber(float newValue) {
        floatTest = newValue;
    }

    private float getTestingFloatNumber() {
        return floatTest;
    }

    @Override
    public void start(Stage stage) throws Exception {

        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = (MainFrame) loader.getController();
        Scene sceneTest = new Scene(page);
        stage.setScene(sceneTest);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);

        stage.show();

    }

    @Test
    public void ajouterMainFrameTest() throws Exception {

        mainFrameControleur = new MainFrameControleur();
        mainFrameControleur.ajouterMainFrame(mainFrame);

        Assert.assertEquals(mainFrameControleur.mainFrame, mainFrame);
    }

    @Test
    public void ajouterFileOpenerTest() {

        mainFrameControleur = new MainFrameControleur();
        fileOpener = new FileOpener();
        mainFrameControleur.ajouterFileOpener(fileOpener);

        Assert.assertEquals(mainFrameControleur.fileOpener, fileOpener);
    }

    @Test
    public void fixerSliderPositionTest() {

        double positionSliderTest = -1;
        mainFrameControleur = new MainFrameControleur();

        positionSliderTest = mainFrame.getSliderProgression().getMax() / Math.PI;
        mainFrame.getSliderProgression().setValue(positionSliderTest);
        Assert.assertTrue(mainFrame.getSliderProgression().getValue() == positionSliderTest);
    }

    @Test
    public void fixerVolumePositionTest() {

        double positionSliderTest = -1;

        mainFrameControleur = new MainFrameControleur();

        positionSliderTest = mainFrame.getSliderVolume().getMax() / Math.PI;

        mainFrame.getSliderVolume().setValue(positionSliderTest);

        Assert.assertTrue(mainFrame.getSliderVolume().getValue() == positionSliderTest);

    }

    //  @Test
    public void MenuItemOuvrirEventHandlerTest() {
        //mainFrameControleur.MenuItemOuvrirEventHandler h = new  mainFrameControleur.MenuItemOuvrirEventHandler();
    }

}