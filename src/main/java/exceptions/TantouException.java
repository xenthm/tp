package exceptions;

public class TantouException extends Exception {
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong!";

    public TantouException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public TantouException(String message) {
        super(message);
    }
}
