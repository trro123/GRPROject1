package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //bruges til at starte programmet når vi engang når så langt :)
    @Override
    public void start(Stage primaryStage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml")); //Skal være "Login.fxml", men det virker ikke
            root.setStyle("-fx-background-image: url('./Background.jpg')");
            Scene scene = new Scene(root);
            //primaryStage.setTitle("RickFlix");
            primaryStage.setScene(scene);
            //primaryStage.setResizable(false);
            primaryStage.show();


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