/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author Datch
 */
public class DeathWinScreen {
    Image img;
    int timeout;
    double blurFactor = 3.2;
    double alpha = .5;
    GaussianBlur blur = new GaussianBlur(blurFactor);
//    ColorAdjust ca = new ColorAdjust();
    
    DeathWinScreen(GameProject gp, GraphicsContext gc, String s){
        long currentTime = System.currentTimeMillis();
        if(s == "Death"){
            try{
                BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/You-Died.jpg"));
                this.img = SwingFXUtils.toFXImage(capture, null);
                gc.save();
                gc.setGlobalAlpha(alpha);
                gc.drawImage(img, 0, 0);
                gc.restore();

    //            gc.applyEffect(blur);

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }else if (s == "Win"){

            try{
                BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Win.png"));
                this.img = SwingFXUtils.toFXImage(capture, null);
                gc.save();
                gc.setGlobalAlpha(alpha);
                gc.drawImage(img, 0, 0);
                gc.restore();       
    //            gc.applyEffect(blur);

            }
            catch(Exception e){
                e.printStackTrace();
            }          
        }
    }
}
