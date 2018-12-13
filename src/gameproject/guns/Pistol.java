/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.guns;

import gameproject.Bullet;
import gameproject.Entity;
import gameproject.GameProject;
import gameproject.MainCharacter;
import gameproject.Vector;

/**
 *
 * @author User
 */
public class Pistol extends Gun {

    public Pistol(Entity owner) {
        this.owner = owner;
        
        if (owner instanceof MainCharacter) {
            this.timeout = 300;
            this.spread = 0.2;
        }
        else {
            this.timeout = 1000;
            this.spread = 0.5;
        }
        
    }

    

}
