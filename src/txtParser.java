import java.io.*;

// Læser .txt-filer linje for linje og spytter felter ud (title, year, rating osv.)
public class txtParser {

    public static void parseMovies() throws IOException {
        File file = new File("resources\\movies.txt"); //fortæller java hvor .txt-filen er
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); //line = én linje i .txt filen

        // kører så længe der er flere lines i .txt-filen
        while (line != null) {
            String[] parts = line.split("; "); //splitter linjen ved alle "; " og indsætter tekstbidder i et String[]
            
            String title = parts[0];

            String yearString = parts[1];
            int year = Integer.parseInt(yearString); //omdanner String year til en int; crasher programmet hvis den læser andet end tal

            String[] genres = parts[2].split(", "); //splitter strengen med genrer ved ", " og indsætter tekstbidder i et String[]

            String ratingString = parts[3];
            // CRASH; prøver at finde en double i ratingString, men der er komma i stedet for punktum
            double rating = Double.parseDouble(ratingString);
        }
    }

    public static void parseSeries() throws IOException {
        File file = new File("resources\\series.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while (line != null) {
            String[] parts = line.split("; ");

            String title = parts[0];

            String[] years = parts[1].split("-"); //opretter et array med start og slut år i String form
            int startYear = Integer.parseInt(years[0]); //omdanner String startYear til en int;
            int endYear = Integer.parseInt(years[1]); //CRASH; ikke noget slutår, prøver at parse ingenting til en int

            String[] genres = parts[2].split(", ");

            String ratingString = parts[3];
            // CRASH; samme problem som i parseMovies()
            double rating = Double.parseDouble(ratingString);

            String[] seasons = parts[4].split(", ");

            int numberOfSeasons = 0; //loop der tæller antal seasons
            for(String s : seasons){
                numberOfSeasons++;
            }

            // implementer: få antal afsnit i hver sæson
        }
    }
}