package ro.teamnet.zerotohero.oop.zoo;

import ro.teamnet.zerotohero.oop.exceptions.AnimalPeCaleDeDisparitieException;
import ro.teamnet.zerotohero.oop.exceptions.AnimaleleNuPotMancaAlteAnimale;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class IngrijitorZoo extends AngajatZoo {
    private int multiplicatorBonus = 3;

    @Override
    public void calculeazaBonusSalarial() {
        bonusTotal += valoareBonusPerAnimal*multiplicatorBonus;
    }

    @Override
    public void lucreaza(Animal animal) {
        calculeazaBonusSalarial();
        System.out.println("Ingrijitorul intra in cusca aninalului");
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException, AnimaleleNuPotMancaAlteAnimale {
        calculeazaBonusSalarial();
        this.lucreaza(animal);
        animal.doarme();
        animal.faceBaie();
        animal.mananca(mancare);
        if ((animal instanceof AnimalZooRar) && (mancare == null)){
            throw new AnimalPeCaleDeDisparitieException();
        }
    }
}
