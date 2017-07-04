package ro.teamnet.zerotohero.oop.zoo;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class VeterinarZoo extends AngajatZoo{
    private int multiplicatorBonus = 2;
    @Override
    public void calculeazaBonusSalarial() {
        bonusTotal += valoareBonusPerAnimal*multiplicatorBonus;
    }

    @Override
    public void lucreaza(Animal animal) {
        calculeazaBonusSalarial();
        System.out.println("Veterinarul are grija de animal");
        if(animal instanceof AnimalZooFeroce){
            animal.faceBaie();
        }
    }
}
