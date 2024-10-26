package exceptions;

public class InvalidDeleteCommandException extends TantouException {
    public static final String INVALID_DELETE_COMMAND_MESSAGE = "Invalid delete command format!\n" +
            "Don't forget your arguments like \"-a Kubo Tite\" " +
            "before placing \"-d\" at the end of the command!";
    public InvalidDeleteCommandException() {
        super(INVALID_DELETE_COMMAND_MESSAGE);
    }
}
