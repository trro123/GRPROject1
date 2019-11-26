public class Series extends Watchable{
    protected int seasons;
    protected int endYear;

    public Series(String title, int year, int endYear, double rating, int seasons){
        super(title, year, rating);
        this.endYear = endYear;
        this.seasons = seasons;
    }

    // get methods
    public int getEndYear(){
        return endYear;
    }

    public int getSeasons(){
        return seasons;
    }
}