package exceptions;

import static constants.Options.QUANTITY_MAX_VALUE;

public class QuantityTooLargeException extends TantouException {
    public static final String QUANTITY_TOO_LARGE_EXCEPTION = "Woah woah woah... \n" +
            "We aren't THAT successful. Try something below " + QUANTITY_MAX_VALUE + ".";
    public QuantityTooLargeException() {
        super(QUANTITY_TOO_LARGE_EXCEPTION);
    }
}
