/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

/**
 *
 * @author User
 */
public class Vector {
    
    public double x;
    public double y;
    
    public Vector (double x,double y){
        this.x=x;
        this.y=y;
    }

    public Vector rotate(double angleRadians) {
        double sin = Math.sin(angleRadians);
        double cos = Math.cos(angleRadians);
        System.out.println(angleRadians + " " + sin + " " + cos);
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;
        System.out.println(newX + " " +  newY);
        return new Vector(newX, newY);
    }



    public Vector add(Vector vector) {
        return new Vector(
            x + vector.x,
            y + vector.y
        );
    }

    public Vector subtract(Vector vector) {
        return new Vector(
            x - vector.x,
            y - vector.y
        );
    }
    
    public static double angleBetween(Vector p1, Vector p2){
       return Math.atan2(p2.y - p1.y, p2.x - p1.x);
    }


    
}
