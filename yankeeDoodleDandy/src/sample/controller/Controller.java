package sample.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import sample.model.*;

import javafx.fxml.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import java.io.File;

public class Controller  {

    @FXML
    private ScrollPane mediasPane;

    @FXML
    private Button button;

    public void handleButton(){

        button.setText("herp");

        MediaContainer medias = new MediaContainer();
        try{
            medias.loadMovies();
            medias.loadSeries();
        }catch(Exception e){
            e.printStackTrace();
        }

        TilePane grid = new TilePane();
        grid.setPrefColumns(5);

        mediasPane.setContent(grid);

        medias.joinLists();

        for(Watchable m : medias.getJoinedList()){
            File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");

            Image image = new Image(file.toURI().toString());
            ImageView imgv = new ImageView();
            imgv.setImage(image);
            imgv.setFitWidth(140);
            imgv.setPreserveRatio(true);

            HBox imgbox = new HBox();
            imgbox.getChildren().add(imgv);

            grid.getChildren().add(imgbox);
        }

    }

    @FXML
    private TextField searchField;

    public void search(){

        MediaContainer medias = new MediaContainer();
        try{
            medias.loadMovies();
            medias.loadSeries();
        }catch(Exception e){
            e.printStackTrace();
        }

        TilePane grid = new TilePane();
        grid.setPrefColumns(5);

        mediasPane.setContent(grid);

        medias.joinLists();

        for(Watchable m : medias.searchTitle(searchField.getText())){
            File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");
            Image image = new Image(file.toURI().toString());

            ImageView imgv = new ImageView();
            imgv.setImage(image);
            imgv.setFitWidth(140);
            imgv.setPreserveRatio(true);

            HBox imgbox = new HBox();
            imgbox.getChildren().add(imgv);

            grid.getChildren().add(imgbox);
        }

    }

}
