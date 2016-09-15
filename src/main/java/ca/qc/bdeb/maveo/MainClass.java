package ca.qc.bdeb.maveo;

import ca.qc.bdeb.maveo.controleur.MainFrameControleur;
import ca.qc.bdeb.maveo.modele.GestionnaireMusique;
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

    public static void main(String[] args) {
        Application.launch(MainClass.class, (java.lang.String[]) null);
    }

    public void start(Stage stage) throws Exception {
        //chargement des libs
        UtilLib.chargerLibSysteme();

        // vue
        try {
            URL ressource = getClass().getClassLoader().getResource("GuiSample.fxml");
            BorderPane page = FXMLLoader.load(ressource);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setTitle(MainFrame.STR_NOM_PROGRAMME);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }



        // modele
        FileOpener fileOpener = new FileOpener();

        // Lecteur média
        GestionnaireMusique gestionMusique = new GestionnaireMusique();

        MainFrameControleur mainFrameControleur = new MainFrameControleur();
        // mainFrameControleur.ajouterMainFrame(mainFrame);
        mainFrameControleur.ajouterFileOpener(fileOpener);
        mainFrameControleur.ajouterGestionnaireMusique(gestionMusique);
    }
}
