import java.io.*;
import java.util.ArrayList;

// de to parse metoder læser .txt-filer linje for linje og spytter felter ud (title, year, rating osv.)
public class TxtParser{

    public static void main(String[] args) {
        try {
            parseSeries();
        }catch(Exception e){
            e.printStackTrace(); //læs op på hvad det her gør, fundet fra nettet.
        }
    }

    public static void parseMovies() throws Exception {
        File f = new File("resources/movies.txt"); //fortæller java hvor .txt-filen er <-- for at køre på windows: skift fra "resources/movies.txt" til "resources\\movies.txt". Spørg ikke hvorfor...
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine(); //line = én linje i .txt filen

        //ArrayList<Movie> burde nok flyttes til en anden klasse med en metode addMovie(), der kaldes i while-løkken
        ArrayList<Movie> movieList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            // kører så længe der er flere lines i .txt-filen

            String[] parts = line.split("; "); //splitter linje ved alle "; " og indsætter tekstbidder i et String[]
            
            String title = parts[0]; //index i, da det er første stykke String på linjen

            String yearString = parts[1]; //+1 da det kommer efter title
            int year = Integer.parseInt(yearString); //omdanner String year til en int

            String[] genres = parts[2].split(", "); //splitter strengen med genrer ved ", " og indsætter tekstbidder i et nyt String[]

            String ratingString = parts[3].replace(",", ".").replace(";", ""); //ændrer komma til punktum, således at strengen kan parses til en double; fjerner semikolon fra strengen
            double rating = Double.parseDouble(ratingString);

            Movie movie = new Movie(title, year, rating);
            for (String s : genres){
                movie.addCategory(s);
            }
            movieList.add(movie);
        } reader.close();

        // System.out.println() for test purposes
        for (Movie m : movieList){
            System.out.print(m.getTitle() +": "+m.getYear()+": ");

            for(int i=0; i < m.numberOfCategories(); i++){
                System.out.print(m.getCategory(i) + ", ");
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

            int startYear = 0;
            int endYear = 0;
            
            /* fix dis

            if(parts[1].contains("-1") || parts[1].contains("-2")){
                String[] years = parts[1].split("-");
                startYear = Integer.parseInt(years[0]);
                endYear = Integer.parseInt(years[1]);
            } else {
                String[] years = parts[1].split("-");
                startYear = Integer.parseInt(years[0]);
            }
            */

            /* TEST TEST TEST
            String[] genres = parts[2].split(", ");

            String ratingString = parts[3].replace(",", ".").replace(";","");
            double rating = Double.parseDouble(ratingString);

            String[] seasons = parts[4].split(", ");

            int numberOfSeasons = 0; 
            for(String s : seasons){ //loop der tæller antal seasons
                numberOfSeasons++;
            }
            */

            Series series = new Series(title, startYear, endYear, 0, 0);

            /* TEST TEST TEST
            for (String s : genres){
                serie.addCategory(s);
            }
            */

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

            for(Series s : seriesList){
                System.out.println(s.title);
            }

        }reader.close();
    }
}