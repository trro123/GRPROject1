package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jdk.dynalink.beans.StaticClass;
import view.*;
import model.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneGUIController {

    static final File seriesDir = new File("series_pictures");
    static final String[] EXTENSTIONS = new String[]{
            "jpg"
    };

    @FXML
    public Font x1;
    @FXML
    public Color x2;
    @FXML
    public Font x3;
    @FXML
    public Color x4;
    @FXML
    HBox hbox;
    @FXML
    ImageView imgv;
    @FXML
    Button b1;

    Image img;

    public  SceneGUIController(){ //vi skal
        this.img = new Image(getClass().getResourceAsStream("series_pictures"));

        for (Image i : images)
        imgv.setImage(i);

    }
    public void buttonPressed1(ActionEvent actionEvent){ //idéen er her at når man trykker på knappen, tilføjer den img til imageview=imgv.
        imgv.setImage(img);
    }

    public void imageArray(){

    }
    public void initialize(){
    }
}
