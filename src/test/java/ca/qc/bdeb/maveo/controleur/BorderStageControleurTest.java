package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.BorderStage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 03/11/16.
 */
public class BorderStageControleurTest extends ApplicationTest {

    private static final double DELTA = 1e-15;

    private BorderStageControleur borderStageControleur;
    private BorderStage borderStage;

    @Override
    public void start(Stage stage) throws Exception {
        URL ressourceBorderStage = getClass().getClassLoader().getResource("BorderStage.fxml");
        FXMLLoader loader = new FXMLLoader(ressourceBorderStage);
        loader.load();
        borderStage = loader.getController();
        borderStage.setStage(stage);
    }

    @Before
    public void before() throws IOException {
        borderStageControleur = new BorderStageControleur();
    }

    @Test
    public void testSetBorderStage() {
        borderStageControleur.setBorderStage(borderStage);
        assertEquals(borderStage, borderStageControleur.borderStage);
    }

    @Test
    public void testMinimiseWindow() {
        borderStageControleur.setBorderStage(borderStage);
        borderStageControleur.minimiseWindow();
        assertTrue(borderStageControleur.borderStage.getStage().isIconified());
    }

    @Test
    public void testNotMinimiseWindow() {
        borderStageControleur.setBorderStage(borderStage);
        assertFalse(borderStageControleur.borderStage.getStage().isIconified());
    }

    @Test
    public void testMaximiseWindow() {
        borderStageControleur.setBorderStage(borderStage);
        borderStageControleur.maximiseWindow();
        assertTrue(borderStageControleur.borderStage.isMaximized());
    }

    @Test
    public void testNotMaximiseWindow() {
        borderStageControleur.setBorderStage(borderStage);
        assertFalse(borderStageControleur.borderStage.isMaximized());
    }

    @Test
    public void testPressMouse() {
        borderStageControleur.setBorderStage(borderStage);
        double x = 0, y = 0;
        borderStageControleur.pressMouse(x + 120, y + 120);
        assertEquals(x + 120, borderStageControleur.borderStage.getXOffSet(), DELTA);
        assertEquals(y + 120, borderStageControleur.borderStage.getYOffSet(), DELTA);
    }

    @Test
    public void testDragMouse() {
        borderStageControleur.setBorderStage(borderStage);
        double x = 50, y = 60;
        borderStageControleur.pressMouse(x, y);
        borderStageControleur.dragMouse(400, 500);
        Stage stage = borderStageControleur.borderStage.getStage();
        assertEquals(stage.getX(), 400 - 50, DELTA);
        assertEquals(stage.getY(), 500 - 60, DELTA);
    }
}