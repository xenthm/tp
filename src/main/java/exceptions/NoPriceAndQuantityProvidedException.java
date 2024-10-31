package exceptions;

public class NoPriceAndQuantityProvidedException extends TantouException {
    public static final String NO_PRICE__AND_QUANTITY_PROVIDED_MESSAGE = "You have not provided the price and quantity!";
    public NoPriceAndQuantityProvidedException() {
        super(NO_PRICE__AND_QUANTITY_PROVIDED_MESSAGE);
    }
}
