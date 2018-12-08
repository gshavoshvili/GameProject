/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.guns;

import gameproject.GameProject;
import gameproject.Vector;

/**
 *
 * @author User
 */
public abstract class Gun {

    int timeout; //ms
    long lastShot = 0;

    public void tryShoot(GameProject gp, Vector from, double targetAngle) {
        long currTime = System.currentTimeMillis();
        if (currTime - lastShot > timeout) {
            shoot(gp, from, targetAngle);
            lastShot = currTime;
        }
    }

    abstract void shoot(GameProject gp, Vector from, double targetAngle);
}
