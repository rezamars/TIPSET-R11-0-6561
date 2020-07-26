/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafik;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import model.ReadFile;

/**
 *
 * @author Reza
 */
//the right-object of the borderpane
public class Right extends VBox{
    
    public ReadFile readFile;
    private String[][] R11_0_729_Tables = new String[729][11];
    private Button countButton = new Button("Räkna antal rätt");
    
    private HBox spaceHbox1 = new HBox();
    private HBox spaceHbox2 = new HBox();
    private TextArea numberOfRightsTextArea = new TextArea();
    
    private HBox spaceHbox3 = new HBox();
    private HBox spaceHbox4 = new HBox();
    private Button clearButton = new Button("  Återställ  ");
    
    public Right(){
        
        readFile = new ReadFile(this);
        
        this.setAlignment(Pos.TOP_CENTER);
        
        this.setPadding(new Insets(10, 10, 50, 100));  
        this.setSpacing(1);
        
        //filling the tables-array with zero text
        for (int x = 0 ; x < 27 ; x++){
            for (int y = 0 ; y < 8 ; y++){
                R11_0_729_Tables[x][y] = "";
            }
        }
        
        //get screenresolution
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double width = primaryScreenBounds.getWidth();
        
        //HBox that acts to fill up space
        spaceHbox1.setPadding(new Insets(10, 10, 100, (width/8)));
        this.getChildren().add(spaceHbox1);
        
        spaceHbox2.setPadding(new Insets(10, 10, 10, (width/8)));
        
        numberOfRightsTextArea.setText("Antal rätt:\n13 rätt: \n12 rätt:\n11 rätt:\n10 rätt:");
        numberOfRightsTextArea.setWrapText(true);
        
        //setting background of TextArea
        BackgroundFill background_fill = new BackgroundFill(Color.YELLOW,  CornerRadii.EMPTY, Insets.EMPTY); 
        Background background = new Background(background_fill);
        numberOfRightsTextArea.setBackground(background);
        
        //setting properties of textarea
        numberOfRightsTextArea.setStyle("-fx-text-fill: red; -fx-border-color: black;");
        numberOfRightsTextArea.setPrefColumnCount(1);
        numberOfRightsTextArea.setPrefRowCount(5);
        numberOfRightsTextArea.setEditable(false);
        Font textAreaFont ;
        textAreaFont = Font.font("", FontWeight.BOLD, 15);
        numberOfRightsTextArea.setFont(textAreaFont);
        
        //setting properties and font of countbutton
        countButton.setDisable(true);
        countButton.setStyle("-fx-text-fill: blue;");
        Font countButtonFont ;
        countButtonFont = Font.font("Arial", FontWeight.BOLD, 15);
        countButton.setFont(countButtonFont);
        
        this.getChildren().add(countButton);
        this.getChildren().add(spaceHbox2);
        this.getChildren().add(numberOfRightsTextArea);
           
        ReadTables();
        
        //setting font and color of clearbutton
        Font clearButtonFont ;
        clearButtonFont = Font.font("Arial", FontWeight.BOLD, 15);
        clearButton.setFont(clearButtonFont);
        clearButton.setStyle("-fx-text-fill: purple;");
        
        spaceHbox3.setPadding(new Insets(65, 10, 10, (width/8)));
        this.getChildren().add(spaceHbox3);
        this.getChildren().add(clearButton);
        
    }
    
    
    public void ReadTables(){
        
        R11_0_729_Tables = readFile.readFileFromTextFile();
                
    }
    
    public String[][] getSystemTables(){
        return R11_0_729_Tables;
    }
   
    public Button getCountButton(){
        return this.countButton;
    }
    
    public Button getClearButton(){
        return this.clearButton;
    }
    
    public TextArea getTextArea(){
        return this.numberOfRightsTextArea;
    }
    
}
