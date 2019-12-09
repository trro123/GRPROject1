package model;

import javafx.fxml.FXML;

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

    public void login(ActionEvent event){
        if (loginUserField.getText().equals("user") && loginPassField.getText().equals("password")){
            loginStatus.setText("Login Successful");
        } else{
            loginStatus.setText("Login Failed");
        }

    }
}
