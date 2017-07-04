package ro.teamnet.zerotohero.oop.zoo;

import ro.teamnet.zerotohero.oop.exceptions.AnimalManancaOmException;
import ro.teamnet.zerotohero.oop.exceptions.AnimaleleNuPotMancaAlteAnimale;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {

    @Override
    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }

    @Override
    void mananca(Object object) throws AnimalManancaOmException, AnimaleleNuPotMancaAlteAnimale {
        System.out.println("AnimalZooFeroce manca");
        if(object instanceof AngajatZoo){
            throw new AnimalManancaOmException();
        }

        if (object instanceof Animal){
            throw new AnimaleleNuPotMancaAlteAnimale();
        }

    }

    @Override
    void seJoca() {
        System.out.println("AnimalZooFeroce se joca");
    }

    @Override
    void faceBaie() {
        System.out.println("AnimalZooFeroce face baie");

    }
}
