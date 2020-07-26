/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Right;
import javafx.scene.control.Button;

/**
 *
 * @author Reza
 */
//takes care of the functionality that handles when and how to count the results
//depending of which MGs and the result that the user inputs
public class CountButtonListener {
    
    private Right right;
    private Button countButton;
    private ResultCounter resultCounter;
    
    
    public CountButtonListener(Right right1, ResultCounter resultCounter1){
        
        this.right = right1;
        this.countButton = right.getCountButton();
        this.resultCounter = resultCounter1;
        
    }
    
    //adds listener to the count-button, starts counting at click
    public void addCountButtonListener(){
        
        countButton.setOnAction(e -> {
            resultCounter.startResultCounter();
        });
            
    }
    
}
