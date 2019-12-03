package test;

import model.*;
import javax.swing.*;
import java.awt.*;

public class searchTest {
    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout());
        
        MediaContainer medias = new MediaContainer();
        try{
            medias.loadMovies();
            medias.loadSeries();
        } catch (Exception e){
            e.printStackTrace();
        }

        for(Watchable m : medias.searchBeforeYear(1950)){
            System.out.println(m.getTitle() + ": " + m.getYear());
            JLabel label = new JLabel(m.getImg());
            contentPane.add(label);
        }
    /*
        for(Watchable m : medias.searchAfterYear(2010)){
            System.out.println(m.getTitle() + " " + m.getYear());
            JLabel label = new JLabel(m.getImg());
            contentPane.add(label);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

     */
}