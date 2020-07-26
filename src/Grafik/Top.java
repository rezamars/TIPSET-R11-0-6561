/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafik;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Reza
 */
//the top-object of the borderpane
public class Top extends HBox{
    
    private Label tipsLabel = new Label();
    private Label systemLabel = new Label("R11-0-729-6561(11)");
    private Image image;
    private String imagePath = "tipset.jpg";
    
    
    public Top() {
        
        
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10, 10, 10, 10));  
        this.setSpacing(30);
        
        //load image
        try {
            
            image = new Image(imagePath);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda tipset-bild!");
            //System.exit(0);
            return;
        }
        
        //set the image to the grafic
        tipsLabel.setGraphic(new ImageView(image));
        
        Font font ;
        font = Font.font("Arial", FontWeight.BOLD, 40);
        systemLabel.setFont(font);
        systemLabel.setTextFill(Color.BLUE);

        this.getChildren().add(tipsLabel);
        this.getChildren().add(systemLabel);

        
    }
    
    
    
}
