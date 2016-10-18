package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.playlist.Playlist;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by WuTchanKi on 2016-10-12.
 */
public class PlaylistControleurTest extends ApplicationTest {

    PlaylistControleur playlistControleur;
    MainFrame mainFrame;
    FileOpener fileOpener;
    float floatTest;

    @Override
    public void start(Stage stage) throws Exception {

        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = (MainFrame) loader.getController();
        Scene sceneTest = new Scene(page);
        stage.setScene(sceneTest);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);

        stage.show();

    }


    @Test
    public void ajouterMainFrame() throws Exception {
        playlistControleur = new PlaylistControleur();
        playlistControleur.ajouterMainFrame(mainFrame);

        Assert.assertEquals(playlistControleur.mainframe, mainFrame);
    }

    @Test
    public void AjouterALaPlaylist(){
        Playlist playlistTest = new Playlist("TEST");
        Media mediaTest1 = new Media("CELINE DION", "X:/test");
        Media mediaTest2 = new Media("GAROU", "X:/test");

        playlistTest.ajouterMediaListe(mediaTest1);
        playlistTest.ajouterMediaListe(mediaTest2);


        Assert.assertEquals(playlistTest.getListeMedia().size(), 2);

    }

    @Test
    public void SauvegarderPlaylistTest() throws IOException {
        Playlist playlistTest = new Playlist("TEST");
        Media mediaTest1 = new Media("CELINE DION", "X:/test");
        Media mediaTest2 = new Media("GAROU", "X:/test");
        playlistTest.ajouterMediaListe(mediaTest1);
        playlistTest.ajouterMediaListe(mediaTest2);

        JSONObject testNull = new JSONObject();
        JSONObject testNotNull = new JSONObject();

        testNotNull.put("TEST", mediaTest1.getTitre());
        testNotNull.put("TEST", mediaTest2.getTitre());


        FileWriter file = new FileWriter("res/test.json");
        try {
            file.write(testNotNull.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }

        Assert.assertNotEquals(testNotNull.toJSONString(), testNull.toJSONString());
        Assert.assertNotNull(file);


    }

    @Test
    public void testOuvrirPlaylist() throws IOException, ParseException {
        File file = new File("res/test.json");
        JSONParser jp = new JSONParser();

        JSONObject jsonObject = (JSONObject) jp.parse(new FileReader(file.getAbsolutePath()));

        Playlist playlist = new Playlist("TEST");
       ArrayList<Media> liste =new ArrayList<Media>();
        liste.add((Media)jsonObject.get("GAROU"));

        playlist.ajouterMediaListe(liste.get(0));

        Assert.assertEquals(playlist.getListeMedia().size(), 1);




    }

    @Test
    public void creerPlaylistTest(){
        Playlist playlistTest = new Playlist("newPlaylist");
        Assert.assertNotNull(playlistTest);
    }

    @Test
    public void getMediaFromFileTest(){
        File file = new File("res/Tokyo.mp3");

      Media mediaTest = new Media(file.getName(), file.getAbsolutePath());

        Assert.assertEquals(mediaTest.getPathMedia(), file.getAbsolutePath());

    }


}