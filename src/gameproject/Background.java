/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Datch
 */
public class Background {
        
        
        Image img;
        Logger logger = Logger.getLogger(Background.class.getName());
        
        Background(){  
            try {
                BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Background.png"));
                this.img = SwingFXUtils.toFXImage(capture, null);
            } catch (IOException ex) {
                logger.info("Cant read image!!!");
            }
        }
        
        public void drawBackground(GraphicsContext gc){
//            System.out.println("here I come sonic");
            gc.drawImage(img, 0, 0);
        }
    }

