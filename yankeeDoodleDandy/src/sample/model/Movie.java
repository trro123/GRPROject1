package sample.model;

/**
 * Movie-klassens objekt er modelleret efter virkeligheden samt data i .txt-filen.
 */
public class Movie extends Watchable{

    /**
     *
     * @param title: String, repræsenterer filmens titel.
     * @param year: int, reprænsterer filmens udgivelsesår.
     * @param rating: double, repræsenterer filmens rating.
     * @param genres: Array af Strings, repræsentere filmens genrer.
     */
    public Movie(String title, int year, double rating, String[] genres){
        super(title, year, rating, genres);
    }
}