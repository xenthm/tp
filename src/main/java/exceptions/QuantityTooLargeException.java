package exceptions;

public class QuantityTooLargeException extends TantouException {
    public static final String QUANTITY_TOO_LARGE_EXCEPTION = "Woah woah woah... \n" +
            "We aren't THAT successful. Try something below 1000000000.";
    public QuantityTooLargeException() {
        super(QUANTITY_TOO_LARGE_EXCEPTION);
    }
}
