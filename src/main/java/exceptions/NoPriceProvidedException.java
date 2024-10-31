package exceptions;

public class NoPriceProvidedException extends TantouException {
    public static final String NO_PRICE_PROVIDED_MESSAGE = "You have not provided a price argument!";
    public NoPriceProvidedException() {
        super(NO_PRICE_PROVIDED_MESSAGE);
    }
}
