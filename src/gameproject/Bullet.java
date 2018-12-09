/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class Bullet extends Entity {

    
    final static Vector initialDelta = new Vector (10,0);
    Vector deltaV; 
    
    boolean shouldDestroy = false;
    
    public Bullet(GameProject gp, Vector position, int width, int height, double angle) {
        super(gp, position, width, height);
        this.deltaV = initialDelta.rotate(angle);
    }
    
    void destroy() {
        gp.bullets.remove(this);
    }

    void update(long delta) {
        position = position.add(deltaV);
        
        for (Enemy enemy : gp.enemies) {
            if (enemy.collisionWith(this)) {
                enemy.damage(3);
                shouldDestroy = true;
                break;
            }
        }
    }

    @Override
    void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillOval(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);
    }

}
