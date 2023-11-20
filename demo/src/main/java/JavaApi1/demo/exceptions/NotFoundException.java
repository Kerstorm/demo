package JavaApi1.demo.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
