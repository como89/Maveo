package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.vue.BorderStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by nicholas on 02/11/16.
 */
public class BorderStageControleur {

    BorderStage borderStage;

    public BorderStageControleur() {
    }

    public void setBorderStage(BorderStage borderStage) {
        this.borderStage = borderStage;
        this.borderStage.setBtnHideEventHandler(new ButtonHideEventHandler());
        this.borderStage.setBtnMaximiseEventHandler(new ButtonMaximizedEventHandler());
        this.borderStage.setBtnCloseEventHandler(new ButtonCloseEventHandler());
        this.borderStage.setMouseClickEventHandler(new MouseClickWindowEventHandler());
        this.borderStage.setMouseDragEventHandler(new MouseDragWindowEventHandler());
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
