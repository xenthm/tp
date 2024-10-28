package exceptions;

import static constants.Options.MAX_AUTHOR_NAME_LENGTH;

public class AuthorNameTooLongException extends TantouException {
    private static final String AUTHOR_NAME_TOO_LONG_MESSAGE =
            "Whoa! That's a long name! Please keep it to " + MAX_AUTHOR_NAME_LENGTH + " characters and below.";

    public AuthorNameTooLongException() {
        super(AUTHOR_NAME_TOO_LONG_MESSAGE);
    }
}
