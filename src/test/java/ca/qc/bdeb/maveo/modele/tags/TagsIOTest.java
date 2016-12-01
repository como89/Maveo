package ca.qc.bdeb.maveo.modele.tags;

import ca.qc.bdeb.maveo.modele.Media;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 15/11/16.
 */
public class TagsIOTest {


    @Test
    public void getTagsFromMedia() throws Exception {

    }

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
        assertNotEquals(tags.title,"");
    }

    @Test
    public void epurerChainesTest(){
        String chaineTest = "Hello   World";
      String chaineFinale =  tagsIO.epurerChainesDeCaracteres(chaineTest);
      assertEquals("Hello World", chaineFinale);
    }




}