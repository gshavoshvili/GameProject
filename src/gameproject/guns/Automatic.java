/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.guns;

import gameproject.Bullet;
import gameproject.enemies.Enemy;
import gameproject.Entity;
import gameproject.GameProject;
import gameproject.MainCharacter;
import gameproject.Vector;

/**
 *
 * @author User
 */
public class Automatic extends Gun {

    // only needed for enemies
    double timeBetweenBursts;
    
    public Automatic(Entity owner) {
        this.owner = owner;
        
        
        if (owner instanceof Enemy) {
            burstTimeout = 1000;
            this.spread = 0.8;
            this.timeout = 100;
        }
        else {
            this.spread = 0.3;
            this.timeout = 160;
        }
    }
    
    
  

   
}
