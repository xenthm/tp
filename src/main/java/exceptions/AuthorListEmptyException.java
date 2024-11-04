package exceptions;

public class AuthorListEmptyException extends TantouException {
    private static final String AUTHOR_LIST_EMPTY_MESSAGE =
            "You have no authors under you! Maybe you are the one slacking...";

    public AuthorListEmptyException() {
        super(AUTHOR_LIST_EMPTY_MESSAGE);
    }
}
