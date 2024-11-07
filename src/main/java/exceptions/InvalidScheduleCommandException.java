package exceptions;

public class InvalidScheduleCommandException extends TantouException {
    public static final String INVALID_DATE_PROVIDED_MESSAGE = "Invalid schedule command!";
    public InvalidScheduleCommandException() {
        super(INVALID_DATE_PROVIDED_MESSAGE);
    }
}
