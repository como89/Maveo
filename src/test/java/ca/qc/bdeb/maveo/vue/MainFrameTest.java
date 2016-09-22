package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sun.applet.Main;

import static org.testfx.api.FxAssert.verifyThat;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.net.URL;

/**
 * Created by WuTchanKi on 2016-09-20.
 */
public class MainFrameTest extends ApplicationTest {
    private MainFrame testFrame;

    @Override
    public void start(Stage stage) throws Exception {
        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        testFrame = (MainFrame) loader.getController();
        Scene sceneTest = new Scene(page);
        stage.setScene(sceneTest);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
        testFrame.boutonPlayPause.setId("boutonPlay");
        //   stage.show();
    }

    @Test
    public void getFenetre() throws Exception {
        Assert.assertNotNull(testFrame.fenetrePrincipale.getScene().getWindow());
    }

    @Test
    public void getBtnJouerPause() throws Exception {

        Assert.assertNotNull(testFrame.boutonPlayPause);
        Assert.assertEquals(testFrame.boutonPlayPause.getText(), testFrame.STR_BOUTON_JOUER);
    }

    /**
     *
     * @throws Exception

    @Test
    public void addEventHandlerBtnPlay() throws Exception {
        Robot robot = new Robot();
        testFrame.boutonPlayPause.setLayoutX(25);
        testFrame.boutonPlayPause.setLayoutY(25);

        robot.mouseMove(27, 27);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(2000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        testFrame.boutonPlayPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                testFrame.boutonPlayPause.setText("TEST");
            }
        });




        Assert.assertEquals(testFrame.boutonPlayPause.getText(), "TEST");

    }
     */

    @Test
    public void addEventHandlerOuvrirFichier() throws Exception {


    }

    @Test
    public void addChangeListenerSliderProgression() throws Exception {

    }

    @Test
    public void addChangeListenerSliderVolume() throws Exception {

    }

    @Test
    public void getSliderProgression() throws Exception {
        Assert.assertNotNull(testFrame.getSliderProgression());

    }

    @Test
    public void getSliderVolume() throws Exception {

        Assert.assertNotNull(testFrame.getSliderVolume());


    }

    @Test
    public void verifierExistenceBoutonPlay() {
        Assert.assertNotNull(testFrame.boutonPlayPause);
    }

    @Test
    public void verifierBoutonPlayActive() {
        Assert.assertFalse(testFrame.boutonPlayPause.isDisable());
    }

    @Test
    public void verifierTexteBoutonPlay() {



        Assert.assertEquals(testFrame.boutonPlayPause.getText(), testFrame.STR_BOUTON_JOUER);
    }

    @Test
    public void verifierExistenceBoutonArreter() {
        Assert.assertNotNull(testFrame.boutonArreter);
    }







}