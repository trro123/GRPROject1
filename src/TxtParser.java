import java.io.*;
import java.util.ArrayList;

// de to parse metoder læser .txt-filer linje for linje og spytter felter ud (title, year, rating osv.)
public class TxtParser{
    ArrayList titles = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            parseMovies();
        }catch(Exception i){
            System.out.println("trond");
        }
        }

    public static void parseMovies() throws Exception {
        File f = new File("resources\\movies.txt"); //fortæller java hvor .txt-filen er
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine(); //line = én linje i .txt filen
        int i = 0;
        // kører så længe der er flere lines i .txt-filen
        while ((line = reader.readLine()) != null) {

            String[] parts = line.split("; "); //splitter linjen ved alle "; " og indsætter tekstbidder i et String[]
            
            String title = parts[i];
            i= i++;
           // String yearString = parts[i+1];
           //  int year = Integer.parseInt(yearString); //omdanner String year til en int; crasher programmet hvis den læser andet end tal
           // String[] genres = parts[i+2].split(", "); //splitter strengen med genrer ved ", " og indsætter tekstbidder i et String[]
            System.out.println(title);
           // String ratingString = parts[i+3].replace(",", ".");
            // CRASH; prøver at finde en double i ratingString, men der er komma i stedet for punktum
           // double rating = Double.parseDouble(ratingString);
           // System.out.println(rating);


        }
    }

    public static void parseSeries() throws IOException {
        File f = new File("resources\\series.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();

        while (line != null) {
            String[] parts = line.split("; ");

            String title = parts[0];

            String[] years = parts[1].split("-"); //opretter et array med start og slut år i String form
            int startYear = Integer.parseInt(years[0]); //omdanner String startYear til en int
            int endYear = Integer.parseInt(years[1]); //CRASH; hvis der ikke er noget slutår -> prøver at parse ingenting til en int

            String[] genres = parts[2].split(", ");

            String ratingString = parts[3];
            // CRASH; samme problem som i parseMovies()
            double rating = Double.parseDouble(ratingString);

            String[] seasons = parts[4].split(", ");

            int numberOfSeasons = 0; //loop der tæller antal seasons
            for(String s : seasons){
                numberOfSeasons++;
            }

            // loop der tilføjer antal afsnit for hver sæson til en ArrayList
            // f.eks. vil episodes[0] returnere antal afsnit i første sæson, episodes[1] i anden osv.
            ArrayList<Integer> episodes = new ArrayList<>();
            for(String s : seasons){
                String[] ep = s.split("-");
                Integer episodeCount = Integer.parseInt(ep[1]);
                episodes.add(episodeCount);
            }
        }
    }
}