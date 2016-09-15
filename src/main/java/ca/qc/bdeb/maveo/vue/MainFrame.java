package ca.qc.bdeb.maveo.vue;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;


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

    public static final String STR_NOM_PROGRAMME = "M A V E O";

    public final String STR_BOUTON_PAUSE = "PAUSE";
    public final String STR_BOUTON_JOUER = "JOUER";
    private final String STR_BOUTON_STOP = "STOP";
    private final String STR_MENU_ITEM_MEDIA = "Média";
    private final String STR_MEDIA_OPTION_OUVRIR = "Ouvrir un fichier...";
    private static final String STR_MEDIA_OPTION_OUVRIRPLUSIEURS = "Ouvrir plusieurs fichiers...";
    private static final String STR_MEDIA_OPTION_ENREGISTRERLISTEDELECTURE = "Enregistrer Liste de lecture";
    private static final String STR_MEDIA_OPTION_QUITTER = "Quitter";

    @FXML
    Button boutonPlayPause;

    @FXML
    Button boutonArreter;

    @FXML
    Button boutonPrecedent;

    @FXML
    Button boutonSuivant;

    @FXML
    Slider sliderProgression;

    @FXML
    Slider sliderVolume;

    @FXML
    Pane panelControleur;

    @FXML
    Pane panelEcran;

    @FXML
    BorderPane fenetrePrincipale;

    @FXML
    MenuBar menuBar;

    @FXML
    MenuItem menuItemFile;

    @FXML
    MenuItem menuItemEdit;

    @FXML
    MenuItem menuItemHelp;

    @FXML
    MenuItem menuItemFileClose;

    @FXML
    MenuItem menuItemEditDelete;

    @FXML
    MenuItem menuItemHelpAbout;

    public MainFrame() {
        
    }

    public void afficherMessage(){
        System.out.println("Ca marche");

    }

    public void renommerBouton(){
        boutonPlayPause.setText("Allahu");

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
}
