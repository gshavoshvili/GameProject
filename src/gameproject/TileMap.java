/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

/**
 *
 * @author Datch
 */
import java.awt.image.BufferedImage;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public abstract class TileMap {
    Tile tl;
    int x;
    int y;
    
    
    public TileMap(Tile lt,int x,int y){
        this.tl = tl;
        this.x = x;
        this.y = y;
    }
    
    
    public boolean collision(){
        return true;
    }
    
    public abstract void Display();
}


interface Tile{
    abstract void DrawTile(GraphicsContext gc, int x, int y);
    

}


class GrassTile implements Tile{
    Image img;
    Logger logger = Logger.getLogger(GrassTile.class.getName());

    GrassTile() {
        try {
            BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Grass.png"));
            this.img = SwingFXUtils.toFXImage(capture, null);
        } catch (IOException ex) {
            logger.info("Cant read image!!!");
        }
    }
    
    @Override
    public void DrawTile(GraphicsContext gc, int x, int y){
//        GraphicsContext.drawImage(img, 100, 100);
//        System.out.println("test");
        gc.drawImage(img,x,y);
    }
}

class DirtTile implements Tile{
    
    Image img;
    Logger logger = Logger.getLogger(GrassTile.class.getName());

    DirtTile() {
        try {
            BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Grass.png"));
            this.img = SwingFXUtils.toFXImage(capture, null);
        } catch (IOException ex) {
            logger.info("Cant read image!!!");
        }
    }
    
    @Override
    public void DrawTile(GraphicsContext gc, int x, int y){
//        GraphicsContext.drawImage(img, 100, 100);
//        System.out.println("test");
        gc.drawImage(img,x,y);
    }
}

class WaterTile implements Tile{
    
     Image img;
    Logger logger = Logger.getLogger(GrassTile.class.getName());

    WaterTile() {
        try {
            BufferedImage capture = ImageIO.read(getClass().getResourceAsStream("resources/Grass.png"));
            this.img = SwingFXUtils.toFXImage(capture, null);
        } catch (IOException ex) {
            logger.info("Cant read image!!!");
        }
    }
    
    @Override
    public void DrawTile(GraphicsContext gc, int x, int y){
//        GraphicsContext.drawImage(img, 100, 100);
//        System.out.println("test");
        gc.drawImage(img,x,y);
    }
}