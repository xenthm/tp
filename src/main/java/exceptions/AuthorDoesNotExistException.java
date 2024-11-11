package exceptions;

public class AuthorDoesNotExistException extends TantouException {
    public AuthorDoesNotExistException(String authorName) {
        super(generateAuthorDoesNotExistMessage(authorName));
    }

    private static String generateAuthorDoesNotExistMessage(String authorName) {
        return "Author \"" + authorName + "\" does not exist! "
                + "Author needs to exist in our catalog first before performing this command!";
    }
}
