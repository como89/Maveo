package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by C A T A on 2016-09-12.
 */
public class MainFrameControleurTest extends ApplicationTest {

    MainFrameControleur mainFrameControleur;
    MainFrame mainFrame;
    FileOpener fileOpener;
    float floatTest;


    private void setTestingFloatNumber(float newValue) {
        floatTest = newValue;
    }

    private float getTestingFloatNumber() {
        return floatTest;
    }

    @Override
    public void start(Stage stage) throws Exception {

        URL ressource = getClass().getClassLoader().getResource("MainStage.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = (MainFrame) loader.getController();
        Scene sceneTest = new Scene(page);
        stage.setScene(sceneTest);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);

        stage.show();

    }

    @Test
    public void ajouterMainFrameTest() throws Exception {

        mainFrameControleur = new MainFrameControleur();
        mainFrameControleur.ajouterMainFrame(mainFrame);

        assertEquals(mainFrameControleur.mainFrame, mainFrame);
    }

    @Test
    public void ajouterFileOpenerTest() {

        mainFrameControleur = new MainFrameControleur();
        fileOpener = new FileOpener();
        mainFrameControleur.ajouterFileOpener(fileOpener);

        assertEquals(mainFrameControleur.fileOpener, fileOpener);
    }

    @Test
    public void fixerSliderPositionTest() {

        double positionSliderTest = -1;
        mainFrameControleur = new MainFrameControleur();

        positionSliderTest = mainFrame.getSliderProgression().getMax() / Math.PI;
        mainFrame.getSliderProgression().setValue(positionSliderTest);
        Assert.assertTrue(mainFrame.getSliderProgression().getValue() == positionSliderTest);
    }

    @Test
    public void fixerVolumePositionTest() {

        double positionSliderTest = -1;

        mainFrameControleur = new MainFrameControleur();

        positionSliderTest = mainFrame.getSliderVolume().getMax() / Math.PI;

        mainFrame.getSliderVolume().setValue(positionSliderTest);

        Assert.assertTrue(mainFrame.getSliderVolume().getValue() == positionSliderTest);

    }

    //  @Test
    public void MenuItemOuvrirEventHandlerTest() {
        //mainFrameControleur.MenuItemOuvrirEventHandler h = new  mainFrameControleur.MenuItemOuvrirEventHandler();
    }

    @Test
    public void testObtenirLaPlusGrandeImageAPartirDeJsonArray() {
        mainFrameControleur = new MainFrameControleur();
        String stringJsonObject = "{\"album\":{\"name\":\"Vagabond\",\"artist\":\"Adrian Von Ziegler\",\"mbid\":\"53d7c7fe-1ebb-4d01-99d3-2fe32d2af6cb\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler/Vagabond\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/a0b41a57bb314780c78f1bf00d82a255.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/a0b41a57bb314780c78f1bf00d82a255.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/a0b41a57bb314780c78f1bf00d82a255.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/a0b41a57bb314780c78f1bf00d82a255.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/a0b41a57bb314780c78f1bf00d82a255.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/a0b41a57bb314780c78f1bf00d82a255.png\",\"size\":\"\"}],\"listeners\":\"339\",\"playcount\":\"2686\",\"tracks\":{\"track\":[{\"name\":\"Spring Charm\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler/_/Spring+Charm\",\"duration\":\"1243\",\"@attr\":{\"rank\":\"1\"},\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"},\"artist\":{\"name\":\"Adrian Von Ziegler\",\"mbid\":\"150d0745-686f-4801-ad5d-58565426199a\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler\"}},{\"name\":\"Summer Isle\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler/_/Summer+Isle\",\"duration\":\"1211\",\"@attr\":{\"rank\":\"2\"},\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"},\"artist\":{\"name\":\"Adrian Von Ziegler\",\"mbid\":\"150d0745-686f-4801-ad5d-58565426199a\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler\"}},{\"name\":\"Autumn Forest\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler/_/Autumn+Forest\",\"duration\":\"1213\",\"@attr\":{\"rank\":\"3\"},\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"},\"artist\":{\"name\":\"Adrian Von Ziegler\",\"mbid\":\"150d0745-686f-4801-ad5d-58565426199a\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler\"}},{\"name\":\"Winter Breath\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler/_/Winter+Breath\",\"duration\":\"1209\",\"@attr\":{\"rank\":\"4\"},\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"},\"artist\":{\"name\":\"Adrian Von Ziegler\",\"mbid\":\"150d0745-686f-4801-ad5d-58565426199a\",\"url\":\"https://www.last.fm/music/Adrian+Von+Ziegler\"}}]},\"tags\":{\"tag\":[]}}}";
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(stringJsonObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject albumJsonObject = (JSONObject) jsonObject.get("album");
        JSONArray jsonArrayImage = (JSONArray) albumJsonObject.get("image");

        JSONObject itemJsonArrayTmp = (JSONObject) jsonArrayImage.get(4);
        String adresseImage = (String) itemJsonArrayTmp.get("#text");
        URL urlImage = null;
        Image imageAttendue = null;
        try {
            urlImage = new URL(adresseImage);
            imageAttendue = SwingFXUtils.toFXImage(ImageIO.read(urlImage), null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image imageObtenue = mainFrameControleur.obtenirLaPlusGrandeImageAPartirDeJsonArray(jsonArrayImage);


        assertEquals(imageAttendue.getHeight(), imageObtenue.getHeight(), 0);
        assertEquals(imageAttendue.getWidth(), imageObtenue.getWidth(), 0);


    }

}