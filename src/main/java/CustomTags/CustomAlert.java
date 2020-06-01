package main.java.CustomTags;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomAlert extends Alert {
    public CustomAlert(Stage stage, AlertType alertType) {
        super(alertType);
        constructor(stage, this);
    }

    public CustomAlert(Stage stage, AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        constructor(stage, this);
    }

    private void constructor(Stage stage, Alert alert) {
        stage.setAlwaysOnTop(true);
        stage.toFront();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
    }
}
