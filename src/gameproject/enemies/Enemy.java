/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.enemies;

import gameproject.Character;
import gameproject.GameProject;
import gameproject.GameProject.Direction;
import gameproject.Vector;
import gameproject.guns.Pistol;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public abstract class Enemy extends Character {
    
    
    Color color;
    
    public Enemy(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
    }

    @Override
    public Direction shouldMoveHor() {
        return Direction.NONE;
    }

    @Override
    public void die() {
        gp.enemies.remove(this);
    }
    
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);
    }

    @Override
    public boolean shouldShoot() {
        System.out.println(distanceTo(gp.hero));
        return Math.abs(this.getCenter().x - gp.hero.getCenter().x) < gp.CANVAS_WIDTH/2;
    }

    @Override
    public void calculateAngle() {
        targetAngle = Vector.angleBetween(position, gp.hero.position);
    }
    
    
    
}
