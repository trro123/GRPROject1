package test;

import sample.model.MediaContainer;
import sample.model.Watchable;

public class TxtParserTest {

    public void txtParserTest(){
        MediaContainer medias = new MediaContainer();
        try{
            // loadMovies og loadSeries kalder metoderne parseMovies og parseSeries fra TxtParser.java
            // (læser de to .txt-filer og opretter movie og series objekter samt putter dem i en MediaContainer)
            medias.loadMovies();
            medias.loadSeries();
            medias.joinLists();
        }catch(Exception e){
            e.printStackTrace();
        }

        for(Watchable m : medias.getJoinedList()){
            System.out.println(m.getTitle() + "; " + m.getYear() + "; " + m.getGenres() + "; " + m.getRating());
        }
    }

}