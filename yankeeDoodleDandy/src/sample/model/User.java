package sample.model;

import java.util.*;

public class User{
    private List<Watchable> watchlist;
    private String username;
    private String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
        watchlist = new ArrayList<>();
    }

    public boolean checkPassword(String input){
        if(input == password){
            return true;
        } else {
            return false;
        }
    }

// get methods
    public List<Watchable> getWatchlist(){
        return watchlist;
    }

    public String getUsername(){
        return username;
    }

// mutator methods
    public void addToWatchlist(Watchable m){
        watchlist.add(m);
    }

    public void removeFromWatchlist(Watchable m){
        watchlist.remove(m);
    }
}