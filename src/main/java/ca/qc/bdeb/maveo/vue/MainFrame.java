package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.MainClass;
import ca.qc.bdeb.maveo.controleur.PlaylistControleur;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


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
    BorderPane boxBorder;

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
    MenuItem menuItemOpenPlaylist;

    @FXML
    MenuItem menuItemAddToPlaylist;

    @FXML
    MenuItem menuItemSavePlaylist;

    public MenuItem getMenuItemHidePlaylist() {
        return menuItemHidePlaylist;
    }

    public static String getStrMenuItemLecture() {
        return STR_MENU_ITEM_LECTURE;
    }

    public static String getStrMenuItemAudio() {
        return STR_MENU_ITEM_AUDIO;
    }

    public static String getStrMenuItemVideo() {
        return STR_MENU_ITEM_VIDEO;
    }

    public static String getStrMenuItemSoustitres() {
        return STR_MENU_ITEM_SOUSTITRES;
    }

    public static String getStrMenuItemOutils() {
        return STR_MENU_ITEM_OUTILS;
    }

    public static String getStrMenuItemVue() {
        return STR_MENU_ITEM_VUE;
    }

    public static String getStrMenuItemAide() {
        return STR_MENU_ITEM_AIDE;
    }

    public static String getStrNomProgramme() {
        return STR_NOM_PROGRAMME;
    }

    public String getSTR_MENU_ITEM_MEDIA() {
        return STR_MENU_ITEM_MEDIA;
    }

    public String getSTR_MEDIA_OPTION_OUVRIR() {
        return STR_MEDIA_OPTION_OUVRIR;
    }

    public static String getStrMediaOptionOuvrirplusieurs() {
        return STR_MEDIA_OPTION_OUVRIRPLUSIEURS;
    }

    public static String getStrMediaOptionEnregistrerlistedelecture() {
        return STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE;
    }

    public static String getStrMediaOptionQuitter() {
        return STR_MEDIA_OPTION_QUITTER;
    }

    public Button getBoutonPlayPause() {
        return boutonPlayPause;
    }

    public void setBoutonPlayPause(Button boutonPlayPause) {
        this.boutonPlayPause = boutonPlayPause;
    }

    public Button getBoutonArreter() {
        return boutonArreter;
    }

    public void setBoutonArreter(Button boutonArreter) {
        this.boutonArreter = boutonArreter;
    }

    public void setBoutonPrecedent(Button boutonPrecedent) {
        this.boutonPrecedent = boutonPrecedent;
    }

    public void setBoutonSuivant(Button boutonSuivant) {
        this.boutonSuivant = boutonSuivant;
    }

    public void setSliderProgression(Slider sliderProgression) {
        this.sliderProgression = sliderProgression;
    }

    public void setSliderVolume(Slider sliderVolume) {
        this.sliderVolume = sliderVolume;
    }

    public Pane getPanelControleur() {
        return panelControleur;
    }

    public void setPanelControleur(Pane panelControleur) {
        this.panelControleur = panelControleur;
    }

    public void setPanelEcran(Pane panelEcran) {
        this.panelEcran = panelEcran;
    }

    public BorderPane getFenetrePrincipale() {
        return fenetrePrincipale;
    }

    public void setFenetrePrincipale(BorderPane fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public MenuItem getMenuItemFile() {
        return menuItemFile;
    }

    public void setMenuItemFile(MenuItem menuItemFile) {
        this.menuItemFile = menuItemFile;
    }

    public MenuItem getMenuItemFileOpen() {
        return menuItemFileOpen;
    }

    public void setMenuItemFileOpen(MenuItem menuItemFileOpen) {
        this.menuItemFileOpen = menuItemFileOpen;
    }

    public MenuItem getMenuItemEdit() {
        return menuItemEdit;
    }

    public void setMenuItemEdit(MenuItem menuItemEdit) {
        this.menuItemEdit = menuItemEdit;
    }

    public MenuItem getMenuItemHelp() {
        return menuItemHelp;
    }

    public void setMenuItemHelp(MenuItem menuItemHelp) {
        this.menuItemHelp = menuItemHelp;
    }

    public MenuItem getMenuItemFileClose() {
        return menuItemFileClose;
    }

    public void setMenuItemFileClose(MenuItem menuItemFileClose) {
        this.menuItemFileClose = menuItemFileClose;
    }

    public MenuItem getMenuItemEditDelete() {
        return menuItemEditDelete;
    }

    public void setMenuItemEditDelete(MenuItem menuItemEditDelete) {
        this.menuItemEditDelete = menuItemEditDelete;
    }

    public MenuItem getMenuItemHelpAbout() {
        return menuItemHelpAbout;
    }

    public void setMenuItemHelpAbout(MenuItem menuItemHelpAbout) {
        this.menuItemHelpAbout = menuItemHelpAbout;
    }

    public MenuItem getMenuItemOpenPlaylist() {
        return menuItemOpenPlaylist;
    }

    public void setMenuItemOpenPlaylist(MenuItem menuItemOpenPlaylist) {
        this.menuItemOpenPlaylist = menuItemOpenPlaylist;
    }

    public MenuItem getMenuItemAddToPlaylist() {
        return menuItemAddToPlaylist;
    }

    public void setMenuItemAddToPlaylist(MenuItem menuItemAddToPlaylist) {
        this.menuItemAddToPlaylist = menuItemAddToPlaylist;
    }

    public MenuItem getMenuItemSavePlaylist() {
        return menuItemSavePlaylist;
    }

    public void setMenuItemSavePlaylist(MenuItem menuItemSavePlaylist) {
        this.menuItemSavePlaylist = menuItemSavePlaylist;
    }

    public Label getLblTxtVolume() {
        return lblTxtVolume;
    }

    public void setLblTxtVolume(Label lblTxtVolume) {
        this.lblTxtVolume = lblTxtVolume;
    }

    public Label getLblProgression() {
        return lblProgression;
    }

    public void setLblProgression(Label lblProgression) {
        this.lblProgression = lblProgression;
    }

    public ListView<String> getListviewPlaylist() {
        return listviewPlaylist;
    }

    public void setListviewPlaylist(ListView<String> listviewPlaylist) {
        this.listviewPlaylist = listviewPlaylist;
    }

    public void setMenuItemHidePlaylist(MenuItem menuItemHidePlaylist) {
        this.menuItemHidePlaylist = menuItemHidePlaylist;
    }

    public void setBoxBorder(BorderPane boxBorder) {
        this.boxBorder.setPrefSize(boxBorder.getPrefWidth(), boxBorder.getPrefHeight());
        this.boxBorder.setCenter(boxBorder.getCenter());
        this.boxBorder.setLeft(boxBorder.getLeft());
        this.boxBorder.setRight(boxBorder.getRight());
        this.boxBorder.getStylesheets().addAll(boxBorder.getStylesheets());
    }

    @FXML
    MenuItem menuItemHidePlaylist;

    @FXML
    Label lblTxtVolume;

    @FXML
    Label lblNomMedia;

    @FXML
    Label lblProgression;

    public TitledPane getPlaylistPane() {
        return playlistPane;
    }

    public void setPlaylistPane(TitledPane playlistPane) {
        this.playlistPane = playlistPane;
    }

    @FXML
    TitledPane playlistPane;

    @FXML
    ListView<String> listviewPlaylist;

    public MainFrame() {

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

    public Pane getPanelEcran() {
        return panelEcran;
    }

    public Slider getSliderProgression() {
        return sliderProgression;
    }

    public Slider getSliderVolume() {
        return sliderVolume;
    }

    public ListView<String> getListPlayList() {
        return listviewPlaylist;
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

    public void actualiseEcranPane(ImageView imageVideo) {

        if (imageVideo != null) {
            panelEcran.getStyleClass().remove("paneEcranMusique");
            panelEcran.getStyleClass().add("paneEcranVideo");
            panelEcran.getChildren().clear();
            panelEcran.getChildren().add(imageVideo);
            panelEcran.getChildren().add(playlistPane);
        } else {
            panelEcran.getStyleClass().remove("paneEcranVideo");
            panelEcran.getStyleClass().add("paneEcranMusique");
            panelEcran.getChildren().clear();
            panelEcran.getChildren().add(lblNomMedia);
            panelEcran.getChildren().add(playlistPane);
            ImageView imageView = new ImageView(MainClass.LOGO_SOFTWARE);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            lblNomMedia.setGraphic(imageView);
        }

        fenetrePrincipale.setCenter(panelEcran);
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

    public void addEventHandlerAddMediaInPlayList(EventHandler<ActionEvent> menuAddToPlaylistEventHandler) {
        menuItemAddToPlaylist.setOnAction(menuAddToPlaylistEventHandler);
    }

    public void addEventHandlerOpenPlaylist(EventHandler<ActionEvent> OpenPlaylistEventHandler) {
        menuItemOpenPlaylist.setOnAction(OpenPlaylistEventHandler);
    }

    public void addEventHandlerSavePlaylist(EventHandler<ActionEvent> SavePlaylistEventHandler) {
        menuItemSavePlaylist.setOnAction(SavePlaylistEventHandler);
    }

    public void addEventHandlerPlayListSelected(EventHandler<MouseEvent> playListSelectedEventHandler) {
        listviewPlaylist.setOnMouseClicked(playListSelectedEventHandler);
    }

    public void addEventHandlerBoutonSuivant(EventHandler<ActionEvent> boutonSuivantActionEvent) {
        boutonSuivant.setOnAction(boutonSuivantActionEvent);
    }

    public void addEventHandlerBoutonPrecedent(EventHandler<ActionEvent> boutonPrecedentActionEvent) {
        boutonPrecedent.setOnAction(boutonPrecedentActionEvent);
    }

    public void addEventHandlerHidePlaylist(EventHandler<ActionEvent> MenuHidePlaylistEventHandler ) {
        menuItemHidePlaylist.setOnAction(MenuHidePlaylistEventHandler);
    }
}
