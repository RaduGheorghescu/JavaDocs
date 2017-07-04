package ro.teamnet.zerotohero.oop;

import ro.teamnet.zerotohero.oop.canvas.Canavas;
import ro.teamnet.zerotohero.oop.exceptions.AnimalManancaOmException;
import ro.teamnet.zerotohero.oop.exceptions.AnimalPeCaleDeDisparitieException;
import ro.teamnet.zerotohero.oop.exceptions.Exceptie;
import ro.teamnet.zerotohero.oop.graphicshape.Circle;
import ro.teamnet.zerotohero.oop.graphicshape.Circles;
import ro.teamnet.zerotohero.oop.graphicshape.Point;
import ro.teamnet.zerotohero.oop.graphicshape.Shape;
import ro.teamnet.zerotohero.oop.zoo.*;

public class RunApp {

    public static void main(String[] args) throws AnimalPeCaleDeDisparitieException, AnimalManancaOmException {
        Circles circles = new Circles();
        System.out.println("The default circle area is " + circles.getAreaPub());
        circles.getAreaDef();

        Canavas canavas = new Canavas();
        Shape shape = new Circle(10);
        System.out.println(shape);

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        try {
            Circle circle = new Circle(1,2, -4);
        }catch (Exceptie e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Ruleaza din nou programul!");
        }

        AnimalZooRar animal1 = new AnimalZooRar("Pinguin");
        AnimalZooRar animal2 = new AnimalZooRar("Elefant","Africa");
        AnimalZooRar animal3 = new AnimalZooRar();

        AnimalZooFeroce animalFeroce = new AnimalZooFeroce();

        AngajatZoo angajat1 = new IngrijitorZoo();
        IngrijitorZoo angajat2 = new IngrijitorZoo();

        AngajatZoo angajat3 = new VeterinarZoo();
        VeterinarZoo angajat4 = new VeterinarZoo();

//aici se vor afisa informatiile despre animal1 si animal2 (nume si tara)
        System.out.println(animal1.getNume());
        System.out.println(animal1.getNumeleTariiDeOrigine());
        System.out.println(animal2.getNume());
        System.out.println(animal2.getNumeleTariiDeOrigine());

//apelul metodelor
        angajat3.lucreaza(animal1);
        angajat3.lucreaza(animal2);
        angajat3.lucreaza(animal3);
        angajat4.lucreaza(animal1);
        angajat4.lucreaza(animal2);
        angajat4.lucreaza(animal3);

        angajat1.lucreaza(animal1);
        angajat1.lucreaza(animal2);
        angajat1.lucreaza(animal3);

        angajat2.lucreaza(animal1);
        angajat2.lucreaza(animal2);
        angajat2.lucreaza(animal3);
        try {
            angajat2.lucreaza(animal1, null);
        } catch (AnimalPeCaleDeDisparitieException e){
            System.out.println(e.getMessage());
        }
        try {
            angajat2.lucreaza(animal1, angajat1);
        }catch (AnimalManancaOmException e){
            System.out.println(e.getMessage());
        }
        angajat2.lucreaza(animal1, new String("Mancare"));

        angajat2.lucreaza(animalFeroce);
        angajat2.lucreaza(animalFeroce,null);
        angajat2.lucreaza(animalFeroce, new String("Mancare"));

        System.out.println("Finish!");
        System.out.println("Salariu angajat 1: " + angajat1.getBonusTotal());
        System.out.println("Salariu angajat 2: " + angajat2.getBonusTotal());
        System.out.println("Salariu angajat 3: " + angajat3.getBonusTotal());
        System.out.println("Salariu angajat 4: " + angajat4.getBonusTotal());

    }
}
