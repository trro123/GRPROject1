package view;

import view.*;
import model.*;
import controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.InvocationEvent;
import java.lang.reflect.InvocationTargetException;

public class Main extends Application {

    //bruges til at starte programmet når vi engang når så langt :)
    @Override
    public void start(Stage primaryStage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("SceneGUI.fxml"));
            primaryStage.setTitle("RickFlix");
            primaryStage.setScene(new Scene(root, 800, 700));
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