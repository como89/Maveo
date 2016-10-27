package ca.qc.bdeb.maveo.vue;

import ca.qc.bdeb.maveo.MainClass;
import javafx.animation.FadeTransition;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe permet l'affichage d'un splashScreen avec le logo + le titre.
 *
 * @author Nicholas
 * @since 26/10/2016
 */
public class SplashScreen {

    static final double TEMPS_SECONDES = 4.0;
    static final double VALUE_DEPART = 4.0;
    static final double VALUE_FIN = 0.3;

    private JFrame splashFrame;
    private ImageView splashImage;
    private Label lblTitle;
    private VBox splashLayout;

    /**
     * On construit le splashScreen, avec un frame, un layout, une image et un texte.
     */
    public SplashScreen() {
        splashImage = new ImageView(MainClass.LOGO_SOFTWARE);
        splashImage.setFitWidth(447);
        splashImage.setFitHeight(MainClass.MIN_HEIGHT_STAGE - 200);
        splashImage.setPreserveRatio(true);

        lblTitle = new Label(MainFrame.STR_NOM_PROGRAMME);
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.getStyleClass().add("lblTitle");

        splashLayout = new VBox();
        splashLayout.getStylesheets().add("/StyleSplash.css");
        splashLayout.getStyleClass().add("splashLayout");
        splashLayout.setAlignment(Pos.CENTER);
        splashLayout.resize(MainClass.MIN_WIDTH_STAGE, MainClass.MIN_HEIGHT_STAGE);
        splashLayout.getChildren().addAll(splashImage, lblTitle);
        splashLayout.setEffect(new DropShadow());

        splashFrame = new JFrame();
        splashFrame.setSize(MainClass.MIN_WIDTH_STAGE - 160, MainClass.MIN_HEIGHT_STAGE - 120);
        splashFrame.setLocationRelativeTo(null);
        splashFrame.setType(Window.Type.POPUP);
        //splashFrame.setUndecorated(true);

        //On utilise un JFXPanel pour l'intercompatibilité entre le frame et le layout (Javafx)
        JFXPanel fxPanel = new JFXPanel();
        splashFrame.getContentPane().add(fxPanel);

        Scene sceneSplash = new Scene(splashLayout);
        fxPanel.setScene(sceneSplash);
    }

    /**
     * Cette méthode permet d'afficher le splash pendant un certains temps en secondes.
     */
    public void showSplash() {
        splashFrame.setVisible(true);
        splashFrame.toFront();

        /*
         * On applique ici un effet de fade sur le layout du splashscreen
         * afin d'effacer tranquillement le splashscreen.
         * Lorsque terminé, on rend invisible le frame.
         */
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(TEMPS_SECONDES), splashLayout);
        fadeSplash.setFromValue(VALUE_DEPART);
        fadeSplash.setToValue(VALUE_FIN);
        fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                splashFrame.setVisible(false);
            }
        });
        fadeSplash.play();
    }
}
