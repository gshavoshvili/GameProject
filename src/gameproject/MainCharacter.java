/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.GameProject.Direction;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class MainCharacter extends Entity {

    final int GRAV_ACCELERATION = 1;
    final int JUMP_ACCELERATION = -15;
    final int MAX_VERT_ACCELERATION = 10;
    int vert_acceleration = 0;

    final int HOR_SPEED = 4;
    
    Vector mousePositionOnScreen = new Vector(0,0);
    double mouseAngle;

    MainCharacter(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
    }
    
    public Vector getCenter() {
        return position.add(new Vector(WIDTH/2,HEIGHT/2));
    }
    
    public void calculateMouseAngle() {
        double angle = Vector.angleBetween(getCenter(), mousePositionOnScreen.add(new Vector(gp.cameraOffset,0)));
        System.out.println(angle);
        mouseAngle = angle;
    }
    

    public void update(long delta) {

        Map<String, Boolean> inputMap = gp.inputMap;
        
        
        Direction movedToHor = Direction.NONE;
        

        if (inputMap.get("A") && !inputMap.get("D")) {
            
            position.x -= HOR_SPEED;
            movedToHor = Direction.LEFT;
        } else if (inputMap.get("D") && !inputMap.get("A")) {
            
            position.x += HOR_SPEED;
            movedToHor = Direction.RIGHT;
        }
        
        // TODO: Only if moved?
        Platform collisionWith = null;
        for (Platform pl : gp.platforms) {
            if (pl.collidesWith(position, WIDTH, HEIGHT)) {
                collisionWith = pl;
                // System.out.println("Horizontal collision: " + position.x + " " + position.y + " " + collisionWith.position.x + " " + collisionWith.position.y);
                break;
            }
        }

        if (collisionWith != null) {
            
            if (movedToHor == Direction.LEFT) {
                position.x = collisionWith.position.x + collisionWith.WIDTH;
            } else {
                position.x = collisionWith.position.x - WIDTH;
            }
            movedToHor = Direction.NONE;
        }

        // Gravity
        
        boolean movedVert = false;
        vert_acceleration += GRAV_ACCELERATION;
        
        if (vert_acceleration != 0) movedVert =true;
        position.y += vert_acceleration;

        boolean onGround = false;
        collisionWith = null;

        if (position.y > 400 - HEIGHT) {
            position.y = 400 - HEIGHT;
            vert_acceleration = 0;
            onGround = true;
        } else {
            onGround = false;
        }

        for (Platform pl : gp.platforms) {
            if (pl.collidesWith(position, WIDTH, HEIGHT)) {
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

        if (inputMap.get("SPACE")) {
            if (onGround) {
                vert_acceleration += JUMP_ACCELERATION;
            }
        }
        
        if (movedToHor != Direction.NONE || movedVert) {
            calculateMouseAngle();
        }

    }

    @Override
    public void render(GraphicsContext gc, long delta) {

        gc.setFill(Color.BLACK);
        gc.fillRect(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);

    }

}
