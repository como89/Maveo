package ca.qc.bdeb.maveo.modele;


import ca.qc.bdeb.maveo.Vue.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Cedric Wu Tchan Ki on 2016-09-08.
 */
public class Gestion_Evenement implements ActionListener {

    private MainFrame player;

    public Gestion_Evenement(MainFrame player) {
        this.player = player;
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.player.getBtnArreter()) {
            System.exit(0);
        } else if (source == this.player.getBtnJouerPause()) {


            String chemin = "C:\\MAVEO\\Maveo\\src\\main\\java\\ca\\qc\\bdeb\\maveo\\Gaara.mp3";


        }


    }
}
