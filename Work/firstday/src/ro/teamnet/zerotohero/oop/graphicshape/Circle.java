package ro.teamnet.zerotohero.oop.graphicshape;

import ro.teamnet.zerotohero.oop.exceptions.Exceptie;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    @Override
    public double area() {
        return Math.PI * radius*radius;
    }

    public Circle() {
        xPos = 4;
        yPos = 5;
        radius = 6;
    }

    public Circle(int xPos) {
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) throws Exceptie {

            this.xPos = xPos;
            this.yPos = yPos;
            this.radius = radius;
            if (radius <=0 ){
                throw new Exceptie("Raza trebuie sa fie mai mare ca 0! Ai introdus: "+ radius);
            }
    }

    public void fillColor(){
        System.out.println(color);
    }

    public void fillColor(int ai){
        color = ai;
        System.out.println("The circle color is now "+color);
    }

    public void fillColor(float af){
        saturation = af;
    }

    @Override
    public String toString() {
        return String.format("center = (%s,%s) and radius = %s", xPos,yPos,radius);
    }
}
