package ro.teamnet.zerotohero.oop.zoo;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class AngajatZoo {
    int valoareBonusPerAnimal = 50;
    int bonusTotal = 0;
    public void lucreaza(Animal animal){
        calculeazaBonusSalarial();
    }
    public void calculeazaBonusSalarial(){
        this.bonusTotal += valoareBonusPerAnimal;
    }

    public int getBonusTotal() {
        return bonusTotal;
    }
}
