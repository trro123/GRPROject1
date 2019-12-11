package sample.controller;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.model.*;

import javafx.fxml.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    private MediaContainer medias;
    private Watchable selected;
    private ArrayList<User> users;
    private User currentUser;
    @FXML
    private TilePane grid;

    @FXML
    private ScrollPane mediasPane;

    @FXML
    private TextField titleSearch;

    @FXML
    private Button watchlistAddButton;

    @FXML
    private Button playButton;

    @FXML
    private TextFlow infoText;

    @FXML
    private ImageView imageBox;

    public Controller() {
        users = new ArrayList<>();
        this.medias = new MediaContainer();
        try {
            medias.loadSeries();
            medias.loadMovies();
        } catch (Exception e) {
            e.printStackTrace();
        }
        medias.joinLists();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void search() {
        grid.getChildren().clear();
        mediasPane.setContent(grid);


        if (searchAll == true && searchMovies == false && searchSeries == false) {

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                toRuleThemAll(m);
            }
        } else if (searchMovies == true) {

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                if (m instanceof Movie) {
                    toRuleThemAll(m);
                }
            }
        } else if (searchSeries == true) {

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                if (m instanceof Series) {
                    toRuleThemAll(m);
                }
            }
        }

    }


    private boolean searchAll;
    private boolean searchMovies;
    private boolean searchSeries;

    public void searchAll() {

        grid.getChildren().clear();

        mediasPane.setContent(grid);

        for (Watchable m : medias.getJoinedList()) {
            toRuleThemAll(m);
        }

        searchMovies = false;
        searchSeries = false;

        searchAll = true;
    }

    public void searchMovies() {

        grid.getChildren().clear();


        mediasPane.setContent(grid);


        for (Movie m : medias.getMovies()) {
            toRuleThemAll(m);
        }

        searchAll = false;
        searchSeries = false;

        searchMovies = true;
    }

    public void searchSeries() {


        grid.getChildren().clear();

        mediasPane.setContent(grid);


        for (Series m : medias.getSeries()) {
            toRuleThemAll(m);
        }

        searchAll = false;
        searchMovies = false;

        searchSeries = true;
    }

    @FXML
    private MenuButton genreChooser;
    @FXML
    private MenuItem lilChooser;
    public void chooseGenre() {
        grid.getChildren().clear();


        mediasPane.setContent(grid);


        for (Watchable m : medias.getJoinedList()) {
            if (m.getGenres().contains()) {
                toRuleThemAll(m);
            }
            /*else if(m.getGenres().contains("Adventure")){
            toRuleThemAll(m);
            }

             */
        }
    }

    public void homescreen() {
        // homescreen() er en fuldstændig anden metode lige nu - laver en user og printer noget gøgl

        User user = new User("admin", "password");
        Controller controller = new Controller();
        controller.addUser(user);


        if (controller.getUsers().contains(user)) {
            System.out.println("jads");
        }

        currentUser = user;
        currentUser.addToWatchlist(medias.getMovies().get(0));

        for (Watchable m : currentUser.getWatchlist()) {
            System.out.println(m.getTitle());
        }
    }

    public void watchlistAdd() {
        //tilføj MovieAlreadyOnWatchlistException
        System.out.println("Added " + selected.getTitle() + " to watchlist.");
        currentUser.addToWatchlist(selected);
    }

    public void showWatchlist() {

        grid.getChildren().clear();


        mediasPane.setContent(grid);

        for (Watchable m : currentUser.getWatchlist()) {

            toRuleThemAll(m);
        }
    }


    public void toRuleThemAll(Watchable m) { //bedste metode i verdenen :)
        File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg");
        Image image = new Image(file.toURI().toString());

        ImageView imgv = new ImageView();
        imgv.setImage(image);
        imgv.setFitWidth(140);
        imgv.setPreserveRatio(true);
        Text text = new Text(m.getTitle() + "\n" + "Rating: " + m.getRating() + " / 10" + "\n"
                + m.getGenres() + "\n" + m.getYear());
        imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                watchlistAddButton.setVisible(true);
                playButton.setVisible(true);

                imageBox.setImage(image);

                infoText.getChildren().clear();
                infoText.getChildren().add(text);

                selected = m;
            }
        });

        Tooltip.install(imgv, new Tooltip(m.getTitle() + "\n" + "Rating: " + m.getRating() + " / 10" + "\n"
                + m.getGenres() + "\n" + m.getYear()));

        HBox imgbox = new HBox();

        imgbox.getChildren().add(imgv);

        grid.getChildren().add(imgbox);
    }
}
