package exceptions;

public class NoDeadlineProvidedException extends TantouException {
    public static final String NO_DEADLINE_PROVIDED_MESSAGE = "You have not provided a deadline argument!";
    public NoDeadlineProvidedException() {
        super(NO_DEADLINE_PROVIDED_MESSAGE);
    }
}
