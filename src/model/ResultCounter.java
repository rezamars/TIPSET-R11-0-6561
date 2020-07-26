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
//handles counting the number of rights, depending on MGs chosen, result
public class ResultCounter {
    
    private int[] chosenMGIndexes;
    private String[] result13Array;
    private String[][] R8_0_27_Tables;
    private boolean flagOf5MGs;
    
    private int[] the8unMGmarkedIndexes = new int[8];
    
    private int amountOf13 = 0;
    private int amountOf12 = 0;
    private int amountOf11 = 0;
    private int amountOf10 = 0;
    
    private String[] eightResultsMarks = new String[8];
    private TextArea numberOfRightsTextArea;
    
    
    public ResultCounter(Left left1, Center center1, Right right1){
        
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        this.result13Array = center1.getResult13Array();
        this.R8_0_27_Tables = right1.getSystemTables();
        this.flagOf5MGs = left1.get5MGsFlag();
        this.numberOfRightsTextArea = right1.getTextArea();
    }
    
    public void startResultCounter(){
        
        specify8unMGIndexes();
        
    }
    
    //fills the 8unmarkedMGs-array with indexes that the user has chosen as not MG
    public void specify8unMGIndexes(){
        
        for (int x = 0 ; x < the8unMGmarkedIndexes.length ; x++){
            the8unMGmarkedIndexes[x] = -1;
        }
        
        int indexOf5 = 0;
        int indexOf8 = 0;
        
        
            for(int i = 0 ; i < 13 ; i++){
                
                if(indexOf5 < 5){
                    
                    if(chosenMGIndexes[indexOf5] == i){
                        indexOf5++;
                    }
                    else{
                        the8unMGmarkedIndexes[indexOf8] = i;
                        indexOf8++;
                    }
                }
                else{
                    System.out.println("in else, i= " + i);
                        the8unMGmarkedIndexes[indexOf8] = i;
                        indexOf8++;
                }
                
            }
        
        countTheRights();
    }
    
    //compares the 8 rows of the user with the default tables for R8-0-27 read from file
    private void countTheRights(){
        
        amountOf13 = 0;
        amountOf12 = 0;
        amountOf11 = 0;
        amountOf10 = 0;
        
        for(int x = 0 ; x < eightResultsMarks.length ; x++){
            
            eightResultsMarks[x] = result13Array[the8unMGmarkedIndexes[x]];
        }
        
        int numberOfRight = 0 ;
        
        for(int i = 0 ; i < 27 ; i++){
            
            numberOfRight = 0;
            for(int y = 0 ; y < 8 ; y++){
                if(R8_0_27_Tables[i][y].equalsIgnoreCase(eightResultsMarks[y])){
                    numberOfRight++;
                }
            }
            
            //adds the 5 MGs that are 1X2-marked from the user
            numberOfRight +=5;
            
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
