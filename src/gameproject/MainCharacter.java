/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.GameProject.Direction;
import gameproject.guns.Automatic;
import gameproject.guns.Gun;
import gameproject.guns.Pistol;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class MainCharacter extends Character {

   
    
    Vector mousePositionOnScreen = new Vector(0,0);
    
    
    Gun[] guns = { new Pistol(), new Automatic() };
    

    MainCharacter(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
        gun = guns[0];
    }
    
    
    
    public void calculateMouseAngle() {
        double angle = Vector.angleBetween(getCenter(), mousePositionOnScreen.add(new Vector(gp.cameraOffset,0)));
        System.out.println(angle);
        targetAngle = angle;
    }
    
    

    @Override
    void jump() {
        if (inputMap.get("SPACE")) {
            if (onGround) {
                vert_acceleration += JUMP_ACCELERATION;
            }
        }
    }
    
    @Override
    void changeGun() {
        if (inputMap.get("DIGIT1")) {
            gun = guns[0];
        }
        else if (inputMap.get("DIGIT2")) {
            gun = guns[1];
        }
    }
    
    @Override
    void calculateAngle() {
        if (movedToHor != Direction.NONE || movedVert) {
            calculateMouseAngle();
        }
    }
    
    boolean shouldShoot() {
        return inputMap.get("LMB");
    }
    
    
    

    @Override
    public void render(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);

    }

    @Override
    void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
