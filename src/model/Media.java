package model;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public abstract class Media{ //abstract class fordi det skal være forbudt at lave media. Man skal oprette noget der nedarver fra den i stedet
    protected String title;
    protected int year;
    protected double rating;
    protected List<String> genres;
    
    // java swing image declaration for test purposes
    protected ImageIcon img;

    public Media(String title, int year, double rating, String[] genres){
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.genres = Arrays.asList(genres);
    }

    //get methods
    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }

    public double getRating(){
        return rating;
    }

    public void addGenre(String genre){ //tilføjer en genre
        genres.add(genre);
    }
    public String getGenre(int index){ //returnerer genrer til et givet index
        return genres.get(index);
    }
    public int numberOfGenres(){ //returnerer mediets antal af genrer
        return genres.size();
    }


    // set image for test purposes
    public void setImg(String path){
        this.img = new ImageIcon(path);
    }

    public ImageIcon getImg(){
        return img;
    }
}