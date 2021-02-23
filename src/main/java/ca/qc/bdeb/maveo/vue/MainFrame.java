package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.modele.tags.Tags;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;


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

    private static final String STR_MENU_ITEM_MEDIA = "Média";
    private static final String STR_MEDIA_OPTION_OUVRIR = "Ouvrir un fichier...";
    private static final String STR_MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers...";
    private static final String STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String STR_MEDIA_OPTION_QUITTER = "Quitter";

    public static final String DEFAULT_TEMPS_ECOULE = "-- : --";
    public static final String DEFAULT_TEMPS_TOTAL = "-- : -- / -- : --";

    public static final int VIDEO_VIEW = 0;
    public static final int MUSIC_VIEW = 1;

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
    ProgressBar traitProgress;

    @FXML
    Slider sliderVolume;

    @FXML
    Pane panelControleur;

    @FXML
    BorderPane boxBorder;

    @FXML
    BorderPane fenetrePrincipale;

    @FXML
    Pane paneEcran;

    @FXML
    MenuBar menuBar;

    @FXML
    MenuItem menuItemFile;

    @FXML
    MenuItem menuItemFileOpen;

    @FXML
    MenuItem menuItemHelp;

    @FXML
    MenuItem menuItemExit;

    @FXML
    MenuItem menuItemHelpAbout;

    @FXML
    MenuItem menuItemOpenPlaylist;

    @FXML
    MenuItem menuItemAddToPlaylist;

    @FXML
    MenuItem menuItemSavePlaylist;

    @FXML
    TitledPane playlistPane;

    @FXML
    ListView<String> listviewPlaylist;

    @FXML
    MenuItem menuItemHidePlaylist;

    @FXML
    StackPane stackPane;

    @FXML
    Label labelTempsEcoule;

    @FXML
    MenuItem menuItemMediaSousTitres;

    @FXML
    MenuItem menuItemMediaChargerSousTitres;

    @FXML
    Label labelTempsTotal;

    @FXML
    ProgressBar progressVolume;

    @FXML
    MenuItem menuItemMediaLyric;

    @FXML
    MenuItem menuItemMediaOpenLyric;

    @FXML
    MenuItem menuItemMediaSaveLyric;

    Pane ecranVideo;

    Pane ecranMusique;

    public MainFrame() {
        ecranVideo = generateEcran(getClass().getClassLoader().getResource("EcranVideo.fxml"));
        ecranMusique = generateEcran(getClass().getClassLoader().getResource("EcranMusique.fxml"));
    }

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

    public MenuItem getMenuItemHelp() {
        return menuItemHelp;
    }

    public void setMenuItemHelp(MenuItem menuItemHelp) {
        this.menuItemHelp = menuItemHelp;
    }

    public MenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public void setMenuItemExit(MenuItem menuItemExit) {
        this.menuItemExit = menuItemExit;
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

    public ProgressBar getTraitProgress() {
        return traitProgress;
    }

    public ProgressBar getProgressVolume() {
        return progressVolume;
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

    public ScrollPane getScrollPane() {
        return (ScrollPane) ecranMusique.lookup("#scrollText");
    }

    public Text getLyricTitle() {
        return (Text) ecranMusique.lookup("#lyricTitle");
    }

    public Text getLyricText() {
        return (Text) ecranMusique.lookup("#lyricText");
    }

    public ImageView getAlbumView() {
        return (ImageView) ecranMusique.lookup("#albumImage");
    }

    public Label getLabelTempsEcoule() {
        return labelTempsEcoule;
    }

    public void setLabelTempsEcoule(Label labelTempsEcoule) {
        this.labelTempsEcoule = labelTempsEcoule;
    }

    public Label getLabelTempsTotal() {
        return labelTempsTotal;
    }

    public void setLabelTempsTotal(Label labelTempsTotal) {
        this.labelTempsTotal = labelTempsTotal;
    }

    public TitledPane getPlaylistPane() {
        return playlistPane;
    }

    public void setPlaylistPane(TitledPane playlistPane) {
        this.playlistPane = playlistPane;
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

    public Slider getSliderProgression() {
        return sliderProgression;
    }

    public Slider getSliderVolume() {
        return sliderVolume;
    }

    public ListView<String> getListPlayList() {
        return listviewPlaylist;
    }

    public ImageView getVideoView() {
        return (ImageView) ecranVideo.lookup("#videoView");
    }

    public Pane getPaneEcran() {
        return paneEcran;
    }

    /**
     * Méthode qui permet de set le border avec ses styles et ses composants.
     * @param boxBorder - Le border à modifier.
     */
    public void setBoxBorder(BorderPane boxBorder) {
        this.boxBorder.setPrefSize(boxBorder.getPrefWidth(), boxBorder.getPrefHeight());
        this.boxBorder.setCenter(boxBorder.getCenter());
        this.boxBorder.setLeft(boxBorder.getLeft());
        this.boxBorder.setRight(boxBorder.getRight());
        this.boxBorder.getStylesheets().addAll(boxBorder.getStylesheets());
    }

    /**
     * Méthode qui permet de switch entre les diverses interface du programme.
     * Soit VIDEO_VIEW OU MUSIC_VIEW.
     * @param typeView - On lui donne en paramètre l'id du view qu'on veut afficher.
     */
    public void switchView(int typeView) {
        stackPane.getChildren().clear();
        Pane pane = null;
        switch (typeView) {
            case VIDEO_VIEW:
                pane = ecranVideo;
                break;
            case MUSIC_VIEW:
                pane = ecranMusique;
                break;
        }
        stackPane.getChildren().add(pane);
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

    public void addEventHandlerHidePlaylist(EventHandler<ActionEvent> MenuHidePlaylistEventHandler) {
        menuItemHidePlaylist.setOnAction(MenuHidePlaylistEventHandler);
    }

    public void addEventHandlerMenuItemMediaOpenLyric(EventHandler<ActionEvent> MenuItemMediaOpenLyricEventHandler) {
        menuItemMediaOpenLyric.setOnAction(MenuItemMediaOpenLyricEventHandler);
    }

    public void addEventHandlerMenuItemMediaSaveLyric(EventHandler<ActionEvent> MenuItemMediaSaveLyricPlaylistEventHandler) {
        menuItemMediaSaveLyric.setOnAction(MenuItemMediaSaveLyricPlaylistEventHandler);
    }

    public void addEventHandlerExitItem(EventHandler<ActionEvent> exitItemEvent) {
        menuItemExit.setOnAction(exitItemEvent);
    }

    public void addEventHandlerAboutItem(EventHandler<ActionEvent> aboutItemEvent) {
        menuItemHelpAbout.setOnAction(aboutItemEvent);
    }

    public MenuItem getMenuItemMediaSousTitres() {
        return menuItemMediaSousTitres;
    }

    public void setMenuItemMediaSousTitres(MenuItem menuItemMediaSousTitres) {
        this.menuItemMediaSousTitres = menuItemMediaSousTitres;
    }

    public void addEventHandlerMenuItemMediaSousTitres(EventHandler<ActionEvent> MenuItemMediaSousTitresEventHandler) {
        menuItemMediaSousTitres.setOnAction(MenuItemMediaSousTitresEventHandler);
    }

    public void addEventHandlerMenuItemMediaChargerSousTitres(EventHandler<ActionEvent> MenuItemMediaChargerSousTitresEventHandler) {
        menuItemMediaChargerSousTitres.setOnAction(MenuItemMediaChargerSousTitresEventHandler);
    }
    /**
     * Méthode qui génère un panel écran pour l'écran envoyé en paramètre.
     *
     * @return Retourne l'écran générer avec ses composants, retourne null si l'écran ne peut pas être généré.
     */
    private Pane generateEcran(URL ressource) {
        Pane panel = null;
        try {
            if (ressource != null) {
                panel = FXMLLoader.load(ressource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panel;
    }
}
