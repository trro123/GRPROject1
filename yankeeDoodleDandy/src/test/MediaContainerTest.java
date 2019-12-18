package test;

import sample.model.MediaContainer;
import sample.model.Movie;
import sample.model.Series;
import sample.model.Watchable;

public class MediaContainerTest{

    public void mediaContainerTest(){
        MediaContainer medias = new MediaContainer();
        String[] s = new String[2];
        s[0] = "ebin";
        s[1] = "win";
        medias.addMovie(new Movie("testmovie", 2019, 0.1, s));
        medias.addSeries(new Series("testseries", 2019, 2020, 0.1, 1, s));
        medias.joinLists();

        for(Watchable m : medias.getJoinedList()){
            System.out.println(m.getTitle() + "; " + m.getYear() + "; " + m.getGenres() + "; " + m.getRating());
        }

        for(Watchable m : medias.searchTitle("test")){
            System.out.println(m.getTitle() + "; " + m.getYear() + "; " + m.getGenres() + "; " + m.getRating());
        }
    }

}