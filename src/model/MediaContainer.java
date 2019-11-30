package model;

import java.io.IOException;
import java.util.*;

public class MediaContainer {
    private static ArrayList<Series> series;
    private static ArrayList<Movie> movies;
    private static ArrayList<Watchable> searchResults;

    public MediaContainer() {
        series = new ArrayList<>();
        movies = new ArrayList<>();
        searchResults = new ArrayList<>();
    }

    public static void addSeries(Series s) {
        series.add(s);
    }

    public static void addMovie(Movie m) {
        movies.add(m);
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public ArrayList<Series> getSeries(){
        return series;
    }

    public void loadMovies() throws IOException {
        // metode der initialiserer movie objekterne med værdier fra .txt fil
        TxtParser.parseMovies();
        
        // loop der sætter billeder til movie objekterne
        for(Movie m : movies){
            m.setImg("resources/movie_pictures/" + m.getTitle() + ".jpg");
        }
    }

    public void loadSeries() throws IOException{
        // metode der initialiserer series objekterne med værdier fra .txt fil
        TxtParser.parseSeries();
        
        // loop der sætter billeder til series objekterne
        for(Series s : series){
            s.setImg("resources/series_pictures/" + s.getTitle() + ".jpg");
        }
    }





    // filter/søge metoder - måske burde de returnere searchResults listen, det vil tiden vise

    public ArrayList<Watchable> searchBeforeYear(int year) { //søger efter alle film og serier før år x og tilføjer til en ArrayList<Watchable> searchResults
        searchResults.clear(); // .clear() arraylisten som det første, da den ellers ville blive latterligt langt efter flere søgninger

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
        searchResults.clear();

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

    public static void searchTitle(String userInput){ //søger efter en brugerdefineret teksttreng (til en eventuel searchbar?)
        searchResults.clear();

        for(Movie m : movies){
            if(m.getTitle().toLowerCase().contains(userInput.toLowerCase())){
                searchResults.add(m);
            }
        }

        for(Series s : series){
            if(s.getTitle().toLowerCase().contains(userInput.toLowerCase())){
                searchResults.add(s);
            }
        }
    }



    
    // metoder der printer indholdet af listerne
    // for test purposes

    public static void seriesParserTest(){

        for(Series s : series){
            System.out.print(s.getTitle() + s.getYear() + s.getEndYear() + s.getRating() + s.getSeasons());
            for(int i=0; i < s.numberOfGenres(); i++){
                System.out.print(s.getGenre(i));
            }
            System.out.println();
        }
    }


    public static void movieParserTest(){
        
        for (Movie m : movies){
            System.out.print(m.getTitle() +": "+m.getYear()+": ");
            for(int i=0; i < m.numberOfGenres(); i++){
                System.out.print(m.getGenre(i) + ", ");
            }
            System.out.print(": " + m.getRating());
            System.out.println();
        }
    }
}