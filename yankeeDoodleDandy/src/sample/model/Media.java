package sample.model;

import javafx.scene.image.Image;
import java.util.*;
import java.util.List;
/**
 * Media.java er superklassen til alle medie-objekter. Watchable (film og serier) nedarver fra denne.
 * Media-klassen eksisterer så de essentielle metoder for diverse medie-objekter er tilgængelige, hvis man på sigt
 * vil tilføje andre medier end blot film og serier. Eksempelvis E-bøger eller lydfiler (musik, podcasts, mf.)
 * Klassen er abstrakt fordi der aldrig skal oprettes et media-objekt. Derimod skal der oprettes objekter der nedarver fra denne.
 */
public abstract class Media{
    protected String title;
    protected int year;
    protected double rating;
    protected List<String> genres;
    protected Image img;

    /**
     *
     * @param title titel på medie-objektet.
     * @param year fødselsår for mediet.
     * @param rating pt. bruger programmet IMDb-ratings for film og serier. På sigt kunne andre medie-objekter have
     *               en gennemsnitsrating på baggrund af brugernes anmeldeser eller lignende.
     * @param genres en ArrayList af genrer for objektet. Bruges til at søge efter medier i bestemte genrer.
     */
    public Media(String title, int year, double rating, String[] genres){
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.genres = Arrays.asList(genres);
    }

    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }

    public double getRating(){
        return rating;
    }

    public List<String> getGenres(){
        return genres;
    }

    /**
     * Tilføjer et Image (javaFX) til et media-objekt. Hentes fra resources mappen på baggrund af objektets titel.
     * Billedet bruges til at visualisere objektet i brugergrænsefladens hovedpanel.
     */
    public void setImg(){
        this.img = new Image("file: /resources/movie_pictures" + title + ".jpg");
    }
}