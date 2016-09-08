package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.controleur.ControleurMenu;
import ca.qc.bdeb.maveo.modele.Gestion_Evenement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Cedric Wu Tchan Ki on 2016-09-07.
 */
public class MainFrame  {


    private static final String STR_MENU_ITEM_LECTURE = "Lecture";
    private static final String STR_MENU_ITEM_AUDIO = "Audio";
    private static final String STR_MENU_ITEM_VIDEO = "Video";
    private static final String STR_MENU_ITEM_SOUSTITRES = "Sous-titres";
    private static final String STR_MENU_ITEM_OUTILS = "Outils";
    private static final String STR_MENU_ITEM_VUE = "Vue";
    private static final String STR_MENU_ITEM_AIDE = "Aide";

private JFrame fenetre;
    private JButton btnJouerPause;
    private JButton btnArreter;
    private boolean estEnJeu;

    private final String STR_NOM_PROGRAMME = "MAVEO";
    private final String STR_BOUTON_PLAY = "PLAY";

    public JButton getBtnJouerPause() {
        return btnJouerPause;
    }


    public void setBtnJouerPause(JButton btnJouerPause) {
        this.btnJouerPause = btnJouerPause;
    }

    public JButton getBtnArreter() {
        return btnArreter;
    }

    public void setBtnArreter(JButton btnArreter) {
        this.btnArreter = btnArreter;
    }

    private final String STR_BOUTON_PAUSE = "PAUSE";
    private final String STR_BOUTON_STOP = "STOP";
    private final String STR_MENU_ITEM_MEDIA = "Media";
    private final String STR_MEDIA_OPTION_OUVRIR = "Ouvrir un fichier";
    private static final String STR_MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers";
    private static final String STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String STR_MEDIA_OPTION_QUITTER = "Quitter";




    private ControleurMenu controleurMenu;

    public MainFrame(ControleurMenu controleurMenu) {

        fenetre = new JFrame("MAVEO");

        initialiserMainFrame();

        this.controleurMenu = controleurMenu;
    }


    /*
    Initialise le MainFrame avec tous les contrôles nécessaires. Méthode executée lors de la construction
    du Frame.
     */
    private void initialiserMainFrame(){
        JPanel content = new JPanel(new java.awt.GridLayout());
        JPanel content2 = new JPanel(new java.awt.BorderLayout());
        Gestion_Evenement ecouteurAction = new Gestion_Evenement(this);

        //Initialisation des boutons
        btnJouerPause = new JButton(STR_BOUTON_PLAY);
        btnArreter = new JButton(STR_BOUTON_STOP);

        //ajout des boutons sur le frame
        content.add(btnJouerPause);
        content.add(btnArreter);

        btnArreter.addActionListener(ecouteurAction);
        btnJouerPause.addActionListener(ecouteurAction);

        fenetre.add(content, BorderLayout.SOUTH);
        fenetre.add(content2, BorderLayout.NORTH);
        fenetre.setJMenuBar(creerMenu());
       fenetre.setSize(800, 700);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    private JMenuBar creerMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu media = new JMenu(STR_MENU_ITEM_MEDIA);
        JMenu lecture = new JMenu(STR_MENU_ITEM_LECTURE);
        JMenu audio = new JMenu(STR_MENU_ITEM_AUDIO);
        JMenu video = new JMenu(STR_MENU_ITEM_VIDEO);
        JMenu sousTitres = new JMenu(STR_MENU_ITEM_SOUSTITRES);
        JMenu outils = new JMenu(STR_MENU_ITEM_OUTILS);
        JMenu vue = new JMenu(STR_MENU_ITEM_VUE);
        JMenu aide = new JMenu(STR_MENU_ITEM_AIDE);


        JMenuItem ouvrirUnFichier = new JMenuItem(STR_MEDIA_OPTION_OUVRIR);
        JMenuItem ouvrirPlusieursFichiers = new JMenuItem(STR_MEDIA_OPTION_OUVRIRPLUSIEURS);
        JMenuItem enregistrerListeDeLecture = new JMenuItem(STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE);
        JMenuItem quitter = new JMenuItem(STR_MEDIA_OPTION_QUITTER);

        media.add(ouvrirUnFichier);
        media.add(ouvrirPlusieursFichiers);
        media.add(enregistrerListeDeLecture);
        media.add(quitter);

        ouvrirUnFichier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        ouvrirPlusieursFichiers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK |
                KeyEvent.SHIFT_DOWN_MASK));
        enregistrerListeDeLecture.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

        menu.add(media);
        menu.add(lecture);
        menu.add(audio);
        menu.add(video);
        menu.add(sousTitres);
        menu.add(outils);
        menu.add(vue);
        menu.add(aide);
        return menu;
    }

}
