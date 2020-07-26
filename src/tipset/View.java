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
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Reza
 */
//this class creates the stage and sets the layout and adds the 4 layout objects
public class View {
    
    private BorderPane borderPane = new BorderPane();
    
    private Top top;
    private Left left;
    private Center center;
    private Right right;
    
    public View(Stage primaryStage, Top top1, Left left1, Center center1, Right right1){
        
        //referring to grafical objects
        this.top = top1;
        this.left= left1;
        this.center = center1;
        this.right = right1;
        
        //setting the grafic-objects to layout
        borderPane.setTop(top);
        borderPane.setLeft(left);
        borderPane.setCenter(center);
        borderPane.setRight(right);
        
        Scene scene = new Scene(borderPane, 300, 250);
        
        primaryStage.setTitle("Tipset");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
}
