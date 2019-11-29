package test;

import java.awt.*;
import java.util.*;
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

        ImageIcon icon = new ImageIcon("resources/movie_pictures/" + medias.getMovies().get(0).getTitle() + ".jpg");
        JLabel label = new JLabel(icon);
        contentPane.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}