package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by cedri on 2016-12-09.
 */
public class ParolesControleurTest extends ApplicationTest {
    MainFrame mainFrame;
    ParolesControleur parolesControleur;

    @Override
    public void start(Stage stage) throws Exception {

        URL ressource = getClass().getClassLoader().getResource("MainStage.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = (MainFrame) loader.getController();
        Scene sceneTest = new Scene(page);
        stage.setScene(sceneTest);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
        parolesControleur= new ParolesControleur();

        stage.show();

    }
    @Test
    public void ajouterMainFrame() throws Exception {

        parolesControleur.ajouterMainFrame(mainFrame);

        Assert.assertEquals(parolesControleur.mainFrame, mainFrame);
    }

    @Test
    public void chargerLyric() throws Exception {


    }

    @Test
    public void recupererParolesTest(){
        String titre = "Lonely";
        String parole = "";
        String artiste = "Akon";
       parole = parolesControleur.recupererParoles(titre, artiste);
      Assert.assertNotEquals(parole, "");
    }

}