package auction.com.example.OnlineAucSpring.exception;

public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public APIException(){
    }

    public APIException(String message) {
        super(message);
    }
}
