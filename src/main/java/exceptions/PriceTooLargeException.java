package exceptions;

import static constants.Options.UNIT_PRICE_MAX_VALUE;

public class PriceTooLargeException extends TantouException {
    public static final String PRICE_TOO_LARGE_EXCEPTION = "Woah woah woah... \n" +
            "NO ONE is going to buy that. Try something below " + UNIT_PRICE_MAX_VALUE + ".";
    public PriceTooLargeException() {
        super(PRICE_TOO_LARGE_EXCEPTION);
    }
}
