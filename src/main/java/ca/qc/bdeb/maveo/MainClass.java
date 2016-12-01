package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.*;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.playlist.PlaylistIO;
import ca.qc.bdeb.maveo.util.UtilLib;
import ca.qc.bdeb.maveo.vue.BorderStage;
import ca.qc.bdeb.maveo.vue.MainFrame;
import ca.qc.bdeb.maveo.vue.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class MainClass extends Application {

    public static final int MIN_HEIGHT_STAGE = 520;
    public static final int MIN_WIDTH_STAGE = 630;

    /**
     * Le logo du logiciel.
     */
    public static final Image LOGO_SOFTWARE = new Image(MainClass.class.getResourceAsStream("/Maveo_icon.png"));


    public static void main(String[] args) {
        Application.launch(MainClass.class, (java.lang.String[]) null);
    }

    public void start(Stage stage) throws Exception {

        //Création du splashScreen.
        SplashScreen splashScreen = new SplashScreen(stage);


        //Chargement des libs
        UtilLib.chargerLibSysteme();

        //On affiche le stage principal.
        showMainStage(stage);
        //On affiche le splashScreen
        splashScreen.showSplash();
    }

    /**
     * Cette méthode permet d'afficher le stage principal du logiciel.
     */
    private void showMainStage(Stage mainStage) {
        try {
            mainStage.initStyle(StageStyle.DECORATED);
            // vue
            URL ressourceMainGUI = getClass().getClassLoader().getResource("MainStage.fxml");
            FXMLLoader loader = new FXMLLoader(ressourceMainGUI);
            BorderPane page = loader.load();

            //Controller of fxml
            MainFrame mainFrame = loader.getController();

            //Load BorderStage
            URL ressourceBorderStage = getClass().getClassLoader().getResource("BorderStage.fxml");
            loader = new FXMLLoader(ressourceBorderStage);
            BorderPane boxBorder = loader.load();
            BorderStage borderStage = loader.getController();
            borderStage.setStage(mainStage);
            borderStage.setTitle(MainFrame.STR_NOM_PROGRAMME);
            //Set it to MainFrame.
            mainFrame.setBoxBorder(boxBorder);

            mainStage.setTitle(MainFrame.STR_NOM_PROGRAMME);
            mainStage.initStyle(StageStyle.UNDECORATED);
            mainStage.setMinHeight(MIN_HEIGHT_STAGE);
            mainStage.setMinWidth(MIN_WIDTH_STAGE);
            mainStage.getIcons().add(LOGO_SOFTWARE);
            Scene scene = new Scene(page);
            mainStage.setScene(scene);
            mainStage.show();

            // modele
            FileOpener fileOpener = new FileOpener();

            BorderStageControleur borderStageControleur = new BorderStageControleur();
            borderStageControleur.setBorderStage(borderStage);
            borderStageControleur.setHostService(this.getHostServices());

            MainFrameControleur mainFrameControleur = new MainFrameControleur();

            PlaylistControleur playlistControleur = new PlaylistControleur();
            PlaylistIO playlistIO = new PlaylistIO();
            playlistControleur.ajouterPlaylistIo(playlistIO);

            LecteurMediaControleur controleurLecteurMedia = new LecteurMediaControleur();

            mainFrameControleur.ajouterMainFrame(mainFrame);
            mainFrameControleur.ajouterFileOpener(fileOpener);
            mainFrameControleur.ajouterControleurLecteurMedia(controleurLecteurMedia);

            playlistControleur.ajouterMainFrame(mainFrame);
            playlistControleur.ajouterControleurLecteurMedia(controleurLecteurMedia);

            ParolesControleur parolesControleur = new ParolesControleur();
            parolesControleur.ajouterMainFrame(mainFrame);

            controleurLecteurMedia.ajouterMainFrame(mainFrame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
