package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Har til ansvar at modelere login_view samt tilføje brugere til ArrayList activeUsers.
 */
public class LoginController {

    private static ArrayList<User> activeUsers;

    public LoginController(){
        activeUsers = new ArrayList<>();
    }

    public static void makeActive(User user){
        activeUsers.add(user);
    }

    public static ArrayList<User> getActiveUsers(){
        return activeUsers;
    }

    @FXML
    private TextField loginUserField;

    @FXML
    private PasswordField loginPassField;

    @FXML
    private Label loginStatus;


    @FXML
    public void newUser() throws IOException{
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("/sample/view/createUser_view.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void login() throws IOException {

        for(User u : activeUsers){
            if(loginUserField.getText().equals(u.getUsername()) && loginPassField.getText().equals(u.getPassword())){
                mainWindowController.setCurrentUser(u);
                loginStatus.setText("Login Successful");
                Stage stage;
                Parent root;

                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("/sample/view/mainWindow_view.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            }else{
                System.out.println("grate suces");
                loginStatus.setText("ERROR 04; Login Failed");
            }
        }

    }
}
