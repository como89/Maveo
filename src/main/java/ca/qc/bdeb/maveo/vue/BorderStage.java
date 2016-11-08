package ca.qc.bdeb.maveo.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by nicholas on 02/11/16.
 */
public class BorderStage {

    @FXML
    BorderPane borderPane;

    @FXML
    HBox boxButtons;

    @FXML
    HBox boxTitle;

    @FXML
    HBox boxCenter;

    @FXML
    ToggleButton btnHide;

    @FXML
    ToggleButton btnMaximise;

    @FXML
    ToggleButton btnClose;

    @FXML
    Text textTitle;

    Stage stage;

    boolean maximized = false;

    double originX = 0;
    double originY = 0;

    double originHeight = 0;
    double originWidth = 0;

    double xOffSet = 0;
    double yOffSet = 0;

    public ToggleButton getBtnHide() {
        return btnHide;
    }

    public ToggleButton getBtnMaximise() {
        return btnMaximise;
    }

    public ToggleButton getBtnClose() {
        return btnClose;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public double getX() {
        return originX;
    }

    public double getY() {
        return originY;
    }

    public double getXOffSet() {
        return xOffSet;
    }

    public double getYOffSet() {
        return yOffSet;
    }

    public void setXOffSet(double xOffSet) {
        this.xOffSet = xOffSet;
    }

    public void setYOffSet(double yOffSet) {
        this.yOffSet = yOffSet;
    }

    public void setPosition(double x, double y) {
        this.originX = x;
        this.originY = y;
    }

    public void setSize(double height, double width) {
        this.originHeight = height;
        this.originWidth = width;
    }

    public double getHeight() {
        return originHeight;
    }

    public double getWidth() {
        return originWidth;
    }

    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTitle(String title) {
        textTitle.setText(title);
    }

    public Stage getStage() {
        return stage;
    }

    public void setBtnHideEventHandler(EventHandler<ActionEvent> eventHide) {
        btnHide.setOnAction(eventHide);
    }

    public void setBtnMaximiseEventHandler(EventHandler<ActionEvent> eventMaximized) {
        btnMaximise.setOnAction(eventMaximized);
    }

    public void setBtnCloseEventHandler(EventHandler<ActionEvent> eventClose) {
        btnClose.setOnAction(eventClose);
    }

    public void setMouseDragEventHandler(EventHandler<MouseEvent> eventDrag) {
        boxCenter.setOnMouseDragged(eventDrag);
        boxButtons.setOnMouseDragged(eventDrag);
        boxTitle.setOnMouseDragged(eventDrag);
    }

    public void setMouseClickEventHandler(EventHandler<MouseEvent> eventClick) {
        boxCenter.setOnMousePressed(eventClick);
        boxButtons.setOnMousePressed(eventClick);
        boxTitle.setOnMousePressed(eventClick);
    }
}
