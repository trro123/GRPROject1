package test;

import sample.controller.mainWindowController;
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
            System.out.println(ute.getMessage();
        }
    }
    public static void test33() throws AlreadyOnWatchlistException{
        try {
            mainWindowController c = new mainWindowController();
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
