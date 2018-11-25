/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class GameProject extends Application {
    
    
    int x = 20;
    int y = 20;
    
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXML_test.fxml"));
    
        Scene scene = new Scene(root, 600, 400);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
        
        Canvas canvas = (Canvas) scene.lookup("#canvas");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        
         AnimationTimer animator = new AnimationTimer(){

                @Override
                public void handle(long arg0) {

                    // UPDATE
                    x++;
                    y++;

                    // RENDER
                    gc.clearRect(0, 0, 600, 400);
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, 30, 30);
                }      
            };

            animator.start();     
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
