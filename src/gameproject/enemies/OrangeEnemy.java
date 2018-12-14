/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.enemies;

import gameproject.GameProject;
import gameproject.Vector;
import gameproject.guns.Automatic;
import gameproject.guns.Pistol;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class OrangeEnemy extends Enemy{
    
    
    
     public OrangeEnemy(GameProject gp, Vector position) {
        super(gp, position, 32, 64);
        gun = new Automatic(this);
        health = 24;
        color = Color.ORANGE;
        this.horSpeed=2;
        
    }
}
