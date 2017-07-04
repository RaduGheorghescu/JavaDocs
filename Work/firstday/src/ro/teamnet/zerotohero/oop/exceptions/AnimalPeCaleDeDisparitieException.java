package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class AnimalPeCaleDeDisparitieException extends Exception {
    private String message;
    public AnimalPeCaleDeDisparitieException(){
        this.message = "Moaree!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
