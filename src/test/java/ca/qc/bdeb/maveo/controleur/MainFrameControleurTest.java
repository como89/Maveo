package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.MainFrame;
import org.junit.Assert;
import sun.applet.Main;

/**
 * Created by C A T A on 2016-09-12.
 */
public class MainFrameControleurTest {
    @org.junit.Test
    public void ajouterMainFrameTest() throws Exception {
        MainFrame mainFrame = new MainFrame();
        MainFrameControleur mfc = new MainFrameControleur();


        mfc.ajouterMainFrame(mainFrame);
      //  Assert.assertEquals(mainFrame, mfc.ma);



    }
}