package sample.model;

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

// accessor methods

    public int getEndYear(){

        return endYear;
    }
    public String getEndYearString(){
        String endYearString= Integer.toString(endYear);
        if (endYearString.equals("0"))
            endYearString = "";
        return endYearString;
    }

    public int getSeasons(){
        return seasons;
    }

    public int getEpisodeCount(int index){ // returnerer antal afsnit i en given sæson; index 0 = første sæson, index 1 = anden osv.

        return numberOfEpisodes.get(index);
    }

// mutator methods

    public void addEpisodeCount(int episodeCount){ // tilføjer antal episoder til en ArrayList. Hver plads i ArrayListen svarer til en sæson og referencen peger så på antal afsnit i den givne sæson
        numberOfEpisodes.add(episodeCount);
    }
}