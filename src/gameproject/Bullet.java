/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author User
 */
public class Bullet extends Entity {

    Bullet(GameProject gp, int x, int y, int width, int height) {
        super(gp, x, y, width, height);
    }

    void update(long delta) {
        x++;
    }

    @Override
    void render(GraphicsContext gc, long delta) {
        gc.fillOval(x - gp.cameraOffset, y, WIDTH, HEIGHT);
    }

}
