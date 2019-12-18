package test;

import sample.controller.Controller;
import sample.model.AlreadyOnWatchlistException;
import sample.model.User;
import sample.model.UsernameTakenException;

public class test01 {
    public static void test31()
    {
        User u = new User("smurfvezz", "password123");
        User u2 = new User("smurfvezz", "telefonboks84");
        u.getUsername();
    }

    public static void test32()
    {
        try
        {
            test31();
        }
        catch (UsernameTakenException ute)
        {
            System.out.println(ute.getMessage()+": "+u.getUsername());
        }
    }
    public static void test33() throws AlreadyOnWatchlistException{
        try {
            Controller c = new Controller();
            User u3 = new User("hassan","skorpion");
            c.setCurrentUser(u3);
            c.watchlistAdd();

        }
        catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }
        }
    }
}
