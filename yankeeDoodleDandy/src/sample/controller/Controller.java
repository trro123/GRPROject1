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


    // søge-metoder nedeunder

    public void search() {
        removeButton.setVisible(false);

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
        removeButton.setVisible(false);

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
        removeButton.setVisible(false);

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
        removeButton.setVisible(false);

        grid.getChildren().clear();

        mediasPane.setContent(grid);


        for (Series m : medias.getSeries()) {
            toRuleThemAll(m);
        }

        searchAll = false;
        searchMovies = false;
        searchSeries = true;
    }

    public void homescreen() {
        // homescreen() er en fuldstændig anden metode lige nu - laver en user og printer noget gøgl
        removeButton.setVisible(false);

        User user = new User("admin", "password");
        Controller controller = new Controller();
        controller.addUser(user);


        if (controller.getUsers().contains(user)) {
            System.out.println("jads");
        }

        currentUser = user;
        currentUser.addToWatchlist(medias.getMovies().get(99));

        for (Watchable m : currentUser.getWatchlist()) {
            System.out.println(m.getTitle());
        }
    }

    public void watchlistAdd() {
        //tilføj MovieAlreadyOnWatchlistException
        System.out.println("Added " + selected.getTitle() + " to watchlist.");
        currentUser.addToWatchlist(selected);
    }

    public void watchlistRemove(){
        System.out.println("Removed " + selected.getTitle() + " from watchlist.");
        currentUser.removeFromWatchlist(selected);
        showWatchlist();
    }

    @FXML
    private Button removeButton;

    public void showWatchlist() {
        removeButton.setVisible(true);

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






    // Eventhandling til genre-knapperne herfra og til slut i denne klasse. Lad være med at kigge på det, lad være med at røre ved det. Tak.
    // if it looks stupid but it works, it ain't stupid.
    public void actionButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Action")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Action")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Action")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void adventureButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Adventure")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Adventure")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Adventure")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void animationButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Animation")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Animation")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Animation")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void biographyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Biography")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Biography")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Biography")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void comedyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Comedy")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Comedy")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Comedy")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void crimeButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Crime")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Crime")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Crime")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void documentaryButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Documentary")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Documentary")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Documentary")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }


    public void dramaButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Drama")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Drama")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Drama")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void familyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Family")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Family")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Family")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void fantasyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Fantasy")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Fantasy")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Fantasy")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void filmnoirButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Film-Noir")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Film-Noir")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Film-Noir")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void historyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("History")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("History")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("History")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void horrorButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Horror")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Horror")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Horror")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void musicButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Music")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Music")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Music")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void musicalButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Musical")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Musical")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Musical")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void mysteryButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Mystery")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Mystery")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Mystery")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void romanceButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Romance")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Romance")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Romance")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void scifiButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Sci-fi")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Sci-fi")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Sci-fi")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void sportButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Sport")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Sport")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Sport")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void talkshowButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Talk-show")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Talk-show")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Talk-show")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void thrillerButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Thriller")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Thriller")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Thriller")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void warButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("War")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("War")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("War")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }

    public void westernButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll == true) {
                if (m.getGenres().contains("Western")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies == true){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Western")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries == true){
                if (m instanceof Series){
                    if (m.getGenres().contains("Western")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
    }
}
