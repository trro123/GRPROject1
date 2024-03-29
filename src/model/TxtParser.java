package model;

import java.io.*;

// de to parse metoder læser .txt-filer linje for linje og spytter felter ud (title, year, rating osv.)
public class TxtParser{

    public static void parseMovies() throws IOException {
        File f = new File("resources/movies.txt"); //fortæller java hvor .txt-filen er <-- for at køre på windows: skift fra "resources/movies.txt" til "resources\\movies.txt". Spørg ikke hvorfor...
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine(); //line = én linje i .txt filen

        while ((line = reader.readLine()) != null) { // kører så længe der er flere linjer i .txt-filen

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
    }


    public static void parseSeries() throws IOException {
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
                Integer episodeCount = Integer.parseInt(ep[1].replace(";", ""));
                series.addEpisodeCount(episodeCount);
            }

        }reader.close();
    }
}