package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout; //ved ikke hvorfor

    //bruges til at starte programmet når vi engang når så langt :)
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

    public static void showCreateUser() throws IOException {
        FXMLLoader l = new FXMLLoader();
        l.setLocation(Main.class.getResource("/view/CreateUser.fxml"));
        BorderPane newUser = l.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Create New User");
        //addDialogStage.initModality(Modality.WINDOW_MODAL); //skulle gøre det umuligt at klikke andre steder end vinduet, men det virker ikke.
        addDialogStage.initOwner(primaryStage); //det nye stage hører til vores primary stage
        Scene s = new Scene(newUser);
        addDialogStage.setScene(s);
        addDialogStage.showAndWait();
    }

    public static void main(String[] args) {

        launch(args);


        /*
        Hvad skal der ske når vi kører programmet? ..
        nedenstående er ikke tænkt som nogen endelig metode men nærmere en huskeliste
        
        MediaContainer mediaList = new MediaContainer();
        TxtParser.parseMovies();
        TxtParser.parseSeries();
        mediaList.loadMovieImages();
        mediaList.loadSeriesImages();


        */
    }

}