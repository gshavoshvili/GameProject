/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.GameProject.Direction;
import gameproject.enemies.Enemy;
import gameproject.guns.Gun;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author User
 */
public abstract class Character extends Entity{
    
    Map<String, Boolean> inputMap;
    
    public Gun gun;
   
    final int GRAV_ACCELERATION = 1;
    final int JUMP_ACCELERATION = -15;
    final int MAX_VERT_ACCELERATION = 10;
    int vert_acceleration = 0;
    boolean movedVert = false;
    boolean onGround = false;
    GameProject.Direction movedToHor = GameProject.Direction.NONE;
   

    protected int horSpeed = 4;
    
    public double targetAngle;
    
    public int health;
    
    
    
    public Character(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
        inputMap = gp.inputMap;
    }
    
    public abstract void die();
    
    public void damage(int dmg) {
        health -= dmg; 
        if (health<=0) {
            die();
        }
    }
    
    public abstract boolean shouldShoot();
    
    
    
    public void shoot() {
        gun.tryShoot(gp, position, targetAngle);
    }
    
    public abstract Direction shouldMoveHor();
    
    Entity collisionWith() {
       
        for (Platform pl : gp.platforms) {
            if (pl.collisionWith(this)) {
                return pl;
            }
        }
        
            for (Enemy en : gp.enemies) {
            if (en != this && en.collisionWith(this)) {
                return en;
               
            }
        }
        
        
            if (gp.hero != this && gp.hero.collisionWith(this)) {
                return gp.hero;
                
        
        }
            return null;
    }
    
    
    public void horMove(long delta) {
        
        Direction shouldMove = shouldMoveHor();

        if (shouldMove == Direction.LEFT) {
            
            position.x -= horSpeed;
            movedToHor = GameProject.Direction.LEFT;
        } else if (shouldMove == Direction.RIGHT) {
            
            position.x += horSpeed;
            movedToHor = GameProject.Direction.RIGHT;
        }
        
        // TODO: Only if moved?
        
        
        Entity colWith = collisionWith();
        
        if (colWith != null) {
            
            if (movedToHor == GameProject.Direction.LEFT) {
                position.x = colWith.position.x + colWith.WIDTH;
            } else {
                position.x = colWith.position.x - WIDTH;
            }
            movedToHor = GameProject.Direction.NONE;
        }
    }
    
    public void jump() {
        
    }
    
    public void vertMove(long delta) {
        movedVert = false;
        vert_acceleration += GRAV_ACCELERATION;
        
        if (vert_acceleration != 0) movedVert =true;
        position.y += vert_acceleration;

        onGround = false;
        Entity colWith = collisionWith();

        if (position.y > 400) {
            die();
        } 

        

        if (colWith != null) {
            if (vert_acceleration < 0) {
                // was moving up
                position.y = colWith.position.y + colWith.HEIGHT;
            } else {
                position.y = colWith.position.y - HEIGHT;
                onGround = true;
                //System.out.println(position.y);
            }
            vert_acceleration = 0;
            movedVert = false;

        }
        
        
    }
    
    public void changeGun() {
        
    };
    
    public abstract void calculateAngle();
    
    
    
    public final void update(long delta) {
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
    public abstract void render(GraphicsContext gc);
    
}
