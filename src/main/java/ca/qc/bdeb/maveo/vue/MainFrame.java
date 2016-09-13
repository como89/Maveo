package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Cedric Wu Tchan Ki on 2016-09-07.
 */
public class MainFrame {


    private static final String STR_MENU_ITEM_LECTURE = "Lecture";
    private static final String STR_MENU_ITEM_AUDIO = "Audio";
    private static final String STR_MENU_ITEM_VIDEO = "Vidéo";
    private static final String STR_MENU_ITEM_SOUSTITRES = "Sous-titres";
    private static final String STR_MENU_ITEM_OUTILS = "Outils";
    private static final String STR_MENU_ITEM_VUE = "Vue";
    private static final String STR_MENU_ITEM_AIDE = "Aide";
    private JFrame fenetre;
    private JButton btnJouerPause;
    private JButton btnArreter;
    private boolean estEnJeu;

    private final String STR_NOM_PROGRAMME = "M A V E O";

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

    public final String STR_BOUTON_PAUSE = "PAUSE";
    public final String STR_BOUTON_JOUER = "JOUER";
    private final String STR_BOUTON_STOP = "STOP";
    private final String STR_MENU_ITEM_MEDIA = "Média";
    private final String STR_MEDIA_OPTION_OUVRIR = "Ouvrir un fichier...";
    private static final String STR_MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers...";
    private static final String STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String STR_MEDIA_OPTION_QUITTER = "Quitter";


    JMenuBar menu;
    JMenu media;
    JMenu lecture;
    JMenu audio;
    JMenu video;
    JMenu sousTitres;
    JMenu outils;
    JMenu vue;
    JMenu aide;


    JMenuItem jMenuItemOuvrirUnFichier;
    JMenuItem ouvrirPlusieursFichiers;
    JMenuItem enregistrerListeDeLecture;
    JMenuItem quitter;

    private MainFrameControleur mainFrameControleur;

    public MainFrame() {

        fenetre = new JFrame(STR_NOM_PROGRAMME);

        initialiserMainFrame();
    }


    /*
    Initialise le MainFrame avec tous les contrôles nécessaires. Méthode executée lors de la construction
    du Frame.
     */
    private void initialiserMainFrame() {
        JPanel content = new JPanel(new java.awt.GridLayout());
        JPanel content2 = new JPanel(new java.awt.BorderLayout());

        //Initialisation des boutons
        btnJouerPause = new JButton(STR_BOUTON_JOUER);
        btnArreter = new JButton(STR_BOUTON_STOP);

        //ajout des boutons sur le frame
        content.add(btnJouerPause);
        content.add(btnArreter);

        fenetre.add(content, BorderLayout.SOUTH);
        fenetre.add(content2, BorderLayout.NORTH);
        fenetre.setJMenuBar(creerMenu());
        fenetre.setSize(800, 700);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    /*
    Crée la barre de menu avec tous les components
         */
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


        jMenuItemOuvrirUnFichier = new JMenuItem(STR_MEDIA_OPTION_OUVRIR);
        ouvrirPlusieursFichiers = new JMenuItem(STR_MEDIA_OPTION_OUVRIRPLUSIEURS);
        enregistrerListeDeLecture = new JMenuItem(STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE);
        quitter = new JMenuItem(STR_MEDIA_OPTION_QUITTER);

        media.add(jMenuItemOuvrirUnFichier);
        media.add(ouvrirPlusieursFichiers);
        media.add(enregistrerListeDeLecture);
        media.add(quitter);

        jMenuItemOuvrirUnFichier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
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

    /**
     * Ajoute un Listener à jMenuItemOuvrirUnFichier
     *
     * @param listener Le listener à ajouter à jMenuItemOuvrirUnFichier
     */
    public void addListenerJMenuItemOuvrirFichier(ActionListener listener) {
        jMenuItemOuvrirUnFichier.addActionListener(listener);
    }

    /**
     * Ajoute un Listener à btnJouerPause
     *
     * @param listener Le listener à ajouter à btnJouerPause
     */
    public void addListenerBtnJouerPause(ActionListener listener) {
        btnJouerPause.addActionListener(listener);
    }

    /**
     * Ajoute un Listener à btnArreter
     *
     * @param listener Le listener à ajouter à btnArreter
     */
    public void addListenerBtnArreter(ActionListener listener) {
        btnArreter.addActionListener(listener);
    }

    /**
     * Retourne la fenêtre
     *
     * @return la fenêtre
     */
    public JFrame getFenetre() {
        return fenetre;
    }
}
