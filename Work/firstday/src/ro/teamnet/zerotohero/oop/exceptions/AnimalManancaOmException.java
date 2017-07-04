package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class AnimalManancaOmException extends RuntimeException {
    private String message;
    public AnimalManancaOmException(){
        this.message="Ajutoooooooooooooooooooooooooooooooor";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
