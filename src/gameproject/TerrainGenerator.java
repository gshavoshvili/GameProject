/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javafx.scene.canvas.GraphicsContext;
/**
 *
 * @author Datch
 */
public class TerrainGenerator {
    
        public static void drawFromString(GameProject gp, GraphicsContext gc, String str, int xCount, int yCount){
            for (char ch: str.toCharArray()){
                switch(ch){
                    case '-': xCount += 32;
                        break;
                    case '/': yCount += 32;
                        break;
                    case '!' : xCount = 0;
                        break;
                    case 'g': 
                        gameproject.Platform grass = new gameproject.Platform(gp, new Vector(xCount, yCount), 32, 32, new GrassTile());
                        gp.platforms.add(grass);
                        xCount += 32;
                        break;
                    case 'd': 
                        gameproject.Platform dirt = new gameproject.Platform(gp, new Vector(xCount, yCount), 32, 32, new DirtTile());
                        gp.platforms.add(dirt);
                        xCount += 32;
                        break;
                    case 'w': 
                        gameproject.Platform water = new gameproject.Platform(gp, new Vector(xCount, yCount), 32, 32, new WaterTile());
                        gp.platforms.add(water);
                        xCount += 32;
                        break;
                }
            }
        }
    
}
