package test;

import sample.model.Movie;
import sample.model.Series;

public class MediaTest{

    public void movieTest(){
        String[] s = new String[2];
        s[0] = "ebin";
        s[1] = "win";
        Movie movie = new Movie("The Test of the Rings", 2069, 6.9, s);

        System.out.println(movie.getTitle() + "; " + movie.getYear() + "; " + movie.getGenres() + "; " + movie.getRating());
    }

    public void seriesTest(){
        String[] s = new String[2];
        s[0] = "ebin";
        s[1] = "win";
        Series series = new Series("Test of Thrones", 2069, 2070, 0.1, 8, s);

        System.out.println(series.getTitle() + "; " + series.getYear() + "-" + series.getEndYear() +
                "; " + series.getGenres() + "; " + series.getRating());
    }

}