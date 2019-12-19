package sample.model;

/**
 * Watchable er datterklasse til Media, samt forældreklasse til Movie og Series-klasserne.
 */
public abstract class Watchable extends Media{
    protected boolean onWatchlist;

    public Watchable(String title, int year, double rating, String[] genres){
        super(title, year, rating, genres);
        onWatchlist = false;
    }
}
