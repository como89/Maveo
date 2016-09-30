package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import ca.qc.bdeb.maveo.modele.FileOpener;
import ca.qc.bdeb.maveo.util.UtilLib;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainClass extends Application {

    public final double MIN_HEGHT_STAGE = 200;
    public final double MIN_WIDTH_STAGE = 400;


    public static void main(String[] args) {
        Application.launch(MainClass.class, (java.lang.String[]) null);
    }

    public void start(Stage stage) throws Exception {
        //chargement des libs
        UtilLib.chargerLibSysteme();


        // vue
        URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
        FXMLLoader loader = new FXMLLoader(ressource);
        BorderPane page = loader.load();
        MainFrame mainFrame = loader.getController();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
        stage.setMinHeight(MIN_HEGHT_STAGE);
        stage.setMinWidth(MIN_WIDTH_STAGE);

        stage.show();


        // modele
        FileOpener fileOpener = new FileOpener();

        MainFrameControleur mainFrameControleur = new MainFrameControleur();
        mainFrameControleur.ajouterMainFrame(mainFrame);
        mainFrameControleur.ajouterFileOpener(fileOpener);
    }
}
