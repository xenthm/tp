package exceptions;

public class AuthorExistsException extends TantouException {
    public static final String AUTHOR_EXISTS_MESSAGE = "Author exists!";

    public AuthorExistsException() {
        super(AUTHOR_EXISTS_MESSAGE);
    }
}
