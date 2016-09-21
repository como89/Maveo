package ca.qc.bdeb.maveo.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

import java.net.URL;

/**
 * Created by WuTchanKi on 2016-09-20.
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

    /**
     * Test qui verifie que le bouton existe
     */
    @Test
    public void verifierExistenceBoutonPlay(){
        Assert.assertEquals(testFrame.boutonPlayPause.getText(), testFrame.STR_BOUTON_JOUER);
    }

    /**
     * Test qui verifie que le bouton change de texte quand il est clique
     */
    @Test
    public void verifierTexteBoutonPlay(){

    }
}