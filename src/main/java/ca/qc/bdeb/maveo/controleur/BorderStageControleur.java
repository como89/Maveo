package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.ResizeHelper;
import ca.qc.bdeb.maveo.util.DialogUtil;
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

    public void setHostService(HostServices hostServices) {
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
            openWikiPage();
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

    void openWikiPage() {
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
        StringBuilder builder = new StringBuilder();
        builder.append("https://fr.wikipedia.org/w/index.php?search=");
        if (result.isPresent()) {
            String[] fieldContents = result.get();
            if (fieldContents != null) {
                for (String content : fieldContents) {
                    builder.append(content);
                }
                hostServices.showDocument(builder.toString());
            }
        }
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
