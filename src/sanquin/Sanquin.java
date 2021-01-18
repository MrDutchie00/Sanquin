/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanquin;

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author stanb
 */
public class Sanquin extends Application {
    private String sanquin = "Sanquin";
    
    
    @Override
    public void start(Stage primaryStage) {
       
      //Login scherm  
      LoginUI root = new LoginUI();
        
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle(sanquin);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
