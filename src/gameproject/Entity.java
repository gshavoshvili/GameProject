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
public abstract class Entity {

    GameProject gp;

    int x;
    int y;

    final int WIDTH;
    final int HEIGHT;

    Entity(GameProject gp, int x, int y, int width, int height) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    abstract void render(GraphicsContext gc, long delta);

}
