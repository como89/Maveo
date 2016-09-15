package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;
import java.awt.*;
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
    private JLabel labelNomChanson;
    private boolean estEnJeu;

    private JPanel content;
    private JPanel content2;

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


    public JPanel getContent() {
        return content;
    }

    public JPanel getContent2() {
        return content2;
    }

    /*
        Initialise le MainFrame avec tous les contrôles nécessaires. Méthode executée lors de la construction
        du Frame.
         */
    private void initialiserMainFrame() {
         content = new JPanel(new java.awt.GridLayout());
         content2 = new JPanel(new java.awt.BorderLayout());

        //Initialisation des boutons
        btnJouerPause = new JButton(STR_BOUTON_JOUER);
        btnArreter = new JButton(STR_BOUTON_STOP);

        labelNomChanson = new JLabel("NOM_MÉDIA");

        //ajout des contrôles sur le frame
        content.add(btnJouerPause);
        content.add(btnArreter);

        content2.add(labelNomChanson, BorderLayout.SOUTH);


        fenetre.add(content, BorderLayout.SOUTH);
        fenetre.add(content2, BorderLayout.NORTH);
        fenetre.setJMenuBar(creerMenu());
        fenetre.setSize(800, 700);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    /**
     * Cree la barre de menu avec les components du menu
     * @return la barre etablie prete a etre ajoutee au frame.
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

        // jMenuItemOuvrirUnFichier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        //  ouvrirPlusieursFichiers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK |
        //           KeyEvent.SHIFT_DOWN_MASK));
        //    enregistrerListeDeLecture.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        //   quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

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
     * Cette méthode permet d'ajouter un eventHandler au composant selon le type du composant.
     * @param eventHandler - l'eventHandler à ajouter.
     * @param typeComposant - Le type du composant.
     */
    //TODO : replacer l'eventHandler selon les composants de la frame principale en JAVA FX.
    public void addEventHandler(EventHandler<?> eventHandler, TypeComposant typeComposant) {
        switch (typeComposant) {
            case BUTTON:
                break;
            case MENU_BUTTON:
                break;
            case SLIDER:
                break;
        }
    }

    /**
     * Retourne la fenêtre
     *
     * @return la fenêtre
     */
    public JFrame getFenetre() {
        return fenetre;
    }

    public JLabel getLabelNomChanson() {
        return labelNomChanson;
    }
}
