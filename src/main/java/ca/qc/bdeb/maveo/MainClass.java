package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.LecteurMediaControleur;
import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import ca.qc.bdeb.maveo.controleur.PlaylistControleur;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.playlist.PlaylistIO;
import ca.qc.bdeb.maveo.util.UtilLib;
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

        //Création du splashScreen et on l'affiche.
        SplashScreen splashScreen = new SplashScreen();


        //Chargement des libs
        UtilLib.chargerLibSysteme();

        //On affiche le stage principal.
        showMainStage(stage);
        splashScreen.showSplash();
    }

    /**
     * Cette méthode permet d'afficher le stage principal du logiciel.
     */
    private void showMainStage(Stage mainStage) {
        try {
            mainStage.initStyle(StageStyle.DECORATED);
            // vue
            URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
            FXMLLoader loader = new FXMLLoader(ressource);
            BorderPane page = null;
            page = loader.load();
            MainFrame mainFrame = loader.getController();
            Scene scene = new Scene(page);
            mainStage.setScene(scene);
            mainStage.setTitle(MainFrame.STR_NOM_PROGRAMME);
            mainStage.setMinHeight(MIN_HEIGHT_STAGE);
            mainStage.setMinWidth(MIN_WIDTH_STAGE);
            mainStage.getIcons().add(LOGO_SOFTWARE);
            mainStage.show();

            // modele
            FileOpener fileOpener = new FileOpener();

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

            controleurLecteurMedia.ajouterMainFrame(mainFrame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
