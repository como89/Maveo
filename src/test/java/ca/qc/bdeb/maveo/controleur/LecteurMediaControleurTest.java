package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.RunnableScheduledFuture;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 24/10/16.
 */
public class LecteurMediaControleurTest extends ApplicationTest {

    private static final float VALUE_CHANGE = 0.01f;
    private static final double DELTA = 1e-15;
    private static final float MULTIPLIER = 100;
    private static final String STYLE_CLASS_BTNPLAY = "buttonPlay";


    private final double MIN_HEGHT_STAGE = 200;
    private final double MIN_WIDTH_STAGE = 400;


    private LecteurMediaControleur lecteurMediaControleur;
    private MainFrame mainFrame;

    @Override
    public void start(Stage stage) throws Exception {
        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        mainFrame = loader.getController();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
        stage.setMinHeight(MIN_HEGHT_STAGE);
        stage.setMinWidth(MIN_WIDTH_STAGE);
    }

    @Before
    public void before() {
        lecteurMediaControleur = new LecteurMediaControleur();
        lecteurMediaControleur.ajouterMainFrame(mainFrame);
    }

    @Test
    public void positionChanged() throws Exception {
        final float valueBefore = (float) lecteurMediaControleur.mainFrame.getSliderProgression().getValue();
        lecteurMediaControleur.positionChanged(null, VALUE_CHANGE);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                float value = (float) lecteurMediaControleur.mainFrame.getSliderProgression().getValue();
                float requestValue = (valueBefore + VALUE_CHANGE) * MULTIPLIER;
                assertEquals(requestValue, value, DELTA);
            }
        });
    }

    @Test
    public void stopped() throws Exception {
        lecteurMediaControleur.stopped(null);
        assertTrue(hasStyleClass(STYLE_CLASS_BTNPLAY));
        assertTrue(mainFrame.getBtnArreter().isDisable());
    }

    @Test
    public void finished() throws Exception {
        double valueMin = lecteurMediaControleur.mainFrame.getSliderProgression().getMin();
        lecteurMediaControleur.finished(null);
        assertEquals(valueMin, lecteurMediaControleur.mainFrame.getSliderProgression().getValue(), DELTA);
        assertTrue(hasStyleClass(STYLE_CLASS_BTNPLAY));
    }

    /**
     * Méthode qui permet de vérifier si le bouton jouer à bien la styleclass du fichier css.
     *
     * @param styleClassName - le nom de la styleclass
     * @return retourne vrai s'il contient la styleclass.
     */
    private boolean hasStyleClass(String styleClassName) {
        boolean hasStyleClass = false;
        for (String styleClass : mainFrame.getBtnJouerPause().getStyleClass()) {
            if (styleClass.equals(styleClassName)) {
                hasStyleClass = true;
            }
        }
        return hasStyleClass;
    }
}