/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
/**
 *
 * @author User
 */
public class Bullet extends Entity {

    Entity shooter;

    final static Vector initialDelta = new Vector(10, 0);
    Vector deltaV;
    
    
    
    boolean shouldDestroy = false;

    Image img;
    Logger logger = Logger.getLogger(Bullet.class.getName());
        
    
    public Bullet(GameProject gp, Entity shooter, Vector position, double angle) {
        super(gp, position, 25, 25);
        this.shooter = shooter;
        this.deltaV = initialDelta.rotate(angle);
        
        
            try {
                BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Bullet.png"));
                this.img = SwingFXUtils.toFXImage(capture, null);
            } catch (IOException ex) {
                logger.info("Cant read image!!!");
            }
        }

    void destroy() {
        gp.bullets.remove(this);
    }

    void update(long delta) {
        position = position.add(deltaV);

        if (shooter instanceof MainCharacter) {
            for (Enemy enemy : gp.enemies) {
                if (enemy.collisionWith(this)) {
                    enemy.damage(3);
                    shouldDestroy = true;
                    break;
                }
            }
        }
        else {
            if (gp.hero.collisionWith(this)) {
                gp.hero.damage(3);
                shouldDestroy = true;
            }
        }
        for (Platform platform : gp.platforms) {
            if (platform.collisionWith(this)) {
                shouldDestroy = true;
                break;
            }
        }
    }

    @Override
    void render(GraphicsContext gc) {
//        gc.setFill(Color.BLACK);
//        gc.fillOval(position.x - gp.cameraOffset, position.y, WIDTH, HEIGHT);
          gc.drawImage(img, position.x - gp.cameraOffset, position.y);
    }

}
