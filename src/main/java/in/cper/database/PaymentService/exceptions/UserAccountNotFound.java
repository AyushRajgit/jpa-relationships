package in.cper.database.PaymentService.exceptions;

public class UserAccountNotFound extends RuntimeException {
    public UserAccountNotFound(String message) {
        super(message);
    }
}
