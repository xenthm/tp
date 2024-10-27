package exceptions;

public class PriceTooLargeException extends TantouException {
    public static final String PRICE_TOO_LARGE_EXCEPTION = "Woah woah woah... \n" +
            "NO ONE is going to buy that. Try something below 999999999.";
    public PriceTooLargeException() {
        super(PRICE_TOO_LARGE_EXCEPTION);
    }
}
