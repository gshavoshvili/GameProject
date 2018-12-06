/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author User
 */
public class Bullet extends Entity {

    
    final static Vector initialDelta = new Vector (10,0);
    Vector deltaV; 
    
    Bullet(GameProject gp, Vector position, int width, int height, double angle) {
        super(gp, position, width, height);
        this.deltaV = initialDelta.rotate(angle);
    }

    void update(long delta) {
        position = position.add(deltaV);
    }

    @Override
    void render(GraphicsContext gc, long delta) {
        gc.fillOval(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);
    }

}
