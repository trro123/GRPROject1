package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.*;

import javafx.fxml.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;

/**
 * @author Trond Rossing
 * @author Mikkel Lippert
 * @author Veronika Raagaard Skotting
 * MainWindowController har felterne:
 * en MediaContainer- kaldet medias.
 * et Watchable-objekt, selected, som repræsenterer valgte Movie/Series-objekt.
 * samt et User-objekt, currentUser, som repræsenter den User som er logget ind.
 */
public class mainWindowController {
    private MediaContainer medias;
    private Watchable selected;
    private static User currentUser;
    /**
     * Constructor opretter en MainWindowController opretter en MediaContainer og sætter denne til medias.
     * den forsøger at kører MediaContainer-klassens metoder loadSeries og LoadMovies
     * Hvis ikke dette er muligt kastes en Exception, og kalder printStackTrace-metoden på Exceptionen.
     * Slutteligt samler den de to ArrayLister til én samlet, vha. joinLists-metoden.
     */
    public mainWindowController() {
        this.medias = new MediaContainer();

        try {
            medias.loadSeries();
            medias.loadMovies();
        } catch (Exception e) {
            e.printStackTrace();
        }
        medias.joinLists();
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    @FXML
    private ScrollPane mediasPane;

    @FXML
    private TilePane grid;

    @FXML
    private TextField titleSearch;

    @FXML
    private Button playButton;

    @FXML
    private Button watchlistAddButton;

    @FXML
    private Button watchlistRemoveButton;

    @FXML
    private TextFlow infoText;

    @FXML
    private ImageView imageBox;

    // søgemetoder

    private boolean searchAll;
    private boolean searchMovies;
    private boolean searchSeries;

    /**
     * Den overordnede søgemetoder.
     * kører Gridpane, grids, clear metode for alle indestående elementer.
     * sætter viewingWatchList til at være falsk, da man ikke kan søge i watchlist.
     * herefter sættes grid til at ligge i mediasPane.
     * genreChooser MenuButton's tekst sættes til "Choose genre".
     * If-statement køres hvor der gennemløbes tre booleans, som sættes i searchSeries, searchMovies og searchAll-metoderne.
     * efterfølgende køres et for-loop med passende Watchable element.
     * Dette gøres ved et instance of statement.
     * slutteligt køres toRuleThemAll-metoden for dette passende Watchable element.
     */
    public void search() {
        grid.getChildren().clear();
        viewingWatchlist = false;
        mediasPane.setContent(grid);
        genreChooser.setText("Choose genre");

        if (searchAll && !searchMovies && !searchSeries) {

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                toRuleThemAll(m);
            }
        } else if (searchMovies) {

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                if (m instanceof Movie) {
                    toRuleThemAll(m);
                }
            }
        } else if (searchSeries) {

            for (Watchable m : medias.searchTitle(titleSearch.getText())) {
                if (m instanceof Series) {
                    toRuleThemAll(m);
                }
            }
        }
    }

    public void searchAll() {
        searchMovies = false;
        searchSeries = false;
        searchAll = true;
        search();
    }

    public void searchMovies() {
        searchAll = false;
        searchSeries = false;
        searchMovies = true;
        search();
    }

    public void searchSeries() {
        searchAll = false;
        searchMovies = false;
        searchSeries = true;
        search();
    }

    /**
     * Denne lukker nuværende Stage vha. getSource().getScene().getWindow().
     * getSource() returnerer hvad der trykkes på.
     * getScene() returnerer sourcens Scene.
     * getWindow() returnerer ovenstående Scenes nuværende vindue.
     * herefter lukkes usynliggøres dette vindue vha. Stage-metoden hide()
     * @param event: et ActionEvent, som fortæller hvilket Stage der er i brug.
     */
    public void logout(ActionEvent event){
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
    }

    /**
     * opretter en Stage, kaldet stage.
     * opretter en Parent, hvori den vha.FXMLLoader-klassen load-metode, henter CreateUser Stagen.
     * herefter sættes Stagens Scene til at være ovenævnte Stage.
     * initModality-metoden, blokerer for input til alle andre Stages i programmet
     * showAndWait blokerer for MainWindow indtil createUser lukkes igen.
     * @throws IOException: kaster IOException.
     */
    public void newUser() throws IOException{
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("/sample/view/createUser_view.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    // watchlist

    private boolean viewingWatchlist;

    public void showWatchlist() {
        grid.getChildren().clear();
        mediasPane.setContent(grid);

        for (Watchable m : currentUser.getWatchlist()) {
            toRuleThemAll(m);
        }

        viewingWatchlist = true;
    }

    public void watchlistAdd() throws AlreadyOnWatchlistException {

        if (currentUser.getWatchlist().contains(selected)){
            throw new AlreadyOnWatchlistException("Already on watchlist");
        }

        currentUser.addToWatchlist(selected);
        if (viewingWatchlist) {
            showWatchlist();
        }

        watchlistRemoveButton.setVisible(true);
    }

    public void watchlistRemove(){
        //tilføj MovieNotOnWatchlistException
        currentUser.removeFromWatchlist(selected);
        if(viewingWatchlist){
            showWatchlist();
        }
        watchlistRemoveButton.setVisible(false);
    }


    // GUDE metoden
    // (laver en kasse med et media object m og tilføjer den til TilePane)

    /**
     *
     * @param m: repræsenterer et watchable objekt.
     *  Der oprettes en billedefil og et ImageView.
     *  billedfilen indsættes i ImageView.
     *  En text for film oprettes, indeholdende passende data fra Watchables accessor metoder.
     *  ImageViewet får en setOnMouseClicked metode, som sætter Watchable-objektet til at være valgt element.
     *  Watchlist knap samt PlayButton sættes synlige og det valgte billede sættes i et prædefineret ImageView.
     *  Der tilføjes tekst, og der skelnes mellem Movie og Series objekter vha. et instanceof statement.
     *  For serier oprettes der to MenuButtons, en for Season og en for Episodes.
     *  der oprettes et antal MenuItems i MenuBottenen, tilhørende Seasons vha. For-loop som gennemløber Series-objektet
     *  vha. Series getSeasons metode.
     *  ved tryk på et af disse MenuItems oprettes en eventhandler, som kører et indre for-loop, med det ydre forloops int som argument.
     *  Dette for-loop har til ansvar at oprette x antal MenuItems for x antal episode i valgte sæson.
     *  Herefter tjekker den vha. getWatchlist() om valgte medie er på watchlist.
     *  hvis den er, sættes watchListButton synlig, ellers usynlig.
     *  slutteligt oprettes et tooltip, som printer information om mediet, hvis man holder musen over objektet.
     */
    public void toRuleThemAll(Watchable m) { //bedste metode i verdenen :)
        File file = new File("resources/movie_pictures/" + m.getTitle() + ".jpg"); //henter vores billeder.
        Image image = new Image(file.toURI().toString());   //opretter et billede med ovenstående fil

        ImageView imgv = new ImageView(); //opretter Imageview, til at vise billedet i.
        imgv.setImage(image);  //sætter billedet ind i Imageviewet.
        imgv.setFitWidth(140); //sætter størrelsen af Imageview.
        imgv.setPreserveRatio(true);

        Text text = new Text(m.getTitle() + "\n"  //Her oprettes teksten (for film kun), som skrives under valgte billede.
                + "Rating: " + m.getRating() + " / 10" + "\n"
                + m.getGenres() + "\n"
                + m.getYear() + "\n"
                + "\n");

        imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selected = m;
                watchlistAddButton.setVisible(true);
                playButton.setVisible(true);

                imageBox.setImage(image);

                infoText.getChildren().clear();
                if (m instanceof Movie){
                    infoText.getChildren().add(text);
                }
                if(m instanceof Series){ // her når vi til serier, de er anderledes da de har et endyear og seasons + episodes.
                    Text text2 = new Text((m.getTitle() + "\n"
                            + "Rating: " + m.getRating() + " / 10" + "\n"
                            + m.getGenres() + "\n"
                            + m.getYear() + " - "+ ((Series) m).getEndYearString()
                            + "\n"));
                    infoText.getChildren().add(text2);

                    MenuButton seasonButton = new MenuButton("Choose season");
                    infoText.getChildren().add(seasonButton);
                    MenuButton episodeChooser = new MenuButton(); //her oprettes vores Menubutton, som vi har lagt ved siden af sæsonChooser.
                    infoText.getChildren().add(episodeChooser); //og her tilføjer vi så knappen til vores infotext-felt, som er under billedet.


                    for(int i=0; i < ((Series) m).getSeasons(); i++){ //her køres et for-loop som gennemkøres for antallet af sæsoner en serie har.
                        MenuItem seasonItem = new MenuItem();
                        seasonItem.setText("Season: " + (i+1)); //her sættes teksten til at være i+1 (da array starter på 0. plads vil sæson 1 være 0+1.
                        seasonButton.getItems().add(seasonItem); //tilføjer seasonitems (altså hver af de foreskellige sæsoner) til sæsonknappen.
                        int finalI = i;

                        EventHandler seasonClickEvent = event -> { // Dette er eventhandleren, som initialiseres når man vælger en sæson
                            seasonButton.setText(seasonItem.getText()); //den sætter sæsonknappens tekst til at være den valgte sæsons tekst.
                            episodeChooser.getItems().clear(); //fjerner alle episodeItems, så den kan putte ''friske'' ind efter
                            episodeChooser.setText("Episode 1");
                            for (int j = 0; j < ((Series) m).getEpisodeCount(finalI); j++){ //her gennemgår for-loopet antallet af afsnit, for den sæson som det ydre(int i loopet)
                                //er i gang med at gennemgå.
                                MenuItem episodeItem = new MenuItem("Episode: " + (j+1)); //her opretter den Menuitems for hver episode.
                                EventHandler episodeEvent = event1 -> episodeChooser.setText(episodeItem.getText()); //når man trykker på episoden, sætter den menuButtonens tekst til at være valgte episode.

                                episodeChooser.getItems().add(episodeItem); //her tilføjer den de afsnit som er i sæsonen, til menuButton episodeChooser.
                                episodeItem.setOnAction(episodeEvent); //her sætter vi at det er for alle ''afsnit'' som skal køre ovenståene episodeEvent.

                            }

                        };
                        seasonItem.setOnAction(seasonClickEvent);

                    }
                    episodeChooser.setText("Episode: 1");
                }

                if(currentUser.getWatchlist().contains(selected)){
                    watchlistRemoveButton.setVisible(true);
                } else {
                    watchlistRemoveButton.setVisible(false);
                }
            }
        });
        Tooltip.install(imgv, new Tooltip(m.getTitle() + "\n"
                + "Rating: " + m.getRating() + " / 10" + "\n"
                + m.getGenres() + "\n"
                + m.getYear() + "\n"));

        HBox imgbox = new HBox();
        imgbox.getChildren().add(imgv);
        grid.getChildren().add(imgbox);
    }

    public void play(){

    }

    @FXML
    private MenuButton genreChooser;

    // Eventhandling til genre-knapperne herfra og til slut i denne klasse. Lad være med at kigge på det, lad være med at røre ved det. Tak.
    // if it looks stupid but it works, it ain't stupid.

    /**
     * 1 metode pr. genre.
     * rydder Gridpanet grid
     * if-statement gennemløber booleans searchAll, searchMovies og searchSeries.
     * instanceof statement definerer hvilke Watchable objekter toRuleThemAll skal kaldes på.
     * kalder ArrayLists contains metode med passende genrenavn som argument på Watchable objekt m.
     * kalder toRuleThemAll på m.
     * Kalder MenuButtons setText metode, sætter teksten til valgte genrenavn.
     */
    public void actionButton(){
        grid.getChildren().clear();
        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Action")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Action")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Action")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Action");
    }

    public void adventureButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Adventure")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Adventure")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Adventure")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Adventure");
    }

    public void animationButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Animation")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Animation")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Animation")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Animation");
    }

    public void biographyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Biography")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Biography")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Biography")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Biography");
    }

    public void comedyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Comedy")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Comedy")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Comedy")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Comedy");
    }

    public void crimeButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Crime")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Crime")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Crime")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Crime");
    }

    public void documentaryButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Documentary")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Documentary")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Documentary")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Documentary");
    }


    public void dramaButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Drama")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Drama")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Drama")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Drama");
    }

    public void familyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Family")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Family")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Family")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Family");
    }

    public void fantasyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Fantasy")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Fantasy")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Fantasy")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Fantasy");
    }

    public void filmnoirButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("FilmNoir")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("FilmNoir")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("FilmNoir")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Film-Noir");
    }

    public void historyButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("History")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("History")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("History")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("History");
    }

    public void horrorButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Horror")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Horror")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Horror")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Horror");
    }

    public void musicButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Music")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Music")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Music")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Music");
    }

    public void musicalButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Musical")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Musical")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Musical")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Musical");
    }

    public void mysteryButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Mystery")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Mystery")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Mystery")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Mystery");
    }

    public void romanceButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Romance")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Romance")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Romance")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Romance");
    }

    public void scifiButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("SciFi")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("SciFi")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("SciFi")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Sci-Fi");
    }

    public void sportButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Sport")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Sport")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Sport")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Sport");
    }

    public void talkshowButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Talkshow")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Talkshow")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Talkshow")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Talk-show");
    }

    public void thrillerButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Thriller")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Thriller")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Thriller")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Thriller");
    }

    public void warButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("War")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("War")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("War")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("War");
    }

    public void westernButton(){
        grid.getChildren().clear();

        for (Watchable m : medias.getJoinedList()){
            if (searchAll) {
                if (m.getGenres().contains("Western")) {
                    toRuleThemAll(m);
                }
            }
            else if (searchMovies){
                if (m instanceof Movie){
                    if (m.getGenres().contains("Western")){
                        toRuleThemAll(m);
                    }
                }
            }
            else if (searchSeries){
                if (m instanceof Series){
                    if (m.getGenres().contains("Western")){
                        toRuleThemAll(m);
                    }
                }
            }
        }
        genreChooser.setText("Horror");
    }
}
