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

    public boolean collidesWith(Vector oposition, int owidth, int oheight) {

        if (position.x < oposition.x + owidth && position.x + WIDTH > oposition.x
                && position.y < oposition.y + oheight && position.y + HEIGHT > oposition.y) {

            return true;
        }
        return false;

    }

    @Override
    public void render(GraphicsContext gc, long delta) {

          tl.DrawTile(gc, position.x - gp.cameraOffset, position.y);
//        gc.setFill(Color.BLACK);
//        gc.fillRect(x - gp.cameraOffset, y, WIDTH, HEIGHT);

    }

}
