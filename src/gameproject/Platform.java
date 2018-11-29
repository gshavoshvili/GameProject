/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Platform {
   


    
    
    GameProject gp;
    
    int x;
    int y;
    
    
    final int WIDTH = 90;
    final int HEIGHT = 30;
    
    
   
    
    Platform (GameProject gp, int x, int y) {
        this.gp = gp;
        this.x = x;
        this.y = y;
    }
    
    
    
    public void render (GraphicsContext gc, long delta ) {
        
        gc.setFill(Color.BLACK);
        gc.fillRect(x - gp.cameraOffset, y, WIDTH, HEIGHT);
        
        
    }
    
}

    
    

