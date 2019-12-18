package sample.model;

import java.util.*;

public class User{
    private ArrayList<Watchable> watchlist;
    private String username;
    private String password;
    
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