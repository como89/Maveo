package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import ca.qc.bdeb.maveo.modele.FileOpener;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
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
import java.awt.event.ActionListener;
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

        stage.show();
    }

    @Test
    public void getFenetre() throws Exception {
        Assert.assertNotNull(testFrame.fenetrePrincipale.getScene().getWindow());
    }

    @Test
    public void getBtnJouerPause() throws Exception {

        Assert.assertNotNull(testFrame.boutonPlayPause);
      //  Assert.assertEquals(testFrame.boutonPlayPause.getText(), testFrame.STR_BOUTON_JOUER);
    }


    @Test
    public void addEventHandlerBtnPlay() throws Exception {
        //    testFrame.boutonPlayPause.setId("btnPlay");
        verifyThat("#boutonPlayPause", NodeMatchers.isNotNull());
        verifyThat("#boutonPlayPause", NodeMatchers.hasText("JOUER"));


//testFrame.boutonPlayPause.setDisable(false);


        testFrame.boutonPlayPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                testFrame.boutonPlayPause.setText("TEST");


            }
        });

        clickOn(testFrame.boutonPlayPause);
        Thread.sleep(200);
        System.out.println("tttttttt " + testFrame.boutonPlayPause.getText());
        Assert.assertEquals(testFrame.boutonPlayPause.getText(), "TEST");


    }


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


        //Assert.assertEquals(testFrame.boutonPlayPause.getText(), testFrame.STR_BOUTON_JOUER);
    }

    @Test
    public void verifierExistenceBoutonArreter() {
        Assert.assertNotNull(testFrame.boutonArreter);
    }

    @Test
    public void setImageLblEcran() throws Exception {
        Label lblTest = new Label();
       Image photoAlbum  = new Image("file:res/noart.png");
        lblTest.setGraphic(new ImageView(photoAlbum));

        Assert.assertNotNull(lblTest.getGraphic());


    }








}