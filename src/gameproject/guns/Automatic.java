/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.guns;

import gameproject.Bullet;
import gameproject.Entity;
import gameproject.GameProject;
import gameproject.Vector;

/**
 *
 * @author User
 */
public class Automatic extends Gun {

    public Automatic(Entity owner) {
        this.owner = owner;
        this.timeout = 100;
        
    }

    @Override
    void shoot(GameProject gp, Vector from, double targetAngle) {
        gp.bullets.add(new Bullet(gp, owner, from, 30, 30, targetAngle));
    }
}
