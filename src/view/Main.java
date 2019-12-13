package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            root.setStyle("-fx-background-image: url('./Background.jpg')");
            Scene scene = new Scene(root);
            primaryStage.setTitle("RickFlix");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }

}