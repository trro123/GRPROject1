package test;

import model.*;

public class TxtParserTest {
    
    public static void main(String[] args) {
        try{
            new MediaContainer();
            
            TxtParser.parseSeries();
            MediaContainer.seriesParserTest();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}