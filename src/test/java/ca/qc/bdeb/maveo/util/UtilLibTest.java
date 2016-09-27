package ca.qc.bdeb.maveo.util;

import com.sun.jna.NativeLibrary;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nicholas on 21/09/16.
 */
public class UtilLibTest {
    @Test
    public void testChargerLibSysteme() throws Exception {
        UtilLib.chargerLibSysteme();
        NativeLibrary nativeLibrary = NativeLibrary.getInstance("vlc");
        Assert.assertEquals("vlc", nativeLibrary.getName());
    }

}