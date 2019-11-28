package model;

public class Series extends Watchable{
    protected int seasons;
    protected int endYear;
    
    public Series(String title, int year, int endYear, double rating, int seasons, String[] genres){
        super(title, year, rating, genres);
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