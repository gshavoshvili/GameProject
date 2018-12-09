/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import gameproject.TileMap;
import gameproject.Tile;

public class Platform extends Entity {
    
    Tile tl;

    Platform(GameProject gp, Vector position, int width, int height, Tile tl) {
        super(gp, position, width, height);
        this.tl = tl;
    }

    

    @Override
    public void render(GraphicsContext gc) {

          tl.DrawTile(gc, (int)(position.x - gp.cameraOffset), (int)position.y);
//        gc.setFill(Color.BLACK);
//        gc.fillRect(x - gp.cameraOffset, y, WIDTH, HEIGHT);

    }

}
