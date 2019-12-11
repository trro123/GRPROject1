package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField loginUserField;

    @FXML
    private PasswordField loginPassField;

    @FXML
    private Label loginStatus;

    public void login(javafx.event.ActionEvent actionEvent) throws IOException {
        if (loginUserField.getText().equals("user") && loginPassField.getText().equals("password")){
            loginStatus.setText("Login Successful");
            Parent loginPage = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();

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
