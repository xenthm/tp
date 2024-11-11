package exceptions;

import static constants.Options.PRICE_OPTION;

public class PriceWrongFormatException extends TantouException {
    public static final String PRICE_WRONG_FORMAT_EXCEPTION = "Umm... \n" +
            "Perhaps try putting just a non-negative DOUBLE value after the '" +
            PRICE_OPTION + "' flag.";
    public PriceWrongFormatException() {
        super(PRICE_WRONG_FORMAT_EXCEPTION);
    }
}
