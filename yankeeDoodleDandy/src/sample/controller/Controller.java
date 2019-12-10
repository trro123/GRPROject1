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

public class Controller  {

    @FXML
    private ScrollPane mediasPane;

    @FXML
    private TilePane grid;

    private MediaContainer medias;

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

        if(searchAll == true && searchMovies == false && searchSeries == false){

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
        } else if (searchMovies == true){

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                if(m instanceof Movie){
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

        } else if (searchSeries == true){

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                if(m instanceof Series){
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
        // grid.setHgap(5);

        mediasPane.setContent(grid);

        medias.joinLists();

        if(searchAll == true && searchMovies == false && searchSeries == false){

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
                        System.out.println(m.getGenres());
                    }
                });

                HBox imgbox = new HBox();

                imgbox.getChildren().add(imgv);

                grid.getChildren().add(imgbox);
            }
        } else if (searchMovies == true){

            for (Watchable m : medias.searchGenre(genreSearch.getText())) {
                if(m instanceof Movie){
                    File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");
                    Image image = new Image(file.toURI().toString());

                    ImageView imgv = new ImageView();
                    imgv.setImage(image);
                    imgv.setFitWidth(140);
                    imgv.setPreserveRatio(true);

                    imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println(m.getGenres());
                        }
                    });

                    HBox imgbox = new HBox();

                    imgbox.getChildren().add(imgv);

                    grid.getChildren().add(imgbox);
                }
            }

        } else if (searchSeries == true){

            for (Watchable m : medias.searchGenre(genreSearch.getText())) {
                if(m instanceof Series){
                    File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");
                    Image image = new Image(file.toURI().toString());

                    ImageView imgv = new ImageView();
                    imgv.setImage(image);
                    imgv.setFitWidth(140);
                    imgv.setPreserveRatio(true);

                    imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println(m.getGenres());
                        }
                    });

                    HBox imgbox = new HBox();

                    imgbox.getChildren().add(imgv);

                    grid.getChildren().add(imgbox);
                }
            }
        }

    }

    private boolean searchAll;
    private boolean searchMovies;
    private boolean searchSeries;

    public void searchAll(){
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

        searchMovies = false;
        searchSeries = false;

        searchAll = true;
    }

    public void searchMovies(){

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

        for (Movie m : medias.getMovies()) {
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

        searchAll = false;
        searchSeries = false;

        searchMovies = true;
    }

    public void searchSeries(){

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

        for (Series m : medias.getSeries()) {
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

        searchAll = false;
        searchMovies = false;

        searchSeries = true;
    }

    public void homescreen(){
        System.out.println("VIS MIG EN HOMESCREEN!!!!!!!!!!! !! ! /!!!!(/&");
        System.out.println("... og kald på den når man trykker login");
    }
}
