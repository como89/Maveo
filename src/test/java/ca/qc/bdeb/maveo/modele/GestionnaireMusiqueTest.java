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
    public void testDemarer() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        boolean success = gestionMusique.demarrer();
        Thread.currentThread().join(10);
        Assert.assertTrue(success);
    }

    @org.junit.Test
    public void testArreter() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.demarrer();
        Thread.currentThread().join(10);
        gestionMusique.arreter();
        Thread.currentThread().join(10);
        Assert.assertFalse(gestionMusique.enLecture());
    }

    @org.junit.Test
    public void testPause() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.demarrer();
        Thread.currentThread().join(10);
        gestionMusique.pause();
        Thread.currentThread().join(10);
        Assert.assertFalse(gestionMusique.enLecture());
    }

    @org.junit.Test
    public void testReprendre() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.demarrer();
        gestionMusique.pause();
        Thread.currentThread().join(10);
        gestionMusique.jouerMedia();
        Thread.currentThread().join(10);
        Assert.assertTrue(gestionMusique.enLecture());
    }

    @org.junit.Test
    public void testVolume() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.demarrer();
        Thread.currentThread().join(10);
        int volume = gestionMusique.getVolume();
        int volumeModifie = volume + 50;
        gestionMusique.setVolume(volumeModifie);
        Thread.currentThread().join(10);
        Assert.assertEquals(volumeModifie, gestionMusique.getVolume());
        //Retour au volume initial
        gestionMusique.setVolume(volume);
    }

    @org.junit.Test
    public void testTemps() throws Exception {
        GestionnaireMusique gestionMusique = new GestionnaireMusique();
        gestionMusique.setCheminFichier(TXT_LINK_MUSIQUE_TEST);
        gestionMusique.demarrer();
        Thread.currentThread().join(1200);
        Assert.assertTrue(gestionMusique.getTempsEcoule() >= 1000);
        long tempsTotal = gestionMusique.getTempsTotal();
        Assert.assertTrue(gestionMusique.getTempsRestant() <= tempsTotal);
    }
}