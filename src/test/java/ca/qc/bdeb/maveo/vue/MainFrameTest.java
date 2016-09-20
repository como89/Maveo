package ca.qc.bdeb.maveo.vue;

import org.junit.Assert;
import org.junit.Test;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;


/**
 * Created by Cedric Wu Tchan Ki on 2016-09-07.
 */
public class MainFrameTest {



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

    Window fenetreTest;


public void MainFrameTest (){

    fenetreTest = fenetrePrincipale.getScene().getWindow();

}

@Test
public void verifierPresenceFenetre() {

}

    @Test
    public void verifierBoutons() throws Exception {


    }

    @Test
    public void addEventHandlerBtnPlay() throws Exception {

    }

    @Test
    public void addEventHandlerOuvrirFichier() throws Exception {

    }

}