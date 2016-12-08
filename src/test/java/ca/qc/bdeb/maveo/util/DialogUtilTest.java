package ca.qc.bdeb.maveo.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

/**
 * Created by nicholas on 30/11/16.
 */
public class DialogUtilTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {}

    @Test
    public void testQuestionDialogInitialise() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String question = "MaQuestion";
                String[] responses = {"Response1","Response2","Response3"};
                Alert alertDialog = DialogUtil.prepareQuestionDialog(question,responses);

                ObservableList<ButtonType> listButtonType = alertDialog.getButtonTypes();
                String content = alertDialog.getHeaderText();
                String title = alertDialog.getTitle();
                Alert.AlertType type = alertDialog.getAlertType();

                assertEquals(type, Alert.AlertType.CONFIRMATION);
                assertEquals(title,DialogUtil.QUESTION_TITLE);
                assertEquals(content,question);

                int totalButtonType = listButtonType.size();
                for(int index = 0; index < totalButtonType;index++) {
                    String response = listButtonType.get(index).getText();
                    assertTrue(response.equals("Annuler") || response.equals(responses[index]));
                }
            }
        });
    }

    @Test
    public void testPrepareDialogInformation() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String[] fieldNames = {"field1", "field2"};
                Dialog<String[]> dialog = DialogUtil.prepareRequestInformation(fieldNames);
                DialogPane dialogPane = dialog.getDialogPane();
                ObservableList<ButtonType> listButtons = dialogPane.getButtonTypes();
                String title = dialog.getTitle();
                String header = dialog.getHeaderText();

                assertEquals(title, DialogUtil.QUESTION_REQUEST_INFO_TITLE);
                assertEquals(header, DialogUtil.REQUEST_INFO_HEADER);

                Parent root = (Parent) dialogPane.getContent();

                int nbTextField = 0;
                VBox verticalBoxLabel = (VBox) root.lookup("#boxVerticalLabel");
                VBox verticalBoxField = (VBox) root.lookup("#boxVerticalField");
            }
        });
    }

    @Test
    public void testPrepareAboutDialog() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String title = "MyTitle";
                String content = "MyContent";
                Alert alert = DialogUtil.prepareAboutDialog(title, content);

                Alert.AlertType type = alert.getAlertType();

                assertEquals(title, alert.getHeaderText());

                assertEquals(content, alert.getContentText());

                assertEquals(Alert.AlertType.INFORMATION,type);
            }
        });
    }
}