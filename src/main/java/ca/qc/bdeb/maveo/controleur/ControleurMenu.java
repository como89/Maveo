package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.LecteurMp3;
import ca.qc.bdeb.maveo.modele.OuvrirFichier;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class ControleurMenu {

    private LecteurMp3 mp3Player;
    private OuvrirFichier ouvrirFichier;

    public ControleurMenu(LecteurMp3 mp3Player) {
        this.mp3Player = mp3Player;
        ouvrirFichier = new OuvrirFichier();
    }

    public void ouvrirUnFichier(){
        ouvrirFichier.activerOuvertureFichier();
    }
}
