package ca.qc.bdeb.maveo.modele.tags;

import ca.qc.bdeb.maveo.modele.Media;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 15/11/16.
 */
public class TagsIOTest {


    private TagsIO tagsIO;

    @Before
    public void before() {
        tagsIO = new TagsIO();
    }

    @Test
    public void testTagsExist() {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");
        assertNotNull(tags);
    }

    @Test
    public void testTagsNotExist() {
        Tags tags = tagsIO.getTagsFromMedia("res/wiki.mp3");
        assertNull(tags);
    }

    @Test
    public void testHasTitle() {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");
        assertNotEquals(tags.title, "");
    }

    @Test
    public void epurerChainesTest() {
        String chaineTest = "Hello   World";
        String chaineFinale = tagsIO.epurerChainesDeCaracteres(chaineTest);
        assertEquals("Hello World", chaineFinale);
    }

    @Test
    public void getTitle() throws Exception {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");
        Assert.assertEquals(tags.getTitle(), "Miss Independent");

    }

    @Test
    public void getArtist() throws Exception {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");
        Assert.assertEquals(tags.getArtist(), "Kelly Clarkson");
    }

    @Test
    public void getAlbum() throws Exception {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");
        Assert.assertEquals(tags.getAlbum(), "Greatest Hits - Chapter One");
    }

    @Test
    public void hasTitle() throws Exception {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");

        Assert.assertTrue(tags.hasTitle());
    }

    @Test
    public void hasArtist() throws Exception {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");
        Assert.assertTrue(tags.hasArtist());
    }

    @Test
    public void hasAlbum() throws Exception {
        Tags tags = tagsIO.getTagsFromMedia("res/uilo.mp3");

        Assert.assertTrue(tags.hasAlbum());
    }





}