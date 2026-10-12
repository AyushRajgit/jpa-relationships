package in.cper.database.PaymentService.exceptions;

public class WrongPassKeyException extends RuntimeException {
    public WrongPassKeyException(String message) {
        super(message);
    }
}
