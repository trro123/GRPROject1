package test;

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
}
