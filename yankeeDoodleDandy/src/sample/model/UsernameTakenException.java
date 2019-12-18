package sample.model;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UsernameTakenException extends RuntimeException {

    public UsernameTakenException(String message){
        super(message);

        final Stage error = new Stage();
        error.initModality(Modality.APPLICATION_MODAL);
        Label errorLabel = new Label("ERROR 05; that username is already taken!");
        Scene dialogScene = new Scene(errorLabel, 300, 100);
        error.setScene(dialogScene);
        error.show();
    }

}
