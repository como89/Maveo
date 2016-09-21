package ca.qc.bdeb.maveo.vue;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;


/**
 * Created by Cedric Wu Tchan Ki on 2016-09-07.
 */
public class MainFrame {

    private static final String STR_MENU_ITEM_LECTURE = "Lecture";
    private static final String STR_MENU_ITEM_AUDIO = "Audio";
    private static final String STR_MENU_ITEM_VIDEO = "Vidéo";
    private static final String STR_MENU_ITEM_SOUSTITRES = "Sous-titres";
    private static final String STR_MENU_ITEM_OUTILS = "Outils";
    private static final String STR_MENU_ITEM_VUE = "Vue";
    private static final String STR_MENU_ITEM_AIDE = "Aide";

    public static final String STR_NOM_PROGRAMME = "M A V E O";

    public final String STR_BOUTON_PAUSE = "PAUSE";
    public final String STR_BOUTON_JOUER = "JOUER";
    private final String STR_BOUTON_STOP = "STOP";
    private final String STR_MENU_ITEM_MEDIA = "Média";
    private final String STR_MEDIA_OPTION_OUVRIR = "Ouvrir un fichier...";
    private static final String STR_MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers...";
    private static final String STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String STR_MEDIA_OPTION_QUITTER = "Quitter";

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

    public MainFrame() {


    }

    public Window getFenetre() {
        return fenetrePrincipale.getScene().getWindow();
    }

    public Button getBtnJouerPause() {
        return boutonPlayPause;
    }

    public void addEventHandlerBtnPlay(EventHandler<ActionEvent> actionEvent) {
        boutonPlayPause.setOnAction(actionEvent);
    }

    public void addEventHandlerOuvrirFichier(EventHandler<ActionEvent> actionEvent) {
        menuItemFileOpen.setOnAction(actionEvent);
    }

    // Listen for Slider value changes
    public void addChangeListenerSliderProgression(ChangeListener<Number> changeListener) {
        sliderProgression.valueProperty().addListener(changeListener);
    }

    public void addChangeListenerSliderVolume(ChangeListener<Number> changeListener) {
        sliderVolume.valueProperty().addListener(changeListener);
    }


    public Slider getSliderProgression() {
        return sliderProgression;
    }

    public Slider getSliderVolume() {
        return sliderVolume;
    }

}
