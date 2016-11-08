package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.modele.playlist.Playlist;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by WuTchanKi on 2016-10-24.
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
    public void getMenuItemHidePlaylist() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemHidePlaylist();
        Assert.assertNotNull(menuItemTest);


    }

    @Test
    public void getStrMenuItemLecture() throws Exception {
        String strTest = testFrame.getStrMenuItemLecture();
        Assert.assertEquals(strTest, "Lecture");

    }

    @Test
    public void getStrMenuItemAudio() throws Exception {
        String strTest = testFrame.getStrMenuItemAudio();
        Assert.assertEquals(strTest, "Audio");
    }

    @Test
    public void getStrMenuItemVideo() throws Exception {
        String strTest = testFrame.getStrMenuItemVideo();
        Assert.assertEquals(strTest, "Vidéo");
    }

    @Test
    public void getStrMenuItemSoustitres() throws Exception {
        String strTest = testFrame.getStrMenuItemSoustitres();
        Assert.assertEquals(strTest, "Sous-titres");
    }

    @Test
    public void getStrMenuItemOutils() throws Exception {
        String strTest = testFrame.getStrMenuItemOutils();
        Assert.assertEquals(strTest, "Outils");
    }

    @Test
    public void getStrMenuItemVue() throws Exception {
        String strTest = testFrame.getStrMenuItemVue();
        Assert.assertEquals(strTest, "Vue");
    }

    @Test
    public void getStrMenuItemAide() throws Exception {
        String strTest = testFrame.getStrMenuItemAide();
        Assert.assertEquals(strTest, "Aide");
    }

    @Test
    public void getStrNomProgramme() throws Exception {
        String strTest = testFrame.getStrNomProgramme();
        Assert.assertEquals(strTest, "M A V E O");
    }

    @Test
    public void getSTR_MENU_ITEM_MEDIA() throws Exception {
        String strTest = testFrame.getSTR_MENU_ITEM_MEDIA();
        Assert.assertEquals(strTest, "Média");
    }

    @Test
    public void getSTR_MEDIA_OPTION_OUVRIR() throws Exception {
        String strTest = testFrame.getSTR_MEDIA_OPTION_OUVRIR();
        Assert.assertEquals(strTest, "Ouvrir un fichier...");
    }

    @Test
    public void getStrMediaOptionOuvrirplusieurs() throws Exception {
        String strTest = testFrame.getStrMediaOptionOuvrirplusieurs();
        Assert.assertEquals(strTest, "Ouvrir plusieurs fichiers...");
    }

    @Test
    public void getStrMediaOptionEnregistrerlistedelecture() throws Exception {
        String strTest = testFrame.getStrMediaOptionEnregistrerlistedelecture();
        Assert.assertEquals(strTest, "Enregistrer Liste de lecture");
    }

    @Test
    public void getStrMediaOptionQuitter() throws Exception {
        String strTest = testFrame.getStrMediaOptionQuitter();
        Assert.assertEquals(strTest, "Quitter");
    }

    @Test
    public void getBoutonPlayPause() throws Exception {
        Button boutonTest = testFrame.getBoutonPlayPause();
        ObservableList<String> testStyleCLass = boutonTest.getStyleClass();
        Assert.assertEquals(testStyleCLass, boutonTest.getStyleClass());

    }

    @Test
    public void setBoutonPlayPause() throws Exception {
        Button boutonMock = new Button("Test");
        testFrame.setBoutonPlayPause(boutonMock);
        Assert.assertEquals(boutonMock.getText(), testFrame.getBoutonPlayPause().getText());


    }

    @Test
    public void getBoutonArreter() throws Exception {
        Button boutonTest = testFrame.getBoutonArreter();
        ObservableList<String> testStyleCLass = boutonTest.getStyleClass();
        Assert.assertEquals(testStyleCLass, boutonTest.getStyleClass());

    }

    @Test
    public void setBoutonArreter() throws Exception {
        Button boutonMock = new Button("Test");
        testFrame.setBoutonArreter(boutonMock);
        Assert.assertEquals(boutonMock.getText(), testFrame.getBtnArreter().getText());

    }

    @Test
    public void setBoutonPrecedent() throws Exception {
        Button boutonMock = new Button("Test");
        testFrame.setBoutonPrecedent(boutonMock);
        Assert.assertEquals(boutonMock.getText(), testFrame.getBoutonPrecedent().getText());
    }

    @Test
    public void setBoutonSuivant() throws Exception {
        Button boutonMock = new Button("Test");
        testFrame.setBoutonSuivant(boutonMock);
        Assert.assertEquals(boutonMock.getText(), testFrame.getBoutonSuivant().getText());
    }

    @Test
    public void setSliderProgression() throws Exception {
        Slider sliderTest = testFrame.getSliderProgression();
        sliderTest.setVisible(true);
        testFrame.setSliderProgression(sliderTest);
        Assert.assertTrue(testFrame.getSliderProgression().isVisible());

    }

    @Test
    public void setSliderVolume() throws Exception {
        Slider sliderTest = testFrame.getSliderVolume();
        sliderTest.setVisible(true);
        testFrame.setSliderVolume(sliderTest);
        Assert.assertTrue(testFrame.getSliderVolume().isVisible());

    }

    @Test
    public void getPanelControleur() throws Exception {
        Pane panelTest = testFrame.getPanelControleur();
        ObservableList<String> testStyleCLass = panelTest.getStyleClass();
        Assert.assertEquals(testStyleCLass, testFrame.getPanelControleur().getStyleClass());

    }

    @Test
    public void setPanelControleur() throws Exception {
        Pane panelTest = new Pane();
        panelTest.setBackground(Background.EMPTY);
        testFrame.setPanelControleur(panelTest);
        Assert.assertEquals(testFrame.getPanelControleur().getBackground(), panelTest.getBackground());
    }

    @Test
    public void setPanelEcran() throws Exception {
        Pane panelTest = new Pane();
        panelTest.setBackground(Background.EMPTY);
        testFrame.setPanelEcran(panelTest);
        Assert.assertEquals(testFrame.getPanelEcran().getBackground(), panelTest.getBackground());
    }

    @Test
    public void getFenetrePrincipale() throws Exception {
        BorderPane borderPaneTest = testFrame.getFenetrePrincipale();
        Assert.assertEquals(borderPaneTest, testFrame.getFenetrePrincipale());


    }

    @Test
    public void setFenetrePrincipale() throws Exception {
        BorderPane borderPaneTest = new BorderPane();
        borderPaneTest.setVisible(false);
        testFrame.setFenetrePrincipale(borderPaneTest);
        Assert.assertFalse(testFrame.getFenetrePrincipale().isVisible());

    }

    @Test
    public void getMenuBar() throws Exception {
        MenuBar menuBarTest = testFrame.getMenuBar();
        ObservableList<Menu> menus = menuBarTest.getMenus();
        menus.get(0);
        Assert.assertEquals(menus.get(0), testFrame.getMenuBar().getMenus().get(0));

    }

    @Test
    public void setMenuBar() throws Exception {
        MenuBar menuBarTest = new MenuBar();
        menuBarTest.setId("MENUTEST");
        testFrame.setMenuBar(menuBarTest);
        Assert.assertEquals(testFrame.getMenuBar().getId(), "MENUTEST");

    }

    @Test
    public void getMenuItemFile() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemFile();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(0));
    }

    @Test
    public void setMenuItemFile() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemFile(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemFile());

    }

    @Test
    public void getMenuItemFileOpen() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemFileOpen();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(0).getItems().get(0));
    }

    @Test
    public void setMenuItemFileOpen() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemFileOpen(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemFileOpen());
    }

    @Test
    public void getMenuItemEdit() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemEdit();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(2));
    }

    @Test
    public void setMenuItemEdit() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemEdit(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemEdit());
    }

    @Test
    public void getMenuItemHelp() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemHelp();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(3));
    }

    @Test
    public void setMenuItemHelp() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemHelp(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemHelp());

    }

    @Test
    public void getMenuItemFileClose() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemFileClose();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(0).getItems().get(1));
    }

    @Test
    public void setMenuItemFileClose() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemFileClose(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemFileClose());
    }

    @Test
    public void getMenuItemEditDelete() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemEditDelete();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(2).getItems().get(0));
    }

    @Test
    public void setMenuItemEditDelete() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemEditDelete(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemEditDelete());
    }

    @Test
    public void getMenuItemHelpAbout() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemHelpAbout();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(3).getItems().get(0));
    }

    @Test
    public void setMenuItemHelpAbout() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemHelpAbout(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemHelpAbout());

    }

    @Test
    public void getMenuItemOpenPlaylist() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemOpenPlaylist();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(1).getItems().get(0));
    }

    @Test
    public void setMenuItemOpenPlaylist() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemOpenPlaylist(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemOpenPlaylist());

    }

    @Test
    public void getMenuItemAddToPlaylist() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemAddToPlaylist();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(1).getItems().get(1));
    }

    @Test
    public void setMenuItemAddToPlaylist() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemAddToPlaylist(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemAddToPlaylist());

    }

    @Test
    public void getMenuItemSavePlaylist() throws Exception {
        MenuItem menuItemTest = testFrame.getMenuItemSavePlaylist();
        Assert.assertEquals(menuItemTest, testFrame.getMenuBar().getMenus().get(1).getItems().get(2));
    }

    @Test
    public void setMenuItemSavePlaylist() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemSavePlaylist(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemSavePlaylist());

    }

    @Test
    public void getLblTxtVolume() throws Exception {
        Label labelTest = testFrame.getLblTxtVolume();
        Assert.assertEquals(labelTest, testFrame.getLblTxtVolume());

    }

    @Test
    public void setLblTxtVolume() throws Exception {
        Label labelTest = new Label("TEST");
        testFrame.setLblTxtVolume(labelTest);
        Assert.assertEquals(labelTest, testFrame.getLblTxtVolume());

    }

    @Test
    public void getListviewPlaylist() throws Exception {
        ListView listViewTest = testFrame.getListviewPlaylist();
        Assert.assertEquals(listViewTest, testFrame.getListviewPlaylist());

    }

    @Test
    public void setListviewPlaylist() throws Exception {
        ListView listViewTest = new ListView(null);
        testFrame.setListviewPlaylist(listViewTest);

    }

    @Test
    public void setMenuItemHidePlaylist() throws Exception {
        MenuItem menuItemTest = new MenuItem("LISA");
        testFrame.setMenuItemHidePlaylist(menuItemTest);
        Assert.assertEquals(menuItemTest, testFrame.getMenuItemHidePlaylist());
    }

    @Test
    public void getPlaylistPane() throws Exception {
        TitledPane playlistPane = testFrame.getPlaylistPane();
        Assert.assertEquals(playlistPane, testFrame.getPlaylistPane());
    }

    @Test
    public void setPlaylistPane() throws Exception {
        TitledPane titledPaneTest = new TitledPane("TESTING", null);
        testFrame.setPlaylistPane(titledPaneTest);
        Assert.assertEquals(titledPaneTest, testFrame.getPlaylistPane());

    }

    @Test
    public void getFenetre() throws Exception {
        Window windowTest = testFrame.getFenetre();
        Assert.assertNotNull(windowTest);

    }

    @Test
    public void getBtnJouerPause() throws Exception {
        Button boutonTest = testFrame.getBoutonPlayPause();
        Assert.assertEquals(boutonTest, testFrame.getBoutonPlayPause());

    }

    @Test
    public void getBtnArreter() throws Exception {
        Button boutonTest = testFrame.getBtnArreter();
        Assert.assertEquals(boutonTest, testFrame.getBoutonArreter());
    }

    @Test
    public void getBoutonPrecedent() throws Exception {
        Button boutonTest = testFrame.getBoutonPrecedent();
        Assert.assertEquals(boutonTest, testFrame.getBoutonPrecedent());
    }

    @Test
    public void getBoutonSuivant() throws Exception {
        Button boutonTest = testFrame.getBoutonSuivant();
        Assert.assertEquals(boutonTest, testFrame.getBoutonSuivant());
    }

    @Test
    public void getPanelEcran() throws Exception {
        Pane panelTest = testFrame.getPanelEcran();
        Assert.assertEquals(panelTest, testFrame.getPanelEcran());
    }

    @Test
    public void getSliderProgression() throws Exception {
        Slider sliderTest = testFrame.getSliderProgression();
        Assert.assertEquals(sliderTest, testFrame.getSliderProgression());

    }

    @Test
    public void getSliderVolume() throws Exception {

        Slider sliderTest = testFrame.getSliderVolume();
        Assert.assertEquals(sliderTest, testFrame.getSliderVolume());
    }

    @Test
    public void getListPlayList() throws Exception {

    }

    @Test
    public void getLblNomMedia() throws Exception {
        Label labelTest = testFrame.getLblNomMedia();
        Assert.assertEquals(labelTest, testFrame.getLblNomMedia());
    }

    @Test
    public void setLblNomMedia() throws Exception {
        Label labelTest = new Label("TEST");
        testFrame.setLblNomMedia(labelTest);
        Assert.assertEquals(labelTest, testFrame.getLblNomMedia());
    }

    @Test
    public void getLabelTempsEcoule(){
        Label labelTest = testFrame.getLabelTempsEcoule();
        Assert.assertEquals(labelTest ,testFrame.getLabelTempsEcoule() );
    }

    @Test
    public void getLabelTempsTotal(){
        Label labelTest = testFrame.getLabelTempsTotal();
        Assert.assertEquals(labelTest ,testFrame.getLabelTempsTotal() );
    }

    // @Test
    public void setImageLblEcran() throws Exception {
//        Image image = new Image("sample.jpg");
        //     testFrame.setImageLblEcran(image);
       // Assert.assertNotNull();

    }

    //  @Test
    public void actualiseEcranPane() throws Exception {

    }

    //  @Test
    public void addEventHandlerBtnPlay() throws Exception {

    }

    //  @Test
    public void addEventHandlerBtnStop() throws Exception {

    }

    //  @Test
    public void addEventHandlerOuvrirFichier() throws Exception {

    }

    //  @Test
    public void addChangeListenerSliderProgression() throws Exception {

    }

    //  @Test
    public void addChangeListenerSliderVolume() throws Exception {

    }

    // @Test
    public void addEventHandlerAddMediaInPlayList() throws Exception {

    }

    //   @Test
    public void addEventHandlerOpenPlaylist() throws Exception {

    }

    //  @Test
    public void addEventHandlerSavePlaylist() throws Exception {

    }

    //  @Test
    public void addEventHandlerPlayListSelected() throws Exception {

    }

    //  @Test
    public void addEventHandlerBoutonSuivant() throws Exception {

    }

    //    @Test
    public void addEventHandlerBoutonPrecedent() throws Exception {

    }

    //   @Test
    public void addEventHandlerCloseWindow() throws Exception {

    }

    //  @Test
    public void addEventHandlerHidePlaylist() throws Exception {

    }

}