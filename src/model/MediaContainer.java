package model;

import java.util.*;

public class MediaContainer {
    private static ArrayList<Series> series;
    private static ArrayList<Movie> movies;
    private static ArrayList<Watchable> searchResults;

    public MediaContainer() {
        series = new ArrayList<>();
        movies = new ArrayList<>();
    }

    public static void addSeries(Series s) {
        series.add(s);
    }

    public static void addMovie(Movie m) {
        movies.add(m);
    }

    // filter/søge metoder

    public static void beforeYear(int year) { //søger efter alle film OG serier før år x og tilføjer til en ArrayList<Watchable> searchResults
        searchResults = new ArrayList<>();

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
    }

    public static void afterYear(int year){ //søger efter alle film OG serier efter år x
        searchResults = new ArrayList<>();

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
    }

    public static void searchTitle(String userInput){ //søger efter en brugerdefineret teksttreng - til en eventuel searchbar?
        searchResults = new ArrayList<>();

        for(Movie m : movies){
            if(m.getTitle().contains(userInput)){
                searchResults.add(m);
            }
        }

        for(Series s : series){
            if(s.getTitle().contains(userInput)){
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