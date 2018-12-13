/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.enemies;

import gameproject.GameProject;
import gameproject.Vector;
import gameproject.enemies.Enemy;
import gameproject.guns.Pistol;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class RedEnemy extends Enemy{
    
    
    
    public RedEnemy(GameProject gp, Vector position, int width, int height) {
        super(gp, position, width, height);
        gun = new Pistol(this);
        health = 6;
        color = Color.RED;
    }
    
}
