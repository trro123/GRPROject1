package sample.model;

import java.util.*;

/**
 * Series er et objekt modeleret efter virkeligheden og data i .txt filer.
 */
public class Series extends Watchable{
    protected int seasons;
    protected int endYear;
    protected ArrayList<Integer> numberOfEpisodes;

    /**
     * @param title: String, repræsenterer filmens titel.
     * @param year: int, repræsenterer seriens startår
     * @param endYear: int, repræsenterer seriens slutår
     * @param rating double, repræsenterer seriens rating.
     * @param seasons int, repræsenterer antal af sæsoner.
     * @param genres Array af Strings, repræsentere filmens genrer.
     */
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

    /**
     * // tilføjer antal episoder til en ArrayList.
     * Hver plads i ArrayListen svarer til en sæson og referencen peger så på antal afsnit i den givne sæson
     * @param episodeCount: Int, repræsenterer antallet af episoder i en sæson.
     */
    public void addEpisodeCount(int episodeCount){
        numberOfEpisodes.add(episodeCount);
    }
}