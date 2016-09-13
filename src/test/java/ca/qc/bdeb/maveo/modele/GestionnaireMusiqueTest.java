package ca.qc.bdeb.maveo.modele;

import ca.qc.bdeb.maveo.util.UtilLib;
import org.junit.Assert;

/**
 * Created by Nicholas on 10/09/16.
 */
public class GestionnaireMusiqueTest {

    private static final String TXT_LINK_MUSIQUE_TEST = "res/Gaara.mp3";

    static {
        UtilLib.chargerLibSysteme();
    }

    @org.junit.Test
    public void demarer() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        boolean success = gestionMusique.demarrer(TXT_LINK_MUSIQUE_TEST);
        Thread.currentThread().join(10);
        Assert.assertTrue(success);
    }

    @org.junit.Test
    public void arreter() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.demarrer(TXT_LINK_MUSIQUE_TEST);
        Thread.currentThread().join(10);
        gestionMusique.arreter();
        Thread.currentThread().join(10);
        Assert.assertFalse(gestionMusique.enCoursDeLecture());
    }

    @org.junit.Test
    public void pause() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.demarrer(TXT_LINK_MUSIQUE_TEST);
        Thread.currentThread().join(10);
        gestionMusique.pause();
        Thread.currentThread().join(10);
        Assert.assertFalse(gestionMusique.enCoursDeLecture());
    }

    @org.junit.Test
    public void reprendre() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.demarrer(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.pause();
        Thread.currentThread().join(10);
        gestionMusique.reprendre();
        Thread.currentThread().join(10);
        Assert.assertTrue(gestionMusique.enCoursDeLecture());
    }

    @org.junit.Test
    public void volume() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.demarrer(TXT_LINK_MUSIQUE_TEST);
        Thread.currentThread().join(10);
        int volume = gestionMusique.getVolume();
        int volumeModifie = volume + 50;
        gestionMusique.setVolume(volumeModifie);
        Thread.currentThread().join(10);
        Assert.assertEquals(volumeModifie, gestionMusique.getVolume());
    }

    @org.junit.Test
    public void temps() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.demarrer(TXT_LINK_MUSIQUE_TEST);
        Thread.currentThread().join(1200);
        Assert.assertTrue(gestionMusique.getTempsEcoule() >= 1000);
        long tempsTotal = gestionMusique.getTempsTotal();
        Assert.assertTrue(gestionMusique.getTempsRestant() <= tempsTotal);
    }
}