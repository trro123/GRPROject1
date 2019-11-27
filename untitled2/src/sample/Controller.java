package sample;
//NUVÆRENDE PROBLEMER: 1: kan kun hente filer fra min egen pc (og er ikke særlig godt bygget op, mere congegence???)
//for lang kode ift. else if loop, lav nogle metoder (skaber mere flexibilitet)
//find ud af hvordan du gør tingene "pænere" (andre fonts, skriftstørrelse)
//titelfeltet skal nok også være noget andet.


// noget som kunne være sjovt at lave: implementere en funktion som ''tæller'' hvilke genre
// og film man har trykket mest på, og laver piecharts med det når examinator klikker rundt.
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.util.prefs.NodeChangeEvent;

public class Controller implements Watchable {
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    // fx:id="text_Field";
    private TextField text_field;
    // initiliazing a tableview of genres (need to specify that it's only genres)
    @FXML
    private TableView<Movie> genres;
    @FXML
    private TableColumn GenreNameColumn1;
    @FXML
    private TableColumn GenreNameColumn2;
    @FXML
    private TableColumnHeader Genre;
    // here is our  ImageView. I want this to show rick
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageTopLeft;
    @FXML
    private ImageView imageTopRight;
    @FXML
    private ImageView imageCenterLeft;
    @FXML
    private ImageView imageCenterRight;
    @FXML
    private ImageView imageBotLeft;
    @FXML
    private ImageView imageBotRight;
    // declaring Animation, need to implement it instead of image (also needs to use methods)
    private Animation ani;
    //pressing button method.
    public void buttonPressed(Event evt) {

        Button b = (Button) evt.getSource();
        //If statement som tjekker om knappen er trykket på før og sætter image=null hvis den er.
        if (b == b1) {
            if (imageTopLeft.getImage() == null) {
                Image image = new Image("file:///C:/Users/clart/Desktop/Images/giphy.gif");
                imageTopLeft.setImage(image);
                System.out.println("Never gonna give u up.");
            } else {
                imageTopLeft.setImage(null);
                System.out.println("Never gonna give u up.");
            }

        }
        else if(b == b2) {
            if (imageTopRight.getImage() == null) {
                Image image = new Image("file:///C:/Users/clart/Desktop/Images/giphy.gif");
                imageTopRight.setImage(image);
                System.out.println("Never gonna let u down");

            } else {
                imageTopRight.setImage(null);
                System.out.println("Never gonna let u down");
            }
        }
            else if(b == b3){
            if (imageCenterLeft.getImage() == null) {
                Image image = new Image("file:///C:/Users/clart/Desktop/Images/giphy.gif");
                imageCenterLeft.setImage(image);
                System.out.println("Never gonna run around and - desert you" );

            } else {
                imageCenterLeft.setImage(null);
                System.out.println("Never gonna run around and - desert you" );
            }

        }
            else if (b == b4){
            if (imageCenterRight.getImage() == null) {
                Image image = new Image("file:///C:/Users/clart/Desktop/Images/giphy.gif");
                imageCenterRight.setImage(image);
                System.out.println("Never gonna make you cry!");
            } else {
                imageCenterRight.setImage(null);
                System.out.println("Never gonna make you cry!");
            }

        }
            else if(b==b5){
            if (imageBotLeft.getImage() == null) {
                Image image = new Image("file:///C:/Users/clart/Desktop/Images/giphy.gif");
                imageBotLeft.setImage(image);
                System.out.println("Never gonna say goodbye");

            } else {
                imageBotLeft.setImage(null);
                System.out.println("Never gonna say goodbye");
            }

        }
        else if (b==b6){
            if (imageBotRight.getImage() == null) {
                Image image = new Image("file:///C:/Users/clart/Desktop/Images/giphy.gif");
                imageBotRight.setImage(image);
                System.out.println("Never gonna tell a lie and hurt you");

            } else {
                imageBotRight.setImage(null);
                System.out.println("Never gonna tell a lie and hurt you");
            }

        }
    }
    }

