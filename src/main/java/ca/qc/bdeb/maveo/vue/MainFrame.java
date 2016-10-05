package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import ca.qc.bdeb.maveo.modele.Fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.Playlist;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireVideo;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import java.io.File;


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

    private final String STR_MENU_ITEM_MEDIA = "Média";
    private final String STR_MEDIA_OPTION_OUVRIR = "Ouvrir un fichier...";
    private static final String STR_MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers...";
    private static final String STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String STR_MEDIA_OPTION_QUITTER = "Quitter";

    @FXML
    Button boutonPlayPause;

    @FXML
    Image imageBtnPlay, imageBtnStop, imageBtnSuivant, imageBtnPrecedent, imageBtnPause;

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

    BorderPane videoPane;

    public MainFrame() {


    }


    private void initialiserBoutons() {



    }

    public Window getFenetre() {
        return fenetrePrincipale.getScene().getWindow();
    }

    public Button getBtnJouerPause() {
        return boutonPlayPause;
    }

    public Button getBtnArreter() {
        return boutonArreter;

    }

    public Button getBoutonPrecedent() {
        return boutonPrecedent;
    }

    public Button getBoutonSuivant() {
        return boutonSuivant;
    }

    public Pane getVideoPane() {
        Pane pane = (Pane) fenetrePrincipale.getScene().lookup("#panelEcranFenetre");
        return pane;
    }



    public void addEventHandlerBtnPlay(EventHandler<ActionEvent> actionEvent) {
        boutonPlayPause.setOnAction(actionEvent);
    }

    public void addEventHandlerBtnStop(EventHandler<ActionEvent> actionEvent) {
        boutonArreter.setOnAction(actionEvent);
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

    public Label getLblNomMedia() {
        return lblNomMedia;
    }

    public void setLblNomMedia(Label lblNomMedia) {
        this.lblNomMedia = lblNomMedia;
    }

    public void setImageLblEcran(Image image) {
        lblNomMedia.setGraphic(new ImageView(image));

    }


    public void openFilePlaylist(ActionEvent actionEvent) {
        FileOpener opener = new FileOpener();
      File fichier =  opener.activerOuvertureFichier(this.getFenetre());
        if (fichier != null) {

            String path = fichier.getAbsolutePath();

            //On doit maintenant lire le fichier json et inserer les infos du fichier dans un tableau

            //infos = recupererInfosPlaylist();
            //afficherInfosPlaylist(infos);






        }
    }

    public void ajouterALaPlaylist(){

    }
}
