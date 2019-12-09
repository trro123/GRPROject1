package sample.model;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

public abstract class Media{ //abstract class fordi det skal være forbudt at lave media. Man skal oprette noget der nedarver fra den i stedet
    protected String title;
    protected int year;
    protected double rating;
    protected List<String> genres;

    protected Image img;

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

    public Image getImg(){
        return img;
    }

    public List<String> getGenres(){
        return genres;
    }

// mutator methods
    
    public void addGenre(String genre){
        genres.add(genre);
    }

    public void setImg(){
        this.img = new Image("file: /resources/movie_pictures" + title + ".jpg");
    }
}