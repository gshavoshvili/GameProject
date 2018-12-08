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

    Platform(GameProject gp, int x, int y, int width, int height, Tile tl) {
        super(gp, x, y, width, height);
        this.tl = tl;
    }

    public boolean collidesWith(int ox, int oy, int owidth, int oheight) {

        if (x < ox + owidth && x + WIDTH > ox
                && y < oy + oheight && y + HEIGHT > oy) {

            return true;
        }
        return false;

    }

    @Override
    public void render(GraphicsContext gc, long delta) {

          tl.DrawTile(gc, x, y);
//        gc.setFill(Color.BLACK);
//        gc.fillRect(x - gp.cameraOffset, y, WIDTH, HEIGHT);

    }

}
