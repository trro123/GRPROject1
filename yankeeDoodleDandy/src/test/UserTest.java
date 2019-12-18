package test;

import sample.model.Movie;
import sample.model.Series;
import sample.model.User;
import sample.model.Watchable;

public class UserTest{

    public static void userTest(){
        User user = new User("testUser", "You shouldn't be printing this.");

        String[] s = new String[2];
        s[0] = "testgenre1";
        s[0] = "testgenre2";
        Movie movie = new Movie("testmovie", 1999999, 0.1, s);
        Series series = new Series("testseries", 199238, 123987, 0.1, 1, s);

        user.addToWatchlist(movie);
        user.addToWatchlist(series);

        System.out.println(user.getUsername() + ": " + user.getPassword());
        for(Watchable m : user.getWatchlist()){
            System.out.println(m.getTitle() + "; " + m.getYear() + "; " + m.getGenres() + "; " + m.getRating());
        }
    }

}