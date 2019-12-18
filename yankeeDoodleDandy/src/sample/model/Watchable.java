package sample.model;

public abstract class Watchable extends Media{
    protected boolean onWatchlist;

    public Watchable(String title, int year, double rating, String[] genres){
        super(title, year, rating, genres);
        onWatchlist = false;
    }
}
