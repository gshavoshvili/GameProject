/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject.enemies.states;

import gameproject.GameProject;
import gameproject.GameProject.Direction;
import gameproject.MainCharacter;
import gameproject.enemies.Enemy;
import gameproject.enemies.OrangeEnemy;

/**
 *
 * @author User
 */
public class HeroSeen implements State{

    Enemy owner;
    
    HeroSeen(Enemy owner) {
        this.owner = owner;
    }

    @Override
    public boolean shouldShoot() {
        return true;
    }

    @Override
    public GameProject.Direction shouldMoveHor() {
        
        if (owner instanceof OrangeEnemy) {
            MainCharacter hero = owner.gp.hero;

            if (hero.position.x + hero.WIDTH < owner.position.x) {
                return Direction.LEFT;
            }
            if (hero.position.x > owner.position.x + owner.WIDTH){
                return Direction.RIGHT;
            }
        }   
        return Direction.NONE;
    }
    
}
