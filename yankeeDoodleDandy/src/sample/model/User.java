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

    public boolean checkPassword(String input){
        if(input == password){
            return true;
        } else {
            return false;
        }
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

    public boolean emptyWatchlist(){
        if(watchlist.size() == 0){
            return true;
        } else {
            return false;
        }
    }

// mutator methods
    public void addToWatchlist(Watchable m){
        watchlist.add(m);
        //m.watchlistAddBool();
    }

    public void removeFromWatchlist(Watchable m){
        watchlist.remove(m);
        //m.watchlistRemoveBool();
    }
}