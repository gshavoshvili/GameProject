/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.enemies.states;

import gameproject.GameProject;
import gameproject.GameProject.Direction;
import gameproject.enemies.Enemy;

/**
 *
 * @author User
 */
public class Idle implements State{

    Enemy owner;
    
    public Idle(Enemy owner) {
        this.owner = owner;
    }
    
    @Override
    public boolean shouldShoot() {
        
        boolean canSee = Math.abs(owner.getCenter().x - owner.gp.hero.getCenter().x) < owner.gp.CANVAS_WIDTH/2;
        if (canSee) {
            owner.state = new HeroSeen(owner);
            return true;
        }
        return false;
    }

    @Override
    public GameProject.Direction shouldMoveHor() {
        return Direction.NONE;
    }
    
}
