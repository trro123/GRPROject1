package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class CreateUserController {

    @FXML
    private Label createAgeDisplay;
    @FXML
    private DatePicker createDate;

    public void cancel(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
    }

    @FXML
    private void showAge() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int birthYear = (createDate.getValue().getYear());
        int age = year - birthYear;
        if (age <= 17) {
            createAgeDisplay.setText("R-rated content disabled.");
        } else {
            createAgeDisplay.setText("R-rated content enabled.");
        }
    }
}