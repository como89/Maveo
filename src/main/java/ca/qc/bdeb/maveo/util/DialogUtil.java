package ca.qc.bdeb.maveo.util;

import com.sun.istack.internal.Nullable;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

/**
 * Created by nicholas on 30/11/16.
 */
public class DialogUtil {

    static final String QUESTION_TITLE = "Question";
    static final String QUESTION_REQUEST_INFO_TITLE = "Information sur la chanson";
    static final String REQUEST_INFO_HEADER = "Entrez les informations demandées : ";

    /**
     * Méthode qui prépare une boite de dialog pour poser une question à l'utilisateur.
     * @return Retourne la boite de dialog préparée.
     */
    public static Alert prepareQuestionDialog(String question, String[] responses) {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle(QUESTION_TITLE);
        alertDialog.setHeaderText(question);
        alertDialog.getButtonTypes().clear();

        ButtonType[] buttonTypes = new ButtonType[responses.length + 1];
        for(int index = 0; index < responses.length;index++) {
            buttonTypes[index] = new ButtonType(responses[index]);
        }
        buttonTypes[responses.length] = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        alertDialog.getButtonTypes().addAll(buttonTypes);

        return alertDialog;
    }

    /**
     * Méthode qui prépare une boite de dialog pour demander le titre et l'artiste de la musique.
     * @return La boite de dialog préparé.
     */
    @Nullable
    public static Dialog<String[]> prepareRequestInformation(String[] fieldName) {
        //On prépare le dialog
        Dialog<String[]> requestDialog = new Dialog<>();
        requestDialog.setTitle(QUESTION_REQUEST_INFO_TITLE);
        requestDialog.setHeaderText(REQUEST_INFO_HEADER);

        DialogPane dialogPane = requestDialog.getDialogPane();
        dialogPane.getButtonTypes().clear();
        dialogPane.getButtonTypes().setAll(ButtonType.CANCEL,ButtonType.OK);

        try {
            Parent root = null;
            // On charge le fichier fxml pour l'interface de la boite de dialog.
            URL ressourceDialog = DialogUtil.class.getClassLoader().getResource("InformationDialog.fxml");
            if(ressourceDialog != null) {
                root = FXMLLoader.load(ressourceDialog);

                VBox verticalBoxLabel = (VBox) root.lookup("#boxVerticalLabel");
                VBox verticalBoxField = (VBox) root.lookup("#boxVerticalField");

                Label[] labelTabs = new Label[fieldName.length];
                TextField[] textFields = new TextField[fieldName.length];

                Node buttonOk = dialogPane.lookupButton(ButtonType.OK);
                buttonOk.setDisable(true);

                for(int index = 0; index < fieldName.length;index++) {
                    labelTabs[index] = new Label(fieldName[index]);
                    labelTabs[index].getStyleClass().add("label_font");
                    textFields[index] = new TextField();
                    TextField textField = textFields[index];
                    textFields[index].textProperty().addListener((observable, oldValue, newValue) ->{
                        buttonOk.setDisable(textField.getText().isEmpty());
                    });
                }

                verticalBoxLabel.getChildren().addAll(labelTabs);
                verticalBoxField.getChildren().addAll(textFields);

                dialogPane.setContent(root);

                //On demande le focus sur le premier field.
                Platform.runLater(textFields[0]::requestFocus);
            }
        } catch (IOException e) {e.printStackTrace();}
        return requestDialog;
    }

    /**
     * Méthode qui prépare un dialog pour afficher un about.
     * @param title - Le titre et le header de la fenêtre
     * @param content - Le contenu du message.
     * @return La boite de dialogue préparé.
     */
    public static Alert prepareAboutDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        return alert;
    }
}
