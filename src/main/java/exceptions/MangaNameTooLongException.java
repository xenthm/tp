package exceptions;

import static constants.Options.MAX_MANGA_NAME_LENGTH;

public class MangaNameTooLongException extends TantouException {
    private static final String MANGA_NAME_TOO_LONG_MESSAGE = "Whoa! That's a long title! Unless you're an obscure " +
            "isekai writer, please keep it to " + MAX_MANGA_NAME_LENGTH + " characters and below.";

    public MangaNameTooLongException() {
        super(MANGA_NAME_TOO_LONG_MESSAGE);
    }
}
