package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.User;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

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
    private Button newUserButton;

    @FXML
    public void newUser() throws IOException{
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("/sample/view/createUser_view.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void login(javafx.event.ActionEvent event) throws IOException {
        //activeUsers.addAll(CreateUserController.getUsers());

        for(User u : activeUsers){
            if(loginUserField.getText().equals(u.getUsername()) && loginPassField.getText().equals(u.getPassword())){
                Controller.setCurrentUser(u);

                /*
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //Lukker login-vinduet
                // for at kunne skifte bruger igen: login-vindue skal kaldes fra mainWindows menubar -> change user?

                 */
                loginStatus.setText("Login Successful");
                Stage stage;
                Parent root;

                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("/sample/view/mainWindow_view.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            }else{
                System.out.println("grate suces");
                loginStatus.setText("Login Failed");
            }
        }

        /*
        if (loginUserField.getText().equals("user") && loginPassField.getText().equals("password")){
            loginStatus.setText("Login Successful");
            Parent loginPage = FXMLLoader.load(getClass().getResource("login_view.fxml"));
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
            //root.setStyle("-fx-background-image: url('../../resources/Background.jpg')");
            Scene scene = new Scene(root);
            primaryStage.setTitle("RickFlix");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } else{
            loginStatus.setText("Login Failed");
        }

         */
    }
}
