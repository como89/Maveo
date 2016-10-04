package ca.qc.bdeb.maveo.modele;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 1379708 on 2016-10-04.
 */
public class PlaylistTest {

    private static final int LENGTH_MEDIA = 4;
    private static final String NAME_MEDIA = "Tokyo.mp3";
    private static final String PATH_MEDIA = "res/Tokyo.mp3";

    @Test
    public void ajouterChansonAListe() throws Exception {
        Media media = new Media("Tokyo.mp3", "res/Tokyo.mp3");

        Playlist liste = new Playlist("test", 1);
        liste.ajouterChansonAListe(media);
        Assert.assertEquals(liste.recupererLongueurListe(), 1);

    }

    @Test
    public void retirerChansonAListe() throws Exception {
        Playlist liste = new Playlist("test", 2);

        for(int i = 0; i < LENGTH_MEDIA; i++) {
            liste.ajouterChansonAListe(new Media(NAME_MEDIA,PATH_MEDIA));
        }

        Assert.assertEquals(liste.recupererLongueurListe(), LENGTH_MEDIA);
    }



}