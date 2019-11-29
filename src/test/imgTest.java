package test;

import java.awt.*;
import model.*;

import javax.swing.*;

public class imgTest{

    public static void main(String[] args) {
        // swing components for test purposes
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout());

        // kører parser metoden for at initialisere movie objekterne
        MediaContainer medias = new MediaContainer();
        try{
            TxtParser.parseMovies();
        } catch (Exception e){
            e.printStackTrace();
        }

        // sætter et billede til alle movies i mediaContaineren baseret på filmens titel
        for(Movie m : medias.getMovies()){
            m.setImg("resources/movie_pictures/" + m.getTitle() + ".jpg");
        }

        // opretter labels der tilføjes til vinduet
        JLabel label = new JLabel(medias.getMovies().get(0).getImg());
        contentPane.add(label);
        JLabel label2 = new JLabel(medias.getMovies().get(1).getImg());
        contentPane.add(label2);
        JLabel label3 = new JLabel(medias.getMovies().get(2).getImg());
        contentPane.add(label3);
        JLabel label4 = new JLabel(medias.getMovies().get(99).getImg());
        contentPane.add(label4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}