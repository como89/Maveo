package ca.qc.bdeb.maveo.vue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by WuTchanKi on 2016-09-20.
 */
public class MainFrameTest extends ApplicationTest {

    @FXML
    Button boutonPlayPause;

    @FXML
    Button boutonArreter;

    @FXML
    Button boutonPrecedent;

    @FXML
    Button boutonSuivant;

    @FXML
    Slider sliderProgression;

    @FXML
    Slider sliderVolume;

    @FXML
    Pane panelControleur;

    @FXML
    Pane panelEcran;

    @FXML
    BorderPane fenetrePrincipale;

    @FXML
    MenuBar menuBar;

    @FXML
    MenuItem menuItemFile;

    @FXML
    MenuItem menuItemFileOpen;

    @FXML
    MenuItem menuItemEdit;

    @FXML
    MenuItem menuItemHelp;

    @FXML
    MenuItem menuItemFileClose;

    @FXML
    MenuItem menuItemEditDelete;

    @FXML
    MenuItem menuItemHelpAbout;

    @FXML
    Label lblTxtVolume;

    @FXML
    Label lblNomMedia;

    @FXML
    Label lblProgression;


    @Override
    public void start(Stage stage) throws Exception {
        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        Scene sceneTest = new Scene(page);
        stage.setScene(sceneTest);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
        stage.show();
    }

    /**
     * Test qui verifie que le bouton existe
     */
    @Test
    public void verifierExistenceBoutonPlay(){

    }

    /**
     * Test qui verifie que le bouton change de texte quand il est clique
     */
    @Test
    public void verifierTexteBoutonPlay(){

    }
}