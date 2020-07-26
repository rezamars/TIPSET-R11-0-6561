/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Right;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author Reza
 */
//handles resetting the Mgs and result and the textarea
public class ClearButtonListener {
    
    private Right right;
    private Button clearButton;
    private MGListener MGlistener;
    private boolean[] mgFlagArray;
    private boolean result13Flag;
    private ResultRowListener resultRowListener;
    private boolean[] resultFlagArray;
    private TextArea numberOfRightsTextArea;
    
    
    public ClearButtonListener(Right right1, MGListener MGlistener1, boolean flag13, ResultRowListener resultRowListener1){
        
        this.right = right1;
        this.clearButton = right.getClearButton();
        this.MGlistener = MGlistener1;
        this.mgFlagArray = MGlistener1.getMgFlagArray();
        this.result13Flag = flag13;
        this.resultRowListener = resultRowListener1;
        this.resultFlagArray = resultRowListener.getResultFlags();
        this.numberOfRightsTextArea = right.getTextArea();
        
    }
    
    //add listener to clear-button
    //at click: resetting the MCGs,flags, the results and the textarea
    //and updating the grafics
    public void addClearButtonListener(){
        
        clearButton.setOnAction(e -> {
            
            MGlistener.resetMGs();
            MGlistener.updateLabelImage();
            MGlistener.updateEnableCountCButton(result13Flag);
            
            for(int x = 0 ; x < mgFlagArray.length ; x++){
                this.resultFlagArray[x] = false;
            }
            
            resultRowListener.resetChosenResults();
            numberOfRightsTextArea.setText("Antal rätt:\n13 rätt: \n12 rätt:\n11 rätt:\n10 rätt:");
            
        });
        
    }
    
}
