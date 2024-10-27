package exceptions;

public class InvalidCatalogCommandException extends TantouException {
    public static final String INVALID_CATALOG_COMMAND_MESSAGE = "Invalid catalog command provided! " +
            "Try \"catalog -a [AUTHOR_NAME] -m [MANGA_NAME]\" instead! " +
            "And if you want to delete, just add a \"-d\" at the end!";

    public InvalidCatalogCommandException() {
        super(INVALID_CATALOG_COMMAND_MESSAGE);
    }
}
