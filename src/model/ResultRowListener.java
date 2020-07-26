/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Center;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Reza
 */
//handles the listener to the results that the user marks
public class ResultRowListener {
    
    
    private Center center;
    private Label[] result1X2Array;
    
    private Image image1;
    private String image1Path = "1.jpg";
    private Image imageX;
    private String imageXPath = "x.jpg";
    private Image image2;
    private String image2Path = "2.jpg";
    private Image imageBlank;
    private String imageBlankPath = "blank.jpg";
    private ImageView[] imageViewArray;
    
    private int resultLabellIndex = -1;
    private boolean[] resultFlagArray = new boolean[39];
    
    private int selectedRowNumber = -1;
    private String strArrayTypeOfImage = "";
    private String[] strArray = new String[39];
    private int[] disableIndexes = new int[2];
    
    private boolean result13Flag;
    private int checkedResults = 0;
    
    private String[] result13Array;
    private int rowIndex = -1;
    
    private MGListener MGListener;
    private int numberOfResChecked = 0;
    
    
    public ResultRowListener(Label[] resultArray, Center center1, MGListener MGListener1, boolean flag13){
        
        this.result1X2Array = resultArray;
        this.center = center1;
        this.imageViewArray = center1.getImageViewArray();
        this.result13Array = center1.getResult13Array();
        this.MGListener = MGListener1;
        this.result13Flag = flag13;
        
        for (int a = 0 ; a < resultFlagArray.length ; a++){
            resultFlagArray[a] = false;
        }
        
        loadLabelImages();
        
        disableIndexes[0] = -1;
        disableIndexes[1] = -1;
        
        for ( int s = 0 ; s < strArray.length ; s++){
            strArray[s] = "";
        }
        
    }
    
    //adds listener for the result input of the user
    //then, calls the flagsetter method, 
    //and updates the images for clicked, depending on 1X2
    public void addResultLabelListener(){
	        
        for(int x = 0 ; x<result1X2Array.length ; x++){
                
	        result1X2Array[x].setOnMouseClicked(event -> {
                    
                    for(int loopIndex = 0 ; loopIndex <result1X2Array.length ; loopIndex+=3){
                        
                        if(event.getSource() == result1X2Array[loopIndex]){
                            resultLabellIndex = loopIndex;
                            strArrayTypeOfImage = "1";
                            strArray[resultLabellIndex] = strArrayTypeOfImage;
                            strArray[resultLabellIndex + 1] = "";
                            strArray[resultLabellIndex + 2] = "";
                        }
                        else if(event.getSource() == result1X2Array[loopIndex+1]){
                            resultLabellIndex = loopIndex + 1 ;
                            strArrayTypeOfImage = "X";
                            strArray[resultLabellIndex] = strArrayTypeOfImage;
                            strArray[resultLabellIndex - 1] = "";
                            strArray[resultLabellIndex + 1] = "";
                        }
                        else if(event.getSource() == result1X2Array[loopIndex+2]){
                            resultLabellIndex = loopIndex + 2 ;
                            strArrayTypeOfImage = "2";
                            strArray[resultLabellIndex] = strArrayTypeOfImage;
                            strArray[resultLabellIndex - 1] = "";
                            strArray[resultLabellIndex - 2] = "";
                        }
                    }
                    
                   
                    ResultLabelFlagSetter();
                    updateLabelImage();
                    
                });
        }
    }      
    
    //loads the images for the result-input
    public void loadLabelImages(){
        
        try {
            
            image1 = new Image(image1Path);
            imageX = new Image(imageXPath);
            image2 = new Image(image2Path);
            imageBlank = new Image(imageBlankPath);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda bild!");
            //System.exit(0);
            return;
        }
        
    }
    
    //sets the flags for the result-part, specifying which of the results the user has chosen and not chosen
    public void ResultLabelFlagSetter(){
        
        if (resultFlagArray[resultLabellIndex] == false){
            resultFlagArray[resultLabellIndex] = true;
                numberOfResChecked++;
            }
        else if(resultFlagArray[resultLabellIndex] == true){
            resultFlagArray[resultLabellIndex] = false;
            numberOfResChecked--;
        }
        
    }
    
    //changing the result-grafics depending on chosen and unchosen 1,X,2
      public void updateLabelImage(){
        
        if (resultFlagArray[resultLabellIndex] == false){
              
            //
            if(strArrayTypeOfImage == "1"){
                disableIndexes[0] = resultLabellIndex + 1;
                disableIndexes[1] = resultLabellIndex + 2;
            }
            else if(strArrayTypeOfImage == "X"){
                disableIndexes[0] = resultLabellIndex - 1;
                disableIndexes[1] = resultLabellIndex + 1;
            }
            else if(strArrayTypeOfImage == "2"){
                disableIndexes[0] = resultLabellIndex - 1;
                disableIndexes[1] = resultLabellIndex - 2;
            }
            else{
                
            }
            
            imageViewArray[resultLabellIndex].setImage(imageBlank);
            result1X2Array[resultLabellIndex].setGraphic(imageViewArray[resultLabellIndex]);    
            
            result1X2Array[disableIndexes[0]].setDisable(false);
            result1X2Array[disableIndexes[1]].setDisable(false);
            
            checkedResults--;
            this.result13Flag  = false;
        }
        else if (resultFlagArray[resultLabellIndex] == true){
            
            if(strArrayTypeOfImage == "1"){
                imageViewArray[resultLabellIndex].setImage(image1);
                result1X2Array[resultLabellIndex].setGraphic(imageViewArray[resultLabellIndex]);
                disableIndexes[0] = resultLabellIndex + 1;
                disableIndexes[1] = resultLabellIndex + 2;
                
            }
            else if(strArrayTypeOfImage == "X"){
                imageViewArray[resultLabellIndex].setImage(imageX);
                result1X2Array[resultLabellIndex].setGraphic(imageViewArray[resultLabellIndex]);
                disableIndexes[0] = resultLabellIndex - 1;
                disableIndexes[1] = resultLabellIndex + 1;
            }
            else if(strArrayTypeOfImage == "2"){
                imageViewArray[resultLabellIndex].setImage(image2);
                result1X2Array[resultLabellIndex].setGraphic(imageViewArray[resultLabellIndex]);
                disableIndexes[0] = resultLabellIndex - 1;
                disableIndexes[1] = resultLabellIndex - 2;
            }
            else{
                
            }
            
            result1X2Array[disableIndexes[0]].setDisable(true);
            result1X2Array[disableIndexes[1]].setDisable(true);
            checkedResults++;   
            
            //when the user checks 13 results the 13Flag sets to true 
            //whihch makes it possible to click the count-button
            if(checkedResults == 13){
                
                result13Flag = true;
                
                countRowNumber();
            }
            else{
                
            }
            
        }
            
        this.MGListener.updateEnableCountCButton(result13Flag);
    }
      
      //settting the result-mark depending on 1, X, or 2
      public void countRowNumber(){
          
          int rowCounter = -1;
          
          
          for ( int i = 0 ; i < resultFlagArray.length ; i+=3){
              rowCounter ++;
              if (resultFlagArray[i] == true ){
                  result13Array[rowCounter] = "1"; 
              }
              else if (resultFlagArray[i+1] == true ) {
                  result13Array[rowCounter] = "X"; 
              }
              else if (resultFlagArray[i+2] = true){
                  result13Array[rowCounter] = "2"; 
              }
              else{
                  result13Array[rowCounter] = "Ö";
              }
          }
          
          
      }
    
      public boolean[] getResultFlags(){
          
          return this.resultFlagArray;
          
      }
      
      //resetting the flags, results, grafics of resultarray and disabling the count-button
      public void resetChosenResults(){
        
        for(int p = 0 ; p < resultFlagArray.length ; p++){
            resultFlagArray[p] = false;
        }
        
        for(int w = 0 ; w < strArray.length ; w++){
            imageViewArray[w].setImage(imageBlank);
            result1X2Array[w].setGraphic(imageViewArray[w]);
            result1X2Array[w].setDisable(false);
        }
        
        for(int q = 0 ; q < result13Array.length ; q++){
            result13Array[q] = "";
        }
        
        checkedResults = 0;
        result13Flag = false;
        this.MGListener.updateEnableCountCButton(result13Flag);
    }
      
}
