package exceptions;

import static constants.Options.AUTHOR_OPTION;
import static constants.Options.BY_DATE_OPTION;
import static constants.Options.SALES_OPTION;

public class InvalidViewCommandException extends TantouException {
    public InvalidViewCommandException(String token) {
        super(generateInvalidViewCommandMessage(token));
    }

    private static String generateInvalidViewCommandMessage(String token) {
        return "Invalid view command \"" + token + "\" provided! " +
                "Try \"view [" + AUTHOR_OPTION + " [AUTHOR_NAME] [" + BY_DATE_OPTION + "] ["
                + SALES_OPTION + "]]\" instead! " +
                "The arguments can be in any order, but you need to provide a valid author when including \""
                + BY_DATE_OPTION + "\" and \"" + SALES_OPTION + "\"!";
    }
}
