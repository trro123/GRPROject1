package test;

import model.*;

public class MediaContainerTest{

    public static void main(String[] args) {
        new MediaContainer();
        
        try{
            TxtParser.parseMovies();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}