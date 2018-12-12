/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.GameProject.Direction;
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
        gun = new Pistol(this);
        health = 5;
    }

    public Direction shouldMoveHor() {
        return Direction.NONE;
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
        System.out.println(distanceTo(gp.hero));
        return Math.abs(this.getCenter().x - gp.hero.getCenter().x) < gp.CANVAS_WIDTH/2;
    }

    @Override
    void calculateAngle() {
        targetAngle = Vector.angleBetween(position, gp.hero.position);
    }
    
    
    
}
