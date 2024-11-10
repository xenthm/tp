package exceptions;

import static constants.Options.QUANTITY_OPTION;

public class QuantityWrongFormatException extends TantouException {
    public static final String QUANTITY_WRONG_FORMAT_EXCEPTION = "Umm... \n" +
            "Perhaps try putting just an INTEGER value after the '" +
            QUANTITY_OPTION + "' flag.";
    public QuantityWrongFormatException() {
        super(QUANTITY_WRONG_FORMAT_EXCEPTION);
    }
}
