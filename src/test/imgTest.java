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

        // kører load metoden for at initialisere movie objekterne
        MediaContainer medias = new MediaContainer();
        try{
            medias.loadMovies();
        } catch (Exception e){
            e.printStackTrace();
        }

        // opretter labels der indeholder en films billede. Tilføjes til vinduet
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