package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.ControleurMenu;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.vue.MainFrame;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MainClass {
    public static void main(String[] args) {

        chargerLibSysteme();

        // vue
        MainFrame mainFrame = new MainFrame();

        // modele
        FileOpener fileOpener = new FileOpener();

        ControleurMenu controleurMenu = new ControleurMenu(mainFrame, fileOpener);

        GestionnaireMusique gestionMusique = new GestionnaireMusique();

        controleurMenu.ajouterGestionnaireMusique(gestionMusique);

        mainFrame.getFenetre().setVisible(true);

    }

    private static void chargerLibSysteme() {
        int typeSysteme = Platform.getOSType();
        String nomSystem = "";
        switch (typeSysteme) {
            case Platform.LINUX:
                nomSystem = "linux";
                break;
            case Platform.WINDOWS:
                nomSystem = "windows";
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
