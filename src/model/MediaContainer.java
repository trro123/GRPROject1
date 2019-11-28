package model;

import java.util.ArrayList;

public class MediaContainer {
    protected static ArrayList<Series> series;
    protected static ArrayList<Movie> movies;

    public MediaContainer(){
        series = new ArrayList<>();
        movies = new ArrayList<>();
    }

    public static void addSeries(Series s){
        series.add(s);
    }
    
    public static void addMovie(Movie m){
        movies.add(m);
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