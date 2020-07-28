/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafik;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

/**
 *
 * @author Reza
 */
//the center-object of the borderpane
public class Center extends VBox{
    
    private Label headingLabel = new Label("Tipsresultat");
    private HBox spaceHbox = new HBox();
    
    private HBox[] hboxLabelArray = new HBox[13];
    private Label[] rowNumberlabelArray = new Label[13];
    
    private Label[] result1X2Array = new Label[39];
    
    private Image imageBlank;
    private String imageBlankPath = "blank.jpg";
    
    private ImageView[] imageViewArray = new ImageView[39];
    
    private String[] result13Array = new String[13];
    
    public Center(){
        
        
        this.setPadding(new Insets(10, 10, 10, 100));  
        this.setSpacing(1);
        
        //set properties for top label and adding it to the layout
        Font headingFont ;
        headingFont = Font.font("Arial", FontWeight.BOLD, 20);
        headingLabel.setFont(headingFont);
        headingLabel.setTextFill(Color.RED);
        headingLabel.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(headingLabel);
        
        loadLabelImages();
        
        //a Hbox added to fill up space in the layout
        spaceHbox = new HBox();
        spaceHbox.setPadding(new Insets(10, 10, 10, 100));
        this.getChildren().add(spaceHbox);
        
        
        
        //create objects of Hbox-array and setting properties
        for(int z = 0 ; z < hboxLabelArray.length ; z++){
            hboxLabelArray[z] = new HBox();
            hboxLabelArray[z].setSpacing(25);
            hboxLabelArray[z].setFillHeight(true); 
            hboxLabelArray[z].autosize();
        }
        
        //get screen-resolution for setting image-size later
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double width = primaryScreenBounds.getWidth();
        double height = primaryScreenBounds.getHeight();
        
        Font labelFont ;
        labelFont = Font.font("Arial", FontWeight.BOLD, 20);
        
        //objects of rownumbers, color, text, font
        for(int x = 0 ; x < rowNumberlabelArray.length ; x++){
            rowNumberlabelArray[x] = new Label();
            BackgroundFill background_fill = new BackgroundFill(Color.ORANGE,  CornerRadii.EMPTY, Insets.EMPTY); 
            Background background = new Background(background_fill);
            rowNumberlabelArray[x].setBackground(background);
            
            if (x<9){
                rowNumberlabelArray[x].setText("   " + Integer.toString((x+1)) + " ");
            }
            else{
                rowNumberlabelArray[x].setText(" " + Integer.toString((x+1)) + " ");
            }
            
            rowNumberlabelArray[x].setFont(labelFont);
            rowNumberlabelArray[x].setAlignment(Pos.BOTTOM_CENTER);
            rowNumberlabelArray[x].setStyle("-fx-border-color: yellow;");
        }
        
        
        //settting properties of image-array
        for(int w = 0 ; w < imageViewArray.length ; w++){
            
            imageViewArray[w] = new ImageView();
            
            
            imageViewArray[w] = new ImageView(imageBlank);
            
            imageViewArray[w].setFitHeight(height/25);
            imageViewArray[w].setFitWidth(width/55);
            result1X2Array[w] = new Label();
            result1X2Array[w].setGraphic(imageViewArray[w]);
            result1X2Array[w].setAlignment(Pos.TOP_CENTER);
            
        }
        
        //filling resultarray with blank text
        for(int p = 0 ; p < result13Array.length ; p++){
            result13Array[p] = "";
        }
        
        int totalLabels = (rowNumberlabelArray.length +  result1X2Array.length);
        int squareNumber = 0; 
        int hboxIndex = 0;
        int result1X2Index = 0;
        
        //filling the result-part of the grafics of center
        for (int addIndex = 0 ; addIndex < totalLabels ; addIndex++ ){
            switch (squareNumber) {
                case 0:
                    hboxLabelArray[hboxIndex].getChildren().add(rowNumberlabelArray[hboxIndex]);
                    squareNumber++;
                    break;
                case 1:
                    hboxLabelArray[hboxIndex].getChildren().add(result1X2Array[result1X2Index]);
                    squareNumber++;
                    result1X2Index++;
                    break;
                case 2:
                    hboxLabelArray[hboxIndex].getChildren().add(result1X2Array[result1X2Index]);
                    result1X2Index++;
                    squareNumber++;
                    break;
                case 3:
                    hboxLabelArray[hboxIndex].getChildren().add(result1X2Array[result1X2Index]);
                    result1X2Index++;
                    hboxIndex++;
                    squareNumber = 0;
                    break;
                default:
                    break;
            }
            
        }
        
        
        this.getChildren().addAll(hboxLabelArray);
        
    }
    
    //load image for blank
    public void loadLabelImages(){
        
        try {
            
            imageBlank = new Image(imageBlankPath);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda bild!");
            //System.exit(0);
            return;
        }
        
    }
    
    public Label[] getResultArray(){
        return this.result1X2Array;
    }
    
    public String[] getResult13Array(){
        return result13Array;
    }
    
    public ImageView[] getImageViewArray(){
        return imageViewArray;
    }
    
}