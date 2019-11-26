import java.io.*;

// Læse .txt-file og oversætte det til felter

public class MediaParser {
    public static void parseMovies() throws IOException { //vi laver både en for Movies og en for Series
        File f = new File("resources\\movies.txt"); //fortæller java hvor .txt-filen er

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("; "); //alle entries i movies.txt er opdelt af "; ", så vi fortæller java at den skal opdele linjerne der.
            String title = parts[0];
            int year = Integer.parseInt(parts[1]); //integer finder en int hvis der er en. Laver exception hvis den ikke kan læse parten pgs "." eller "," og crasher.
            String[] genres = parts[2].split(", "); //splitter genrer ligesom da vi splittede de andre linjer. String-arrayet hedder "genres".
            double rating = Integer.parseInt(parts[3]);
        }
    }
    public static void parseSeries() throws IOException {
        File f = new File("resources\\series.txt"); //fortæller java hvor .txt-filen er

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("; ");
            String title = parts[0];
            String[] years = parts[1].split("-");
            int startYear = Integer.parseInt(years[0]);
            int endYear = Integer.parseInt(years[1]); //Lige nu crasher programmet højst sandsynligt, fordi der i nogle af linjerne ikke er slutår.
            String[] genres = parts[2].split(", "); //splitter genrer ligesom da vi splittede de andre linjer. String-arrayet hedder "genres".
            double rating = Integer.parseInt(parts[3]);
        }
    }
}
//undskyld mikkel v2.0