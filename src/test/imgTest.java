package test;

import java.awt.*;
import model.*;

import javax.swing.*;

public class imgTest{

    public static void main(String[] args) {
        //swing components for test purposes
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();

        MediaContainer medias = new MediaContainer();
        try{
            TxtParser.parseMovies();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(medias.getMovies().get(0).getTitle());

        medias.getMovies().get(0).setImg("resources/movie_pictures/" + medias.getMovies().get(0).getTitle() + ".jpg");

        JLabel label = new JLabel(medias.getMovies().get(0).getImg());
        contentPane.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}