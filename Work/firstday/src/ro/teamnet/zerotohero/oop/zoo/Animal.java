package ro.teamnet.zerotohero.oop.zoo;

import ro.teamnet.zerotohero.oop.exceptions.AnimaleleNuPotMancaAlteAnimale;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public abstract class Animal {
    abstract void mananca(Object object) throws AnimaleleNuPotMancaAlteAnimale;
    abstract void seJoca();
    abstract void  faceBaie();
    public void doarme(){
        System.out.println("Animaul doarme! Nu il deranja ca te mananca!!!!");
    }

    public Animal() {
        System.out.println("Animal nou!");
    }
}
