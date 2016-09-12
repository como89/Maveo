package ca.qc.bdeb.maveo.modele;

import ca.qc.bdeb.maveo.util.UtilLib;
import org.junit.Assert;

/**
 * Created by Nicholas on 10/09/16.
 */
public class GestionnaireMusiqueTest {

    static {
        UtilLib.chargerLibSysteme();
    }

    @org.junit.Test
    public void demarer() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        boolean success = gestionMusique.demarrer("res/Gaara.mp3");
        try {
            Thread.currentThread().join(10000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(success);
    }

}