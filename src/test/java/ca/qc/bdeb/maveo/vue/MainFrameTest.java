package ca.qc.bdeb.maveo.vue;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
        Assert.assertEquals(strTest, "Video");
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
        Assert.assertEquals(strTest, "Ouvrir");
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

    }

    @Test
    public void setMenuItemFile() throws Exception {


    }

    @Test
    public void getMenuItemFileOpen() throws Exception {

    }

    @Test
    public void setMenuItemFileOpen() throws Exception {

    }

    @Test
    public void getMenuItemEdit() throws Exception {

    }

    @Test
    public void setMenuItemEdit() throws Exception {

    }

    @Test
    public void getMenuItemHelp() throws Exception {

    }

    @Test
    public void setMenuItemHelp() throws Exception {

    }

    @Test
    public void getMenuItemFileClose() throws Exception {

    }

    @Test
    public void setMenuItemFileClose() throws Exception {

    }

    @Test
    public void getMenuItemEditDelete() throws Exception {

    }

    @Test
    public void setMenuItemEditDelete() throws Exception {

    }

    @Test
    public void getMenuItemHelpAbout() throws Exception {

    }

    @Test
    public void setMenuItemHelpAbout() throws Exception {

    }

    @Test
    public void getMenuItemOpenPlaylist() throws Exception {

    }

    @Test
    public void setMenuItemOpenPlaylist() throws Exception {

    }

    @Test
    public void getMenuItemAddToPlaylist() throws Exception {

    }

    @Test
    public void setMenuItemAddToPlaylist() throws Exception {

    }

    @Test
    public void getMenuItemSavePlaylist() throws Exception {

    }

    @Test
    public void setMenuItemSavePlaylist() throws Exception {

    }

    @Test
    public void getLblTxtVolume() throws Exception {

    }

    @Test
    public void setLblTxtVolume() throws Exception {

    }

    @Test
    public void getLblProgression() throws Exception {

    }

    @Test
    public void setLblProgression() throws Exception {

    }

    @Test
    public void getListviewPlaylist() throws Exception {

    }

    @Test
    public void setListviewPlaylist() throws Exception {

    }

    @Test
    public void setMenuItemHidePlaylist() throws Exception {

    }

    @Test
    public void getPlaylistPane() throws Exception {

    }

    @Test
    public void setPlaylistPane() throws Exception {

    }

    @Test
    public void getFenetre() throws Exception {

    }

    @Test
    public void getBtnJouerPause() throws Exception {

    }

    @Test
    public void getBtnArreter() throws Exception {

    }

    @Test
    public void getBoutonPrecedent() throws Exception {

    }

    @Test
    public void getBoutonSuivant() throws Exception {

    }

    @Test
    public void getPanelEcran() throws Exception {

    }

    @Test
    public void getSliderProgression() throws Exception {

    }

    @Test
    public void getSliderVolume() throws Exception {

    }

    @Test
    public void getListPlayList() throws Exception {

    }

    @Test
    public void getLblNomMedia() throws Exception {

    }

    @Test
    public void setLblNomMedia() throws Exception {

    }

    @Test
    public void setImageLblEcran() throws Exception {

    }

    @Test
    public void actualiseEcranPane() throws Exception {

    }

    @Test
    public void addEventHandlerBtnPlay() throws Exception {

    }

    @Test
    public void addEventHandlerBtnStop() throws Exception {

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
    public void addEventHandlerAddMediaInPlayList() throws Exception {

    }

    @Test
    public void addEventHandlerOpenPlaylist() throws Exception {

    }

    @Test
    public void addEventHandlerSavePlaylist() throws Exception {

    }

    @Test
    public void addEventHandlerPlayListSelected() throws Exception {

    }

    @Test
    public void addEventHandlerBoutonSuivant() throws Exception {

    }

    @Test
    public void addEventHandlerBoutonPrecedent() throws Exception {

    }

    @Test
    public void addEventHandlerCloseWindow() throws Exception {

    }

    @Test
    public void addEventHandlerHidePlaylist() throws Exception {

    }

}