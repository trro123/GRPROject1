package sample.model;

import java.awt.*;
import java.io.IOException;
import java.util.*;

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

    public ArrayList<Watchable> getJoinedList(){
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

    public void loadMovies() throws IOException { //tror dette skal gøres modsat! tror for at scenebuilder kan forstå det, skal det være Imageobjekter.
        // metode der initialiserer movie objekterne med værdier fra .txt fil
        TxtParser.parseMovies();
        
        // loop der sætter billeder til movie objekterne. Billedet skal have samme navn som titlen på filmen.
        for(Movie m : movies){
            m.setImg();
        }
    }

    public void loadSeries() throws IOException{
        // metode der initialiserer series objekterne med værdier fra .txt fil
        TxtParser.parseSeries();
        
        // loop der sætter billeder til series objekterne. Billedet skal have samme navn som titlen på serien.
        for(Series s : series){
            s.setImg();
        }
    }

// filter/search methods

    public ArrayList<Watchable> searchBeforeYear(int year) { //søger efter alle film og serier før år x og tilføjer til en ArrayList<Watchable> searchResults
        ArrayList<Watchable> searchResults = new ArrayList<>();

        for(Movie m : movies){
            if(m.getYear() < year){
                searchResults.add(m);
            }
        }

        for(Series s : series){
            if(s.getYear() < year){
                searchResults.add(s);
            }
        }

        return searchResults;
    }

    public ArrayList<Watchable> searchAfterYear(int year){ //søger efter alle film og serier efter år x
        ArrayList<Watchable> searchResults = new ArrayList<>();

        for(Movie m : movies){
            if(m.getYear() > year){
                searchResults.add(m);
            }
        }

        for(Series s : series){
            if(s.getYear() > year){
                searchResults.add(s);
            }
        }

        return searchResults;
    }

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

        /*
        for(Watchable m : joined){
            if(m.getTitle().toLowerCase().contains(userInput.toLowerCase())){
                searchResults.add(m);
            }
        }

         */

        return searchResults;
    }

    public static ArrayList<Watchable> searchGenre(){
        ArrayList<Watchable> searchResults = new ArrayList<>();

        // :-)

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