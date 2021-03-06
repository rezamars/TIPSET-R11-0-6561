/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Center;
import Grafik.Left;
import Grafik.Right;
import javafx.scene.control.TextArea;

/**
 *
 * @author Reza
 */
//handles counting the number of rights, depending on MGs chosen and the result
public class ResultCounter {
    
    private int[] chosenMGIndexes;
    private String[] result13Array;
    private String[][] R11_0_729_Tables;
    private boolean flagOf2MGs;
    
    private int[] the11unMGmarkedIndexes = new int[11];
    
    private int amountOf13 = 0;
    private int amountOf12 = 0;
    private int amountOf11 = 0;
    private int amountOf10 = 0;
    
    private String[] elevenResultsMarks = new String[11];
    private TextArea numberOfRightsTextArea;
    
    
    public ResultCounter(Left left1, Center center1, Right right1){
        
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        this.result13Array = center1.getResult13Array();
        this.R11_0_729_Tables = right1.getSystemTables();
        this.flagOf2MGs = left1.get2MGsFlag();
        this.numberOfRightsTextArea = right1.getTextArea();
    }
    
    public void startResultCounter(){
        
        specify11unMGIndexes();
        
    }
    
    //fills the 11unmarkedMGs-array with indexes that the user has chosen as not MG
    public void specify11unMGIndexes(){
        
        for (int x = 0 ; x < the11unMGmarkedIndexes.length ; x++){
            the11unMGmarkedIndexes[x] = -1;
        }
        
        int indexOf2 = 0;
        int indexOf11 = 0;
        
        
            for(int i = 0 ; i < 13 ; i++){
                
                if(indexOf2 < 2){
                    
                    if(chosenMGIndexes[indexOf2] == i){
                        indexOf2++;
                    }
                    else{
                        the11unMGmarkedIndexes[indexOf11] = i;
                        indexOf11++;
                    }
                }
                else{
                    System.out.println("in else, i= " + i);
                        the11unMGmarkedIndexes[indexOf11] = i;
                        indexOf11++;
                }
                
            }
        
        countTheRights();
    }
    
    //compares the 11 rows of the user with the default tables for R8-0-27 read from file
    private void countTheRights(){
        
        amountOf13 = 0;
        amountOf12 = 0;
        amountOf11 = 0;
        amountOf10 = 0;
        
        for(int x = 0 ; x < elevenResultsMarks.length ; x++){
            
            elevenResultsMarks[x] = result13Array[the11unMGmarkedIndexes[x]];
        }
        
        int numberOfRight = 0 ;
        
        for(int i = 0 ; i < 729 ; i++){
            
            numberOfRight = 0;
            for(int y = 0 ; y < 11 ; y++){
                if(R11_0_729_Tables[i][y].equalsIgnoreCase(elevenResultsMarks[y])){
                    numberOfRight++;
                }
            }
            
            //adds the 2 MGs that are 1X2-marked from the user
            numberOfRight +=2;
            
            //adds number of rights to the 4 levels of won
            if (numberOfRight == 13){
                amountOf13++;
            }
            else if (numberOfRight == 12){
                amountOf12++;
            }
            else if (numberOfRight == 11){
                amountOf11++;
            }
            else if (numberOfRight == 10){
                amountOf10++;
            }
            
        }
        
        setRightsInTextArea();
        
    }
    
    //fills the ammount of rights in textarea
    public void setRightsInTextArea(){
        
        numberOfRightsTextArea.setText("Antal rätt:\n13 rätt: " + amountOf13 + "\n12 rätt: " + amountOf12 + 
                "\n11 rätt: " + amountOf11 + "\n10 rätt: " + amountOf10);
        
    }
    
}
