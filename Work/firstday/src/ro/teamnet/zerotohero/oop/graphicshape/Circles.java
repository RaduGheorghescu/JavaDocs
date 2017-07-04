package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class Circles {
    public double getAreaPub(){
        Circle circle= new Circle();
        return circle.area();
    }

    public void getAreaDef(){
        Circle circle= new Circle();
        circle.fillColor();
        circle.fillColor(4);
        circle.fillColor(5.f);
    }
}
