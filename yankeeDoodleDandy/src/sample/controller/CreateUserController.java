package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;

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
        users.add(new User(createUserName.getText(), createPassword.getText()));

        /*
        if (createPassword.getText() != (createPasswordRepeat.getText())){
            errorMessage.setText("ERROR: Please make sure your passwords match.");
            System.out.println(createPassword.getText()+ " "+ createPasswordRepeat.getText());
        }
        if (createUserName.getText()==null) {
            errorMessage.setText("ERROR: Please choose a username.");
        }

        if (createAgeDisplay.getText()=="Rated R status will show here.") {
            errorMessage.setText("ERROR: Birthday input invalid.");
        }
        else{
            System.out.println("This user has chosen the username " + createUserName.getText() +". The user has also chosen a password. That password is: " + createPassword.getText() + ". Please don't tell anyone! The user named " + createUserName.getText() + " has enlightened us with their birthday, and we conclude that for this particular user, based on their age: "+createAgeDisplay.getText());
        }

         */
    }


}