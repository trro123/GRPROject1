package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.util.Calendar;

public class CreateUserController {
    @FXML
    private TextField createName;
    @FXML
    private PasswordField createPass;
    @FXML
    private PasswordField createRepeatPass;
    @FXML
    private DatePicker createDate;
    @FXML
    private TextField createAgeDisplay;
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;
    @FXML
    private void  showAge() {
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
