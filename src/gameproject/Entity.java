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

    Vector position;

    final int WIDTH;
    final int HEIGHT;

    Entity(GameProject gp, Vector position, int width, int height) {
        this.gp = gp;
        this.position = position;
        this.WIDTH = width;
        this.HEIGHT = height;
    }
    
    public boolean collisionWith(Entity ent) {

        if (position.x < ent.position.x + ent.WIDTH && position.x + WIDTH > ent.position.x
                && position.y < ent.position.y + ent.HEIGHT && position.y + HEIGHT > ent.position.y) {

            return true;
        }
        return false;

    }
    
    public Vector getCenter() {
        return position.add(new Vector(WIDTH/2,HEIGHT/2));
    }
    
    public double distanceTo(Entity entity) {
        Vector center = getCenter();
        Vector oCenter = entity.getCenter();
        return Math.sqrt(Math.pow(center.x - oCenter.x, 2) + Math.pow(center.y - oCenter.y, 2));
    }

    abstract void render(GraphicsContext gc);

}
