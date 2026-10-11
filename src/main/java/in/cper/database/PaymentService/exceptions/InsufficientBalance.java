package in.cper.database.PaymentService.exceptions;

public class InsufficientBalance extends RuntimeException {
    public InsufficientBalance(String message) {
        super(message);
    }
}
