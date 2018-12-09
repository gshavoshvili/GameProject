/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.guns.Gun;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author User
 */
public abstract class Character extends Entity{
    
    Map<String, Boolean> inputMap;
    
    Gun gun;
   
    final int GRAV_ACCELERATION = 1;
    final int JUMP_ACCELERATION = -15;
    final int MAX_VERT_ACCELERATION = 10;
    int vert_acceleration = 0;
    boolean movedVert = false;
    boolean onGround = false;
    GameProject.Direction movedToHor = GameProject.Direction.NONE;
    Platform collisionWith = null;

    final int HOR_SPEED = 4;
    
    double targetAngle;
    
    int health;
    
    
    
    Character(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
        inputMap = gp.inputMap;
    }
    
    abstract void die();
    
    void damage(int dmg) {
        health -= dmg; 
        if (health<=0) {
            die();
        }
    }
    
    abstract boolean shouldShoot();
    
    
    
    public void shoot() {
        gun.tryShoot(gp, position, targetAngle);
    }
    
    
    void horMove(long delta) {
        
        

        if (inputMap.get("A") && !inputMap.get("D")) {
            
            position.x -= HOR_SPEED;
            movedToHor = GameProject.Direction.LEFT;
        } else if (inputMap.get("D") && !inputMap.get("A")) {
            
            position.x += HOR_SPEED;
            movedToHor = GameProject.Direction.RIGHT;
        }
        
        // TODO: Only if moved?
        collisionWith = null;
        for (Platform pl : gp.platforms) {
            if (pl.collisionWith(this)) {
                collisionWith = pl;
                // System.out.println("Horizontal collision: " + position.x + " " + position.y + " " + collisionWith.position.x + " " + collisionWith.position.y);
                break;
            }
        }

        if (collisionWith != null) {
            
            if (movedToHor == GameProject.Direction.LEFT) {
                position.x = collisionWith.position.x + collisionWith.WIDTH;
            } else {
                position.x = collisionWith.position.x - WIDTH;
            }
            movedToHor = GameProject.Direction.NONE;
        }
    }
    
    void jump() {
        
    }
    
    void vertMove(long delta) {
        movedVert = false;
        vert_acceleration += GRAV_ACCELERATION;
        
        if (vert_acceleration != 0) movedVert =true;
        position.y += vert_acceleration;

        onGround = false;
        collisionWith = null;

        if (position.y > 400 - HEIGHT) {
            position.y = 400 - HEIGHT;
            vert_acceleration = 0;
            onGround = true;
        } else {
            onGround = false;
        }

        for (Platform pl : gp.platforms) {
            if (pl.collisionWith(this)) {
                collisionWith = pl;
                // System.out.println("Vertical collision: " + position.x + " " + position.y + " " + collisionWith.position.x + " " + collisionWith.position.y);
                break;
            }
        }

        if (collisionWith != null) {
            if (vert_acceleration < 0) {
                // was moving up
                position.y = collisionWith.position.y + collisionWith.HEIGHT;
            } else {
                position.y = collisionWith.position.y - HEIGHT;
                onGround = true;
                //System.out.println(position.y);
            }
            vert_acceleration = 0;
            movedVert = false;

        }
        
        
    }
    
    void changeGun() {
        
    };
    
    void calculateAngle() {
        
    }
    
    
    
    final void update(long delta) {
        changeGun();
        horMove(delta);
        vertMove(delta);
        jump();
        calculateAngle();
        if (shouldShoot()) {
            shoot();
        }
        
        
        
    };
    
    @Override
    abstract void render(GraphicsContext gc);
    
}
