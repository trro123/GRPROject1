package sample.model;

import java.io.IOException;
import java.util.*;
/**
 * MediaContainer.java er et objekt der holder listerne med film, serier og en samlet med begge dele.
 * Det er disse lister der gennemløbes i de forskellige søgemetoder.
 * MediaContaineren initialiseres i mainWindows controller, altså når mainWindow vises.
 */
public class MediaContainer {
    private static ArrayList<Series> series;
    private static ArrayList<Movie> movies;

    private static ArrayList<Watchable> joined;

    public MediaContainer() {
        series = new ArrayList<>();
        movies = new ArrayList<>();
        joined = new ArrayList<>();
    }

// get methods

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public ArrayList<Series> getSeries(){
        return series;
    }

    public static ArrayList<Watchable> getJoinedList(){
        return joined;
    }

    public void joinLists(){
        joined.addAll(series);
        joined.addAll(movies);
    }

// mutator methods

    public static void addSeries(Series s) {
        series.add(s);
    }

    public static void addMovie(Movie m) {
        movies.add(m);
    }

    /**
     * LoadMovies() metoden kalder TxtParser-klassens parseMovies metode, som initialiserer Movie-objekter med værdier hentet fra .txt fil
     * se evt. TxtParser-klassens dokumentation for forklaring.
     * For-loop af Movie-objekter køres og Media-klassens setImg() metode køres, så alle Movie-objekter får et tilhørende billede.
     * @throws IOException: kaster en IOException
     */
    public void loadMovies() throws IOException {
        // metode der initialiserer movie objekterne med værdier fra .txt fil
        TxtParser.parseMovies();
        
        // loop der sætter billeder til movie objekterne. Billedet skal have samme navn som titlen på filmen.
        for(Movie m : movies){
            m.setImg();
        }
    }

    /** LoadSeries() metoden kalder TxtParser-klassens parseSeries metode, som initialiserer Movie-objekter med værdier hentet fra .txt fil
     * se evt. TxtParser-klassens dokumentation for forklaring.
     * Der køres et for-loop som gennemløber Series-objekter og kalder Media-klassens setImg()-metode
     *
     * @throws IOException: kaster IOException
     */
    public void loadSeries() throws IOException{
        // metode der initialiserer series objekterne med værdier fra .txt fil
        TxtParser.parseSeries();
        
        // loop der sætter billeder til series objekterne. Billedet skal have samme navn som titlen på serien.
        for(Series s : series){
            s.setImg();
        }
    }

// filter/search methods

    /**
     *  searchTitle() opretter en ArrayList af Watchable-objekter, kaldet searchResult
     *  Kører et for-loop for Movie-objekter, med et underliggende if-statement, som tjekker om input passer til en film-titel, og tilføjer Movie-objekter som opfylder dette til searchResults.
     *  Kører et for-loop for Series-objekter, med et underliggende if-statement, som tjekker om input passer til en film-titel. og tilføjer Series-objekter som opfylder dette til searchResults.
     * @param userInput: det input som tastes i søgefeltet.
     * @return ArrayListen searchResults af Watchable-objekter,
     */
    public static ArrayList<Watchable> searchTitle(String userInput) { //søger efter en brugerdefineret teksttreng (til en eventuel searchbar?)
        ArrayList<Watchable> searchResults = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
                searchResults.add(m);
            }
        }

        for (Series s : series) {
            if (s.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
                searchResults.add(s);
            }
        }

        return searchResults;
    }


// metoder der printer indholdet af listerne


// for test purposes

    public static void seriesParserTest(){

        for(Series s : series){
            System.out.print(s.getTitle() + s.getYear() + s.getEndYear() + s.getRating() + s.getSeasons());
            for(String g : s.getGenres()){
                System.out.println(g);
            }
            System.out.println();
        }
    }


    public static void movieParserTest(){
        
        for (Movie m : movies){
            System.out.print(m.getTitle() +": "+m.getYear()+": ");
            for(String g : m.getGenres()){
                System.out.println(g);
            }
            System.out.print(": " + m.getRating());
            System.out.println();
        }
    }
}