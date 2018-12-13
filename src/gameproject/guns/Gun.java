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
public abstract class Gun {

    Entity owner;
    
    
    int timeout; //ms
    long lastShot = 0;
    
    // needed for enemy automatic
    long lastBurst = 0;
    int burstTimeout = 0;
    int bulletsShot = 0;
    final int MAX_BURST_SHOTS = 3;
    
    double spread; // angle in radians
    
    
    public void tryShoot(GameProject gp, Vector from, double targetAngle) {
        long currTime = System.currentTimeMillis();
        if (currTime - lastShot > timeout && ( bulletsShot>0 || currTime - lastBurst > burstTimeout)) {
            shoot(gp, from, targetAngle);
            lastShot = currTime;
            bulletsShot++;
            bulletsShot%=MAX_BURST_SHOTS;
            if (bulletsShot == 0){
                lastBurst = currTime;
            }
        }
    }

    
    final void shoot(GameProject gp, Vector from, double targetAngle) {
        targetAngle -= spread/2;
        targetAngle += Math.random() * spread;
        gp.bullets.add(new Bullet(gp, owner, from, targetAngle));
    }
}
