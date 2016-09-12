package ca.qc.bdeb.maveo.util;

import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * @author Nicholas
 * @since 10/09/2016
 */
public class UtilLib {

    private static final String TXT_WINDOWS = "windows";
    private static final String TXT_LINUX = "linux";

    /**
     * Méthode qui permet de charger les libs pour le bon fonctionnement du logiciel
     * selon le système d'exploitation et son architecture (32 ou 64 bit)
     */
    public static void chargerLibSysteme() {
        int typeSysteme = Platform.getOSType();
        String nomSystem = "";
        switch (typeSysteme) {
            case Platform.LINUX:
                nomSystem = TXT_LINUX;
                break;
            case Platform.WINDOWS:
                nomSystem = TXT_WINDOWS;
                break;
        }

        if (Platform.is64Bit()) {
            nomSystem += "_64";
        } else {
            nomSystem += "_32";
        }

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib/natives/" + nomSystem + "/");
    }

}
