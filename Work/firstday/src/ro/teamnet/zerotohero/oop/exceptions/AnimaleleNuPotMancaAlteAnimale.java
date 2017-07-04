package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class AnimaleleNuPotMancaAlteAnimale extends RuntimeException {
    private String message;
    public AnimaleleNuPotMancaAlteAnimale(){
        this.message = "Animalele nu pot manca alte animale!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
