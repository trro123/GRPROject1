package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jdk.dynalink.beans.StaticClass;
import view.*;
import model.*;
public class SceneGUIController {
    @FXML
    public Font x1;
    @FXML
    public Color x2;
    @FXML
    public Font x3;
    @FXML
    public Color x4;

    @FXML
    GridPane ActionGrid;

    public void addMovieImg(){
        MediaContainer medias = new MediaContainer();
        int i = 0;
        int j = 0;
        GridPane actionGrid = ActionGrid;
        for (Movie m : medias.getMovies()){

            ImageView img = new ImageView();
            img.setImage(m.getImg());
            ActionGrid.add(img, i, j);
            img.setVisible(true);
            i++;
            if (i==2){
                j++;
                i=0;
            }
        }
    }

}
