/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipset;

import Grafik.Center;
import Grafik.Left;
import Grafik.Right;
import Grafik.Top;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ClearButtonListener;
import model.CountButtonListener;
import model.MGListener;
import model.ResultCounter;
import model.ResultRowListener;

/**
 *
 * @author Reza
 */
//this class is controller of the program, it contains instance variables and the main-method
public class Controller extends Application {
    
    
    private Top top = new Top();
    private Left left = new Left();
    private Center center = new Center();
    private Right right = new Right();
    private Label[] MGArray ;
    private MGListener MGlistener;
    private Label[] result1X2Array;
    private ResultRowListener resultRowListener;
    private CountButtonListener countButtonListener;
    
    private ResultCounter resultCounter;
    private boolean flag13 = false;
    
    private ClearButtonListener clearButtonListener;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //get screenresolution
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        //set screen-size, procentage of screenresolution
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth((primaryScreenBounds.getWidth())/1.4);
        primaryStage.setHeight((primaryScreenBounds.getHeight())/1.2);
        
        //referring to arrays
        this.MGArray = left.getMGArray();
        this.result1X2Array = center.getResultArray();
        
        //creating new instances of various objects
        MGlistener = new MGListener(MGArray, left, center, right, flag13);
        MGlistener.addMGLabelListener();
        
        resultRowListener = new ResultRowListener(result1X2Array, center, MGlistener, flag13);
        resultRowListener.addResultLabelListener();
        
        resultCounter = new ResultCounter(left,center,right);
        
        countButtonListener = new CountButtonListener(right, resultCounter);
        countButtonListener.addCountButtonListener();
        
        clearButtonListener = new ClearButtonListener(right, MGlistener, flag13, resultRowListener);
        clearButtonListener.addClearButtonListener();
        
        
        View v = new View(primaryStage, top, left, center, right);
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
