package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;
import sample.model.UsernameTakenException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * har til ansvar at oprette brugere, ved modellering af data indsamlet fra createUser_View
 */
public class CreateUserController {

    @FXML
    private Label createAgeDisplay;
    @FXML
    private DatePicker createDate;
    @FXML
    private PasswordField createPassword;
    @FXML
    private PasswordField createPasswordRepeat;
    @FXML
    private TextField createUserName;
    @FXML
    private Label errorMessage;


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

    public void cancel(javafx.event.ActionEvent actionEvent){
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
    }

    public void ok(javafx.event.ActionEvent actionEvent){
        boolean passwordChecked = false;
        boolean usernameChecked = false;

        if(createPassword.getText().trim().equals(createPasswordRepeat.getText().trim())){
            errorMessage.setText("ERROR 03; Make sure the passwords match.");

            if(!createPassword.getText().trim().isEmpty()){
                passwordChecked = true;
            }
        }else{
            errorMessage.setText("ERROR 03; Make sure the passwords match.");
        }

        if(createPassword.getText().trim().equals("")){
            errorMessage.setText("ERROR 02; Please choose a password.");
        }

        if(createUserName.getText().trim().equals("")){
            errorMessage.setText("ERROR 01; Please choose a username.");
        }else{
            usernameChecked = true;
        }

        if(usernameChecked && passwordChecked){
            for(User u : LoginController.getActiveUsers()){
                if(u.getUsername().equals(createUserName.getText())){
                    errorMessage.setText("");
                    throw new UsernameTakenException("Username already taken");
                }
            }

            LoginController.makeActive(new User(createUserName.getText(), createPassword.getText()));
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.close();
        }
    }
}