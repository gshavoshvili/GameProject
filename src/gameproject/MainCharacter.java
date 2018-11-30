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

    MainCharacter(GameProject gp, int x, int y, int width, int height) {
        super(gp, x, y, width, height);
    }
    

    public void update(long delta) {

        Map<String, Boolean> inputMap = gp.inputMap;

        Direction movedTo = Direction.NONE;

        if (inputMap.get("A") && !inputMap.get("D")) {
            x -= HOR_SPEED;
            movedTo = Direction.LEFT;
        } else if (inputMap.get("D") && !inputMap.get("A")) {
            x += HOR_SPEED;
            movedTo = Direction.RIGHT;
        }

        Platform collisionWith = null;
        for (Platform pl : gp.platforms) {
            if (pl.collidesWith(x, y, WIDTH, HEIGHT)) {
                collisionWith = pl;
                System.out.println("Horizontal collision: " + x + " " + y + " " + collisionWith.x + " " + collisionWith.y);
                break;
            }
        }

        if (collisionWith != null) {
            if (movedTo == Direction.LEFT) {
                x = collisionWith.x + collisionWith.WIDTH;
            } else {
                x = collisionWith.x - WIDTH;
            }
        }

        // Gravity
        vert_acceleration += GRAV_ACCELERATION;
        y += vert_acceleration;

        boolean onGround = false;
        collisionWith = null;

        if (y > 400 - HEIGHT) {
            y = 400 - HEIGHT;
            vert_acceleration = 0;
            onGround = true;
        } else {
            onGround = false;
        }

        for (Platform pl : gp.platforms) {
            if (pl.collidesWith(x, y, WIDTH, HEIGHT)) {
                collisionWith = pl;
                System.out.println("Vertical collision: " + x + " " + y + " " + collisionWith.x + " " + collisionWith.y);
                break;
            }
        }

        if (collisionWith != null) {
            if (vert_acceleration < 0) {
                y = collisionWith.y + collisionWith.HEIGHT;
            } else {
                y = collisionWith.y - HEIGHT;
                onGround = true;
                System.out.println(y);
            }
            vert_acceleration = 0;

        }

        if (inputMap.get("SPACE")) {
            if (onGround) {
                vert_acceleration += JUMP_ACCELERATION;
            }
        }

    }

    @Override
    public void render(GraphicsContext gc, long delta) {

        gc.setFill(Color.BLACK);
        gc.fillRect(x - gp.cameraOffset, y, WIDTH, HEIGHT);

    }

}
