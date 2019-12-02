package model;

import java.util.*;
import java.util.List;
import javax.swing.*;

public abstract class Media{ //abstract class fordi det skal være forbudt at lave media. Man skal oprette noget der nedarver fra den i stedet
    protected String title;
    protected int year;
    protected double rating;
    protected List<String> genres;
    
    // java swing image for test purposes
    protected ImageIcon img;

    public Media(String title, int year, double rating, String[] genres){
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.genres = Arrays.asList(genres);
    }

// get methods

    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }

    public double getRating(){
        return rating;
    }

    public ImageIcon getImg(){
        //ImageIcon er en swing component - på sigt bruger vi javafx, men jeg aner ikke hvordan man opretter de forskellige javafx komponenter, og skulle teste om syntaksen i imgTest.java ellers virkede
        //ps det gør den
        return img;
    }

    public List<String> getGenres(){
        return genres;
    }

// mutator methods
    
    public void addGenre(String genre){ //tilføjer en genre til listen
        genres.add(genre);
    }

    public void setImg(String path){
        this.img = new ImageIcon(path);
    }
}