import java.io.*;
import java.util.ArrayList;

// de to parse metoder læser .txt-filer linje for linje og spytter felter ud (title, year, rating osv.)
public class TxtParser{
    ArrayList titles = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            parseMovies();
        }catch(Exception e){
            e.printStackTrace(); //læs op på hvad det her gør, fundet fra nettet.
        }
    }

    public static void parseMovies() throws Exception {
        File f = new File("resources/movies.txt"); //fortæller java hvor .txt-filen er
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine(); //line = én linje i .txt filen

        //ArrayList burde nok flyttes til en anden klasse med en metode addMovie(), der kaldes i while-løkken
        ArrayList<Movie> movies = new ArrayList<>();

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
            movies.add(movie);
        } reader.close();

        // System.out.println() for test purposes
        for (Movie m : movies){
            System.out.print(m.getTitle() +": "+m.getYear()+": ");

            for(int i=0; i < m.numberOfCategories(); i++){
                System.out.print(m.getCategory(i) + ", ");
            }
            System.out.print(": " + m.getRating());
            System.out.println();
        }
    }



    public static void parseSeries() throws Exception {
        File f = new File("resources\\series.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();
        ArrayList<Series> series = new ArrayList<>();
        int i = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("; ");
            String title = parts[i];

            //der går noget galt her - den er fuldstændig på afveje. Kør main metode og se fejlbesked.
            int startYear = 0;
            int endYear = 0;
            if(parts[i+1].contains("-1") || parts[i+1].contains("-2")){
                String[] years = parts[i+1].split("-");
                startYear = Integer.parseInt(years[0]);
                endYear = Integer.parseInt(years[1]);
            } else {
                String[] years = parts[i+1].split("-");
                startYear = Integer.parseInt(years[0]);
            }




            String[] genres = parts[i+2].split(", ");

            String ratingString = parts[i+3].replace(",", ".").replace(";","");
            // CRASH; samme problem som i parseMovies()
            double rating = Double.parseDouble(ratingString);

            String[] seasons = parts[i+4].split(", ");

            int numberOfSeasons = 0; //loop der tæller antal seasons
            for(String s : seasons){
                numberOfSeasons++;
            }
            Series serie = new Series(title, startYear, endYear, rating, numberOfSeasons);
            for (String s : genres){
                serie.addCategory(s);
            }
            series.add(serie);
            i++;

            // loop der tilføjer antal afsnit for hver sæson til en ArrayList
            // f.eks. vil episodes[0] returnere antal afsnit i første sæson, episodes[1] i anden osv.
            /*ArrayList<Integer> episodes = new ArrayList<>();
            for(String s : seasons){
                String[] ep = s.split("-");
                Integer episodeCount = Integer.parseInt(ep[1]);
                episodes.add(episodeCount);
            }
            */



            for (Series m : series){
                System.out.print(m.getTitle() +": "+m.getYear()+": ");

                for(i=0; i < m.numberOfCategories(); i++){
                    System.out.print(m.getCategory(i) + ", ");
                }
                System.out.print(": " + m.getRating() +": " + m.getEndYear());
                System.out.println();
            }
        }reader.close();
    }
}