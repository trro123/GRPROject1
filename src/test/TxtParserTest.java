package test;

import model.*;

public class TxtParserTest {
    
    public static void main(String[] args) {
        try{
            new MediaContainer();
            
            TxtParser.parseMovies();
            MediaContainer.movieParserTest();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}