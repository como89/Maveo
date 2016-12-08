package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.tags.Tags;
import ca.qc.bdeb.maveo.util.ResizeHelper;
import ca.qc.bdeb.maveo.util.DialogUtil;
import ca.qc.bdeb.maveo.util.WikiUtil;
import ca.qc.bdeb.maveo.vue.BorderStage;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by nicholas on 02/11/16.
 */
public class BorderStageControleur {

    BorderStage borderStage;
    HostServices hostServices;

    public BorderStageControleur() {
    }

    public void setBorderStage(BorderStage borderStage) {
        this.borderStage = borderStage;
        this.borderStage.setBtnHideEventHandler(new ButtonHideEventHandler());
        this.borderStage.setBtnMaximiseEventHandler(new ButtonMaximizedEventHandler());
        this.borderStage.setBtnCloseEventHandler(new ButtonCloseEventHandler());
        this.borderStage.setMouseClickEventHandler(new MouseClickWindowEventHandler());
        this.borderStage.setMouseDragEventHandler(new MouseDragWindowEventHandler());
        this.borderStage.setBtnWikiEventHandler(new ButtonWikiEventHandler());
        ResizeHelper.addResizeListener(this.borderStage.getStage());
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    class ButtonHideEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            minimiseWindow();
        }
    }

    class ButtonMaximizedEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            maximiseWindow();
        }
    }

    class ButtonCloseEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

    class ButtonWikiEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            handleWikiPage();
        }
    }

    class MouseClickWindowEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            pressMouse(event.getSceneX(), event.getSceneY());
        }
    }

    class MouseDragWindowEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            dragMouse(event.getScreenX(), event.getScreenY());
        }
    }

    /**
     * Méthode qui vérifie si des tags existent, si oui, il ajoute le contenu au wikiUtil afin
     * de pouvoir afficher la page de wiki.
     * Si les tags n'existent pas, on demande des informations pour effectuer une recherche sur wiki.
     * Si aucune musique n'est joué, on demande des informations pour effectuer une recherche sur wiki.
     */
    void handleWikiPage() {
        boolean noTags = true;
        WikiUtil wikiUtil = new WikiUtil(hostServices);
        GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
        if(gestionnaireMedia instanceof GestionnaireMusique) {
            GestionnaireMusique gestionnaireMusique = (GestionnaireMusique) gestionnaireMedia;
            Tags tags = gestionnaireMusique.getTags();
            if(tags != null) {
                String title = tags.hasTitle()?tags.getTitle():"";
                String artist = tags.hasArtist()?tags.getArtist():"";
                String album = tags.hasAlbum()?tags.getAlbum():"";
                wikiUtil.addSearchContent(title + " " + artist + " " + album);
                noTags = false;
            }
        }

        if(noTags) {
            String[] informations = askWikiSearch();
            if(informations != null) {
                wikiUtil.addSearchContent(informations[0]);
            }
        }

        wikiUtil.openWikiPage();
    }

    /**
     * Méthode pour demander des informations afin de faire une recherche sur wiki.
     * @return Retourne les informations écrites dans le dialog.
     */
    String[] askWikiSearch() {
        String[] fieldsName = {"Recherche sur wiki : "};
        Dialog<String[]> dialog = DialogUtil.prepareRequestInformation(fieldsName);
        DialogPane dialogPane = dialog.getDialogPane();
        Parent root = (Parent) dialogPane.getContent();
        VBox verticalBoxField = (VBox) root.lookup("#boxVerticalField");

        dialog.setResultConverter(dialogButton -> {
            String[] fieldContent = null;
            if (dialogButton == ButtonType.OK) {
                fieldContent = new String[fieldsName.length];
                for (int index = 0; index < fieldContent.length; index++) {
                    fieldContent[index] = ((TextField) verticalBoxField.getChildren().get(index)).getText();
                }
            }
            return fieldContent;
        });
        Optional<String[]> result = dialog.showAndWait();
        return result.isPresent()?result.get():null;
    }

    void minimiseWindow() {
        Stage stage = borderStage.getStage();
        stage.setIconified(true);
    }

    void maximiseWindow() {
        Stage stage = borderStage.getStage();

        if (!borderStage.isMaximized()) {
            borderStage.setMaximized(true);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            borderStage.setPosition(stage.getX(), stage.getY());
            borderStage.setSize(stage.getHeight(), stage.getWidth());

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
        } else {
            borderStage.setMaximized(false);
            stage.setX(borderStage.getX());
            stage.setY(borderStage.getY());
            stage.setWidth(borderStage.getWidth());
            stage.setHeight(borderStage.getHeight());
        }
    }

    void pressMouse(double x, double y) {
        borderStage.setXOffSet(x);
        borderStage.setYOffSet(y);
    }

    void dragMouse(double xWindow, double yWindow) {
        if (!borderStage.isMaximized()) {
            Stage stage = borderStage.getStage();
            stage.setX(xWindow - borderStage.getXOffSet());
            stage.setY(yWindow - borderStage.getYOffSet());
        }
    }
}
