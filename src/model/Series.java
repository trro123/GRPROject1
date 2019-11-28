package model;

import java.util.*;

public class Series extends Watchable{
    protected int seasons;
    protected int endYear;
    protected ArrayList<Integer> numberOfEpisodes;
    
    public Series(String title, int year, int endYear, double rating, int seasons, String[] genres){
        super(title, year, rating, genres);
        this.endYear = endYear;
        this.seasons = seasons;
        numberOfEpisodes = new ArrayList<>();
    }

    // tilføjer antal episoder til en ArrayList
    // index 0 vil indeholde en int svarende til antal episoder i første sæson, index 1 for anden sæson osv.
    public void addEpisodes(int episodeCount){
        numberOfEpisodes.add(episodeCount);
    }

    // get methods
    public int getEndYear(){
        return endYear;
    }

    public int getSeasons(){
        return seasons;
    }

    // returnerer antal afsnit i en given sæson; index 0 = første sæson, index 1 = anden osv.
    public int episodeCount(int index){
        return numberOfEpisodes.get(index);
    }
}