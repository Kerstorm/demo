package JavaApi1.demo.exceptions;

public class NotUniquePrimaryKeyException extends RuntimeException{
    public NotUniquePrimaryKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
