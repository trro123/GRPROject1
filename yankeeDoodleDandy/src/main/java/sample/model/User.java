package sample.model;

import java.util.*;

/**
 * User-klassen har til ansvar at holde på data vdr. brugernavn og adgangskode, samt tilføje Watchable objekter til watchlists.
 */

public class User{
    private ArrayList<Watchable> watchlist;
    private String username;
    private String password;

    /**
     *
     * @param username: String, repræsenterer et brugernavn.
     * @param password: String, repræsenterer en adgangskode.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        watchlist = new ArrayList<>();
    }


// get methods
    public ArrayList<Watchable> getWatchlist(){
        return watchlist;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


// mutator methods

    /**
     * tilføjer et Watchable-objekt til ArrayListen watchlist.
     * @param m: repræsentere et Watchable objekt (Movie/Series)
     */
    public void addToWatchlist(Watchable m){
        watchlist.add(m);
    }
    /**
     * fjerner et Watchable-objekt fra ArrayListen watchlist.
     * @param m: repræsentere et Watchable objekt (Movie/Series)
     */
    public void removeFromWatchlist(Watchable m){
        watchlist.remove(m);
    }
}