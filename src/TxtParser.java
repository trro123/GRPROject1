import java.io.*;
import java.util.ArrayList;

// de to parse metoder læser .txt-filer linje for linje og spytter felter ud (title, year, rating osv.)
public class TxtParser{

    public static void main(String[] args) {
        try {
            parseSeries();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void parseMovies() throws Exception {
        File f = new File("resources/movies.txt"); //fortæller java hvor .txt-filen er <-- for at køre på windows: skift fra "resources/movies.txt" til "resources\\movies.txt". Spørg ikke hvorfor...
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine(); //line = én linje i .txt filen

        //ArrayList<Movie> burde nok flyttes til en anden klasse med en metode addMovie(), der kaldes i while-løkken
        ArrayList<Movie> movieList = new ArrayList<>();

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
            movieList.add(movie);
        } reader.close();

        // System.out.println() for test purposes
        for (Movie m : movieList){
            System.out.print(m.getTitle() +": "+m.getYear()+": ");

            for(int i=0; i < m.numberOfGenres(); i++){
                System.out.print(m.getGenre(i) + ", ");
            }
            System.out.print(": " + m.getRating());
            System.out.println();
        }
    }


    public static void parseSeries() throws Exception {
        File f = new File("resources/series.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();

        // igen, listen af serier burde nok flyttes til en anden klasse
        ArrayList<Series> seriesList = new ArrayList<>();
        
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

            int numberOfSeasons = 0; 
            for(String s : seasons){ //loop der tæller antal seasons
                numberOfSeasons++;
            }

            Series series = new Series(title, startYear, endYear, rating, numberOfSeasons, genres);

            seriesList.add(series);

            // OBS under construction
            // loop der tilføjer antal afsnit for hver sæson til en ArrayList
            // f.eks. vil episodes[0] returnere antal afsnit i første sæson, episodes[1] i anden osv.
            /*
            ArrayList<Integer> episodes = new ArrayList<>();
            for(String s : seasons){
                String[] ep = s.split("-");
                Integer episodeCount = Integer.parseInt(ep[1]);
                episodes.add(episodeCount);
            }
            */

        }reader.close();

        // System.out.println() for test purposes
        for(Series s : seriesList){
            System.out.print(s.getTitle() + s.getYear() + s.getEndYear() + s.getRating() + s.getSeasons());
            for(int i=0; i < s.numberOfGenres(); i++){
                System.out.print(s.getGenre(i));
            }
            System.out.println();
        }
    }
}