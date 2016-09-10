package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.ControleurMenu;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.vue.MainFrame;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MainClass {
    public static void main(String[] args) {

        // vue
        MainFrame mainFrame = new MainFrame();

        // modele
        FileOpener fileOpener = new FileOpener();

        ControleurMenu controleurMenu = new ControleurMenu(mainFrame, fileOpener);

        GestionnaireMusique gestionMusique = new GestionnaireMusique();

        controleurMenu.ajouterGestionnaireMusique(gestionMusique);

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib/natives/windows_32/");

        mainFrame.getFenetre().setVisible(true);

    }
}
