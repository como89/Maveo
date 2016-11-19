package ca.qc.bdeb.maveo.modele.fichier;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by nicholas on 21/09/16.
 */
public class FileOpenerTest {

    FileOpener fileOpener;

    @Test
    public void testDefaultFiltreMedia() {
        fileOpener = new FileOpener();
        Assert.assertEquals(fileOpener.fileChooser.getExtensionFilters(),
                fileOpener.accesExtensions.getListeFiltresMedia());
    }

    @Test
    public void testHaveFiltreParole() {
        fileOpener = new FileOpener();
        fileOpener.activerFiltresParoles();
        Assert.assertEquals(fileOpener.fileChooser.getExtensionFilters(),
                fileOpener.accesExtensions.getListeFiltresParoles());
    }

    @Test
    public void testHaveFiltrePlaylist() {
        fileOpener = new FileOpener();
        fileOpener.activerFiltresPlaylist();
        Assert.assertEquals(fileOpener.fileChooser.getExtensionFilters(),
                fileOpener.accesExtensions.getListeFiltresPlaylist());
    }

    @Test
    public void testHaveFiltreMedia() {
        fileOpener = new FileOpener();
        fileOpener.activerFiltresPlaylist();
        fileOpener.activerFiltresMedia();
        Assert.assertEquals(fileOpener.fileChooser.getExtensionFilters(),
                fileOpener.accesExtensions.getListeFiltresMedia());
    }
}