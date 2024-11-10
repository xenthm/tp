package exceptions;

import static constants.Options.MAX_DEADLINE_LENGTH;

public class DeadlineTooLongException extends TantouException {
    private static final String DEADLINE_TOO_LONG_MESSAGE =
            "Wow. That's a lotta yapping for a deadline! Don't exceed " + MAX_DEADLINE_LENGTH + " characters please.";

    public DeadlineTooLongException() {
        super(DEADLINE_TOO_LONG_MESSAGE);
    }
}
