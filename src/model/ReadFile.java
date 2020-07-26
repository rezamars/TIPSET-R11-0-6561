/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Right;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;


/**
 *
 * @author Reza
 */
//handles reading default tables from textfile
public class ReadFile {
    
    private Scanner scanner;
    private File file;
    private String s = "-1";
    private String[][] R8_0_27_Tables;
    private int rowNumber = -1;
    private int signNumber = -1;
    
    
    public ReadFile(Right right1){
        
        this.R8_0_27_Tables = right1.getSystemTables();
        
        //filling the tables-array with ""
        for(int i = 0 ; i < 27 ; i++){
            for(int k = 0 ; k < 8 ; k++){
                 R8_0_27_Tables[i][k] = "";
            }
            
        }
        
    }
    
    //reads the default tables from file loaded from sv-spel
    public String[][] readFileFromTextFile(){
        
        
        InputStream inputs;
        inputs = getClass().getClassLoader().getResourceAsStream(
                "R8-0-27.txt");
        
        
        try{
                //starting the scannner
		scanner = new Scanner(inputs);
                System.out.println("Filen öppnades!");
                
                while(scanner.hasNext()){
                    
                    
                    for(int i = 0 ; i < 27 ; i++){
                        rowNumber++;
                        
                        for(int k = 0 ; k < 8 ; k++){
                            
                            signNumber++;
                            s = scanner.next();
                            
                            R8_0_27_Tables[i][k] = s;
                            
                            
                            if(k == 7){
                                s = scanner.next();
                                s = scanner.next();
                                s = scanner.next();
                                s = scanner.next();
                                s = scanner.next();
                            }
                            
                        }
                    }
                    
                }
                
                //filling the tables-array
		for(int i = 0 ; i < 27 ; i++){
                    for(int k = 0 ; k < 8 ; k++){
                        if(R8_0_27_Tables[i][k].equalsIgnoreCase("1") || R8_0_27_Tables[i][k].equalsIgnoreCase("X") || 
                                R8_0_27_Tables[i][k].equalsIgnoreCase("2")){
                            
                        }
                        else{
                            
                        }
                    }
            
                }
               
                
                //close the scanner when finished
		scanner.close();
		System.out.println("Filen är stängd.");
	}
	catch(Exception e){
            System.out.println("Fil-laddning problem!");
            System.out.println(e.getMessage());
				
	}
        
        return R8_0_27_Tables;
    }
    
    
    
}
