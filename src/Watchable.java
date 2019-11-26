public class Watchable extends Media{
    protected boolean onWatchlist;

    public Watchable(String title, int year, double rating){
        super(title, year, rating);
        onWatchlist = false;
    }

    public addToWatchlist(){
        onWatchlist = true;
    }

    public boolean onWatchlist(){
        return onWatchlist;
    }
}
