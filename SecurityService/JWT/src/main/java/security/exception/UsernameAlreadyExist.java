package security.exception;

public class UsernameAlreadyExist extends Throwable {
    public UsernameAlreadyExist(String message){
        super(message);
    }
}
