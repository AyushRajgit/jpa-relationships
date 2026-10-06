package in.cper.database.ECommerceOrderEngine.exceptions;

public class NoStockAvailableException extends RuntimeException {
    public NoStockAvailableException(String message) {
        super(message);
    }
}
