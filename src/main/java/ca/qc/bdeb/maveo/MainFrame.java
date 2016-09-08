package ca.qc.bdeb.maveo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Cedric Wu Tchan Ki on 2016-09-07.
 */
public class MainFrame extends JFrame {


    private JButton btnJouerPause;
    private JButton btnArreter;
    private boolean estEnJeu;

    private final String NOM_PROGRAMME = "MAVEO";
    private final String BOUTON_PLAY = "PLAY";

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

    private final String BOUTON_PAUSE = "PAUSE";
    private final String BOUTON_STOP = "STOP";
    private final String MENU_ITEM_MEDIA = "Media";
    private final String MEDIA_OPTION_OUVRIR = "Ouvrir un fichier";
    private static final String MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers";
    private static final String MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String MEDIA_OPTION_QUITTER = "Quitter";


    public MainFrame() {
        super("MAVEO");

        JPanel content = new JPanel(new java.awt.GridLayout());
        Gestion_Evenement ecouteurAction = new Gestion_Evenement(this);

        //Initialisation des boutons
        btnJouerPause = new JButton(BOUTON_PLAY);
        btnArreter = new JButton(BOUTON_STOP);

        //ajout des boutons sur le frame
        content.add(btnJouerPause);
        content.add(btnArreter);

        btnArreter.addActionListener(ecouteurAction);
        btnJouerPause.addActionListener(ecouteurAction);

        add(content);
        setJMenuBar(creerMenu());
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    private JMenuBar creerMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu media = new JMenu(MENU_ITEM_MEDIA);

        JMenuItem ouvrirUnFichier = new JMenuItem(MEDIA_OPTION_OUVRIR);
        JMenuItem ouvrirPlusieursFichiers = new JMenuItem(MEDIA_OPTION_OUVRIRPLUSIEURS);
        JMenuItem enregistrerListeDeLecture = new JMenuItem(MEDIA_OPTION_ENREGISTRERLISTEDELECTURE);
        JMenuItem quitter = new JMenuItem(MEDIA_OPTION_QUITTER);

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
        return menu;
    }

}
