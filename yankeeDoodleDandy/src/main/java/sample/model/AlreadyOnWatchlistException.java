package sample.model;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.*;

/**
 * En checked exception. Hvis fremprovokeret: Åbner et popup-vindue med teksten "ERROR 06; this is already on your watchlist!"
 */
public class AlreadyOnWatchlistException extends Exception {

    public AlreadyOnWatchlistException(String message){
        super(message);

        final Stage error = new Stage();
        error.initModality(Modality.APPLICATION_MODAL);
        Label errorLabel = new Label("ERROR 06; this is already on your watchlist!");
        Scene dialogScene = new Scene(errorLabel, 300, 100);
        error.setScene(dialogScene);
        error.show();
    }
}