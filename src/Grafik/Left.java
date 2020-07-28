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
//the Left-object of the borderpane
public class Left extends VBox{
    
    private Label headingLabel = new Label("Mitt system, 11 hel-RG + 2 hel-MG");
    private Label[] rowNumberlabelArray = new Label[13];
    private Label[] MGArray = new Label[13];
    private ImageView[] imageViewArray = new ImageView[13];
    private Label[] arrayLabel1X2 = new Label[13];
    
    private Image MGimage1;
    private String MGimage1Path = "MG-ej-klickad.jpg";
    private Image MGimage2;
    private String MGimage2Path = "MG-klickad.jpg";
    private ImageView imageView1;
    private ImageView imageView2;
    
    private HBox spaceHbox = new HBox();
    private HBox[] hboxLabelArray = new HBox[13];
    private boolean flagOf2MGs = false;
    
    private int[] chosenMGIndexes = new int[5];
    
    
    public Left(){
        
        this.setPadding(new Insets(10, 10, 10, 100));  
        this.setSpacing(1);
        
        Font headingFont ;
        headingFont = Font.font("Arial", FontWeight.BOLD, 20);
        
        headingLabel.setFont(headingFont);
        headingLabel.setTextFill(Color.BLUE);
        
        this.getChildren().add(headingLabel);
        
        //a HBox for filling up space
        spaceHbox = new HBox();
        spaceHbox.setPadding(new Insets(10, 10, 10, 100));
        this.getChildren().add(spaceHbox);
        
        //creating instances of HBox-array and setting properties
        for(int z = 0 ; z < hboxLabelArray.length ; z++){
            hboxLabelArray[z] = new HBox();
            hboxLabelArray[z].setSpacing(25);
            hboxLabelArray[z].setFillHeight(true); 
            hboxLabelArray[z].autosize();
            hboxLabelArray[z].setAlignment(Pos.CENTER);

        }
       
       
        
        Font labelFont ;
        labelFont = Font.font("Arial", FontWeight.BOLD, 20);
        
        //objects of rownumbers, color, text, font
        for(int x = 0 ; x < rowNumberlabelArray.length ; x++){
            rowNumberlabelArray[x] = new Label();
            BackgroundFill background_fill = new BackgroundFill(Color.LIGHTYELLOW,  CornerRadii.EMPTY, Insets.EMPTY); 
            Background background = new Background(background_fill);
            rowNumberlabelArray[x].setBackground(background);
            
            if (x<9){
                rowNumberlabelArray[x].setText("   " + Integer.toString((x+1)) + " ");
            }
            else{
                rowNumberlabelArray[x].setText(" " + Integer.toString((x+1)) + " ");
            }
            
            rowNumberlabelArray[x].setFont(labelFont);
            rowNumberlabelArray[x].setAlignment(Pos.CENTER);
        }
        
        //loading images for result-part
        try {
            
            MGimage1 = new Image(MGimage1Path);
            MGimage2 = new Image(MGimage2Path);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda MG-bild!");
            //System.exit(0);
            return;
        }
        
        //get screenresolution to set procentage of screen later
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double width = primaryScreenBounds.getWidth();
        double height = primaryScreenBounds.getHeight();
        
        //setting properties of image-views
        imageView1 = new ImageView(MGimage1);
        imageView1.setFitHeight(height/25);
        imageView1.setFitWidth(width/55);
        imageView2 = new ImageView(MGimage2);
        imageView2.setFitHeight(height/25);
        imageView2.setFitWidth(width/55);
        
        //setting properties of image-views and setting the images to the grafics of the MGArray
        for(int w = 0 ; w < MGArray.length ; w++){
            imageViewArray[w] = new ImageView(MGimage1);
            imageViewArray[w].setFitHeight(height/25);
            imageViewArray[w].setFitWidth(width/55);
            
            MGArray[w] = new Label();
            MGArray[w].setGraphic(imageViewArray[w]);
            
        }
        
        
        //setting background, font and text of the 1X2-labels
        for(int q = 0 ; q < arrayLabel1X2.length ; q++){
            arrayLabel1X2[q] = new Label();
            BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGREEN,  CornerRadii.EMPTY, Insets.EMPTY); 
            Background background = new Background(background_fill);
            arrayLabel1X2[q].setBackground(background);
            arrayLabel1X2[q].setText("  1 X 2 ");
            arrayLabel1X2[q].setFont(labelFont);
        }
        
        
        
        int totalLabels = (rowNumberlabelArray.length + MGArray.length + arrayLabel1X2.length);
        int squareNumber = 0; 
        int hboxIndex = 0;
        int array1X2Number = 0;
        
        //adding rowNumbers, MG-pictures and 1X2-labels to the screen (stage)
        for (int addIndex = 0 ; addIndex < totalLabels ; addIndex++ ){
            if ( squareNumber == 0){
                hboxLabelArray[hboxIndex].getChildren().add(rowNumberlabelArray[hboxIndex]);
                squareNumber++;
            }
            else if (squareNumber == 1){
                hboxLabelArray[hboxIndex].getChildren().add(MGArray[hboxIndex]);
                squareNumber++;
            }
            else if (squareNumber == 2){
                hboxLabelArray[hboxIndex].getChildren().add(arrayLabel1X2[array1X2Number]);
                array1X2Number++;
                hboxIndex++;
                squareNumber = 0;
            }
            
        }
        
        //filling the chosenIndex-array of -1 (unacceptable number), to know if and when 
        //the array contains unaccepted number
        for (int x = 0 ; x < chosenMGIndexes.length ; x++){
            chosenMGIndexes[x] = -1;
        }
        
        this.getChildren().addAll(hboxLabelArray);
        
    }
    
    public Label[] getMGArray(){
        return MGArray;
    }
    
    public ImageView[] getImageViewArray(){
        return imageViewArray;
    }
    
    public boolean get2MGsFlag(){
        return flagOf2MGs;
    }
    
    public int[] getChosenMGIndexes(){
        return chosenMGIndexes;
    }
    
    public ImageView getImageView1(){
        return imageView1;
    }
    
    public ImageView getImageView2(){
        return imageView2;
    }
    
}
