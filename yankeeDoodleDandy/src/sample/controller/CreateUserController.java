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

public class CreateUserController {

    private static ArrayList<User> users;

    public CreateUserController(){
        users = new ArrayList<>();
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

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

    public void cancel(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
    }

    public void ok(javafx.event.ActionEvent actionEvent) throws IOException {
        boolean passwordChecked = false;
        boolean usernameChecked = false;

        if(createPassword.getText().trim().equals(createPasswordRepeat.getText().trim())){
            errorMessage.setText("ERROR: Please make sure the passwords match.");

            if(!createPassword.getText().trim().isEmpty()){
                passwordChecked = true;
            }
        }else{
            errorMessage.setText("ERROR: Please make sure the passwords match.");
        }

        if(createPassword.getText().trim().equals("")){
            errorMessage.setText("ERROR: Please choose a password.");
        }

        if(createUserName.getText().trim().equals("")){
            errorMessage.setText("ERROR: Please choose a username.");
        }else{
            usernameChecked = true;
        }

        if(usernameChecked && passwordChecked){
            for(User u : LoginController.getActiveUsers()){
                if(u.getUsername().equals(createUserName.getText())){
                    throw new UsernameTakenException("Username already taken");
                }
            }

            //users.add(new User(createUserName.getText(), createPassword.getText()));
            LoginController.makeActive(new User(createUserName.getText(), createPassword.getText()));
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.close();
        }
    }
}