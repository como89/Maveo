package ca.qc.bdeb.maveo;
import ca.qc.bdeb.maveo.controleur.ControleurMenu;
import ca.qc.bdeb.maveo.modele.LecteurMp3;
import ca.qc.bdeb.maveo.vue.MainFrame;

public class MainClass {
    public static void main(String[] args) {

        LecteurMp3 mp3Player = new LecteurMp3();

        ControleurMenu controleurMenu = new ControleurMenu(mp3Player);

        new MainFrame(controleurMenu);
    }
}
