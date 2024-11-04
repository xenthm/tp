package exceptions;

public class NoQuantityProvidedException extends TantouException {
    public static final String NO_QUANTITY_PROVIDED_MESSAGE = "You have not provided a quantity argument!";
    public NoQuantityProvidedException() {
        super(NO_QUANTITY_PROVIDED_MESSAGE);
    }
}
