package gameproject;


import gameproject.GameProject;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Datch
 */
public class HealthBar {
    Image img;
    HealthBar(GameProject gp, GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(60.0, 20, 144, 25.0);
        gc.setFill(Color.RED);
        gc.fillRect(62.0, 22, 140 * gp.hero.health/30, 22.0);
            try{
                BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Full_Heart.png"));
                this.img = SwingFXUtils.toFXImage(capture, null);
                gc.drawImage(img, 25, 20);
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
}
