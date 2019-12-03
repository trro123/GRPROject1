package test;

import model.*;

public class HowMany{

    public static void main(String[] args) {
        MediaContainer medias = new MediaContainer();
        try{
            medias.loadMovies();
            medias.loadSeries();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        int counter = 0;
        for(Series m : medias.getSeries()){
            if(m.getGenres().contains("Drama")){ //
                counter++;
            }
        }
        System.out.println(counter);
    }
}