package sample.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import sample.model.*;

import javafx.fxml.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.io.File;

public class Controller {

    private MediaContainer medias;
    private TilePane grid;

    public void init(){
        medias = new MediaContainer();
        try {
            medias.loadMovies();
            medias.loadSeries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        grid = new TilePane();
        grid.setPrefColumns(5);

        mediasPane.setContent(grid);

        medias.joinLists();
    }

    @FXML
    private ScrollPane mediasPane;

    @FXML
    private Button button;

    public void handleButton() {

        button.setText("herp");

        MediaContainer medias = new MediaContainer();
        try {
            medias.loadMovies();
            medias.loadSeries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TilePane grid = new TilePane();
        grid.setPrefColumns(5);

        mediasPane.setContent(grid);

        medias.joinLists();

        for (Watchable m : medias.getJoinedList()) {
            File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");

            Image image = new Image(file.toURI().toString());
            ImageView imgv = new ImageView();
            imgv.setImage(image);
            imgv.setFitWidth(140);
            imgv.setPreserveRatio(true);

            imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(m.getTitle());
                }
            });

            Tooltip.install(imgv, new Tooltip(m.getTitle()));

            HBox imgbox = new HBox();
            imgbox.getChildren().add(imgv);

            grid.getChildren().add(imgbox);
        }

    }

    @FXML
    private TextField titleSearch;

    public void search() {

        MediaContainer medias = new MediaContainer();
        try {
            medias.loadMovies();
            medias.loadSeries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TilePane grid = new TilePane();
        grid.setPrefColumns(5);
        grid.setVgap(5);

        mediasPane.setContent(grid);

        medias.joinLists();

        for (Watchable m : medias.searchTitle(titleSearch.getText())) {
            File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");
            Image image = new Image(file.toURI().toString());

            ImageView imgv = new ImageView();
            imgv.setImage(image);
            imgv.setFitWidth(140);
            imgv.setPreserveRatio(true);

            imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(m.getTitle());
                }
            });

            HBox imgbox = new HBox();

            imgbox.getChildren().add(imgv);

            grid.getChildren().add(imgbox);
        }

    }

    @FXML
    private TextField genreSearch;

    public void searchGenre() {

        MediaContainer medias = new MediaContainer();
        try {
            medias.loadMovies();
            medias.loadSeries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TilePane grid = new TilePane();
        grid.setPrefColumns(5);
        grid.setVgap(5);

        mediasPane.setContent(grid);

        medias.joinLists();

        for (Watchable m : medias.searchGenre(genreSearch.getText())) {
            File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");
            Image image = new Image(file.toURI().toString());

            ImageView imgv = new ImageView();
            imgv.setImage(image);
            imgv.setFitWidth(140);
            imgv.setPreserveRatio(true);

            imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(m.getTitle());
                }
            });

            HBox imgbox = new HBox();

            imgbox.getChildren().add(imgv);

            grid.getChildren().add(imgbox);
        }

    }

}
