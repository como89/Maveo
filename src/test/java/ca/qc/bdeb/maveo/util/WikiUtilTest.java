package ca.qc.bdeb.maveo.util;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 06/12/16.
 */
public class WikiUtilTest {

    private HostServices hostServices;

    @Test
    public void testPasdeContenu() {
        Application app = getApplication();
        WikiUtil wikiUtil = new WikiUtil(app.getHostServices());
        assertFalse(wikiUtil.hasContent());
    }

    @Test
    public void testContientduContenu() {
        Application app = getApplication();
        WikiUtil wikiUtil = new WikiUtil(app.getHostServices());
        wikiUtil.addSearchContent("Title");
        assertTrue(wikiUtil.hasContent());
    }

    @Test
    public void testContientDeuxContenus() {
        String title = "title";
        String artist = " artist";

        Application app = getApplication();
        WikiUtil wikiUtil = new WikiUtil(app.getHostServices());
        wikiUtil.addSearchContent(title + artist);
        assertTrue(wikiUtil.hasContent());
        assertEquals(title.length() + artist.length(),wikiUtil.searchContent.length());
    }

    /**
     * Permet de récupérer l'instance de l'application javafx.
     * @return retourne une instance de l'application javafx.
     */
    private Application getApplication() {
        return new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {}
        };
    }
}