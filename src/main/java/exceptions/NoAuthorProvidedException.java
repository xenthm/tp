package exceptions;

public class NoAuthorProvidedException extends TantouException {
    public static final String NO_AUTHOR_PROVIDED_MESSAGE = "You have not provided an author argument!";
    public NoAuthorProvidedException() {
        super(NO_AUTHOR_PROVIDED_MESSAGE);
    }
}
