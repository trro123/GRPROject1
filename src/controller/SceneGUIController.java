package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import view.*;
import model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SceneGUIController implements Initializable  {

    @FXML
    Label label;
    @FXML
    TilePane tilePane; //definere det tilepane som ER oprettet i Scenebuilder.
    @FXML
    AnchorPane myAnchor; //definere det tilepane som ER oprettet i Scenebuilder.
    //@FXML
    //ScrollPane scrollPane;

    int count = 0;

    private int nRows = 10; //number of rows on tilepane
    private int nCols = 10; //number of column for tile pane (dette skal skaleres op senere hen)

    private static final double ELEMENT_SIZE = 100; //hvor stort vores billede skal være
    private static final double GAP = ELEMENT_SIZE / 10; // mellemrummet mellem 2 bokse (altså 2 billeder)


    File filesJpg[]; //her laver vi et array af vores billeder.

    public SceneGUIController() {
    }
    @FXML
    private void handleButtonAction(ActionEvent event) { //idéen er her at når man trykker på knappen, tilføjer den img til imageview=imgv.
        Stage parent = (Stage)myAnchor.getScene().getWindow();

        // i stedet for at indlæse en ekstern fil, så evt. kald en metode, der tager en genre-parameter
        // og lister filmene på baggrund af den


        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(parent);

        if(selectedDirectory != null){
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };
            filesJpg = selectedDirectory.listFiles(filterJpg); // typisk ville man indlæse alle billeder der
        }

        createElements();
    }
    private void createElements(){
        tilePane.getChildren().clear();

        for (int i = 0; i< nCols; i++){
            for (int j = 0; j < nRows; j++){ //herinde tænker jeg vi kan blive ved med at lave nye rows, så længe j er mindre end files.length()?/files.size
                tilePane.getChildren().add(createPage(count));
                count++;
            }
        }
    }
    public VBox createPage(int index){

        ImageView imageView = new ImageView();

        // i stedet for at gemme billeder i det her array, så gennemløb media-listen fra Model og hiv billederne ud derfra

       /* ArrayList<Media> mediaList = MediaContainer.givMigAlleDineMediaObjekterIEnArrayList(); //man bør have begge typer medier på samme liste, og så lave if-statements hvis der skal skelnes mellem de to-

        for (Media media : mediaList) {
            if (media.getGenres().equals("enBestemtGenre")) {
                media.getImg();
            }
        }


        */
        File file = filesJpg[index];
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = new Image(file.toURL().toString());
            imageView.setImage(image);
            imageView.setFitWidth(ELEMENT_SIZE);
            imageView.setFitHeight(ELEMENT_SIZE);
            // imageView.setPreserveRatio(true); //var udkommenteret i video.

            imageView.setSmooth(true);
            imageView.setCache(true);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        pageBox.setStyle("-fx-border-color: orange;");
        return pageBox;
    }
    public void initialize(URL url, ResourceBundle rb) {

        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
        myAnchor.setStyle("-fx-background-image: Backgound.png;"); //-fx-background-color: rgba(0, 0, 0);
        //tilePane.setStyle("-fx-background-color: rgba(0, 0, 0);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);


    }
}

