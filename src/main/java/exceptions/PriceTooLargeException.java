package exceptions;

import static constants.Options.MAX_UNIT_PRICE_VALUE;

public class PriceTooLargeException extends TantouException {
    public static final String PRICE_TOO_LARGE_EXCEPTION = "Woah woah woah... \n" +
            "NO ONE is going to buy that. Try something below " + MAX_UNIT_PRICE_VALUE + ".";
    public PriceTooLargeException() {
        super(PRICE_TOO_LARGE_EXCEPTION);
    }
}
