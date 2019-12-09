package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginController {
    @FXML
    private Label loginUserField;
    @FXML
    private Label loginPassField;
    @FXML
    private Label loginButton;
    @FXML
    private Label loginStatus;

    public void login(ActionEvent event) throws Exception {
        if (loginUserField.getText().equals("user") && loginPassField.getText().equals("password")){
            loginStatus.setText("Login Successful");
            Stage primaryStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
            root.setStyle("-fx-background-image: url('../../resources/Background.jpg')");
            Scene scene = new Scene(root);
            primaryStage.setTitle("RickFlix");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } else{
            loginStatus.setText("Login Failed");
        }

    }
}
