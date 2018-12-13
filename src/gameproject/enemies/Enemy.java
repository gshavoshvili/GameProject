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
import gameproject.enemies.states.Idle;
import gameproject.enemies.states.State;
import gameproject.guns.Pistol;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public abstract class Enemy extends Character {
    
    
    Color color;
    public State state;
    
    public Enemy(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
        this.state = new Idle(this);
    }

    @Override
    public Direction shouldMoveHor() {
        return state.shouldMoveHor();
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
        return state.shouldShoot();
    }

    @Override
    public void calculateAngle() {
        targetAngle = Vector.angleBetween(position, gp.hero.position);
    }
    
    
    
}
