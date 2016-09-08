package ca.qc.bdeb.maveo;
import ca.qc.bdeb.maveo.controleur.ControleurMenu;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
import ca.qc.bdeb.maveo.vue.MainFrame;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MainClass {
    public static void main(String[] args) {

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib/natives/windows_32/");

        GestionnaireMusique gestionMusique = new GestionnaireMusique();

        ControleurMenu controleurMenu = new ControleurMenu(gestionMusique);

        new MainFrame(controleurMenu);
    }
}
