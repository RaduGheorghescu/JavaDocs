package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class Exceptie extends Exception {
    private String message;
    public Exceptie(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
