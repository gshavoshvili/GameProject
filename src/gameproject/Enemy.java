/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.guns.Pistol;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class Enemy extends Character {
    
    
    public Enemy(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
        gun = new Pistol();
        health = 5;
    }

    

    @Override
    void die() {
        gp.enemies.remove(this);
    }
    
    @Override
    void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);
    }

    @Override
    boolean shouldShoot() {
        return false;
    }
    
}
