package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public void loadMovies() throws IOException{
        // sætter et billede til alle movies i mediaContaineren baseret på filmens titel
        File f = new File("resources/movies.txt"); //fortæller java hvor .txt-filen er <-- for at køre på windows: skift fra "resources/movies.txt" til "resources\\movies.txt". Spørg ikke hvorfor...
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine(); //line = én linje i .txt filen

        while ((line = reader.readLine()) != null) { // line er den næste linje. Indtil vi løber tør for linjer
            // kører så længe der er flere lines i .txt-filen

            String[] parts = line.split("; "); //splitter linje ved alle "; " og indsætter tekstbidder i et String[]
            
            String title = parts[0];

            String yearString = parts[1]; //+1 da det kommer efter title
            int year = Integer.parseInt(yearString); //omdanner String year til en int

            String[] genres = parts[2].split(", "); //splitter strengen med genrer ved ", " og indsætter tekstbidder i et nyt String[]

            String ratingString = parts[3].replace(",", ".").replace(";", ""); //ændrer komma til punktum, således at strengen kan parses til en double; fjerner semikolon fra strengen
            double rating = Double.parseDouble(ratingString);

            Movie movie = new Movie(title, year, rating, genres);
            MediaContainer.addMovie(movie);

        } reader.close();
        
        // loop der initialiserer billeder
        for(Movie m : movies){
            m.setImg("resources/movie_pictures/" + m.getTitle() + ".jpg");
        }
    }

    public void loadSeries() throws IOException{
        File f = new File("resources/series.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();
        
        while ((line = reader.readLine()) != null) {
            
            String[] parts = line.split("; ");

            String title = parts[0];

            String[] years = parts[1].split("-");
            int startYear = Integer.parseInt(years[0]);
            int endYear;
            if(parts[1].contains("-1") || parts[1].contains("-2")) {
                endYear = Integer.parseInt(years[1]);
            } else {
                endYear = 0;
            }

            String[] genres = parts[2].split(", ");

            String ratingString = parts[3].replace(",", ".").replace(";","");
            double rating = Double.parseDouble(ratingString);

            String[] seasons = parts[4].split(", ");

            int numberOfSeasons = seasons.length;

            Series series = new Series(title, startYear, endYear, rating, numberOfSeasons, genres);

            MediaContainer.addSeries(series);

            // loop der tilføjer antal episoder i de forskellige sæsoner til Series objektet. Antal afsnit i en sæson tilgås med episodeCount(index) i Series klassen
            for(String s : seasons){
                String[] ep = s.split("-");
                Integer episodeCount = Integer.parseInt(ep[1]);
                series.addEpisodes(episodeCount);
            }

        }reader.close();
        
        // loop der initialiserer billeder
        for(Series s : series){
            s.setImg("resources/series_pictures/" + s.getTitle() + ".jpg");
        }
    }





    // filter/søge metoder - måske burde de returnere searchResults listen, det vil tiden vise

    public static void beforeYear(int year) { //søger efter alle film og serier før år x og tilføjer til en ArrayList<Watchable> searchResults
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
    }

    public static void afterYear(int year){ //søger efter alle film og serier efter år x
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
    }

    public static void searchTitle(String userInput){ //søger efter en brugerdefineret teksttreng (til en eventuel searchbar?)
        //kan ikke finde ud af de store og små bogstaver, f.eks. vil en søgning på "the godfather" ikke give noget resultat, da filmen hedder "The Godfather"
        searchResults.clear();

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

        // test
        for(Watchable m : searchResults){
            System.out.println(m.getTitle());
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