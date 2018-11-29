/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class MainCharacter {
    
    
    int x = 20;
    int y = 20;
    
    
    final int WIDTH = 30;
    final int HEIGHT = 30;
    
    
    final int GRAV_ACCELERATION = 1;
    final int JUMP_ACCELERATION = -15;
    final int MAX_VERT_ACCELERATION = 10;
    int vert_acceleration = 0;
    
    final int HOR_SPEED = 4;
    
    
    
    public void update (Map<String,Boolean> inputMap, long delta) {
        
        if (inputMap.get("A")) {
            x-= HOR_SPEED;
        }
        
        if (inputMap.get("D")) {
            x+= HOR_SPEED;
        }
        
        
        // Gravity
        
        vert_acceleration += GRAV_ACCELERATION;
        y+=vert_acceleration;
        
        boolean onGround = false;
        
        if(y > 400 - HEIGHT) {
            y=400 - HEIGHT;
            vert_acceleration = 0;
            onGround = true;
        }
        else {
            onGround = false;
        }
        
        if (inputMap.get("SPACE")) {
            if (onGround)
                vert_acceleration += JUMP_ACCELERATION;
        }
        
    }
    
    public void render (GraphicsContext gc, long delta ) {
        
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, WIDTH, HEIGHT);
        
        
    }
    
}
