package ro.teamnet.zerotohero.oop.zoo;

import ro.teamnet.zerotohero.oop.exceptions.AnimalManancaOmException;
import ro.teamnet.zerotohero.oop.exceptions.AnimaleleNuPotMancaAlteAnimale;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class AnimalZooRar extends Animal{
    private String nume;
    private String numeleTariiDeOrigine;

    public AnimalZooRar() {
        super();
    }

    public AnimalZooRar(String nume) {
        this.nume = nume;
    }

    public AnimalZooRar(String nume, String numeleTariiDeOrigine) {
        this.nume = nume;
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }

    @Override
    void mananca(Object object) throws AnimalManancaOmException, AnimaleleNuPotMancaAlteAnimale {
        if(object instanceof AngajatZoo){
            throw new AnimalManancaOmException();
        }else {
            System.out.println("AnimalZooRar mananca");
        }

        if (object instanceof Animal){
            throw new AnimaleleNuPotMancaAlteAnimale();
        }
    }

    @Override
    void seJoca() {
        System.out.println("AnimalulZooRar se joaca");
        doarme();
    }

    @Override
    void faceBaie() {
        System.out.println("Animalul Rar face baie!");
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumeleTariiDeOrigine() {
        return numeleTariiDeOrigine;
    }

    public void setNumeleTariiDeOrigine(String numeleTariiDeOrigine) {
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }
}
