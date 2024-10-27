package exceptions;

public class SalesArgsWrongOrderException extends RuntimeException {
    public static final String SALES_ARGUMENTS_WRONG_ORDER_EXCEPTION = "Order of commands incorrect! " +
            "Try \"sales -a [AUTHOR_NAME] -m [MANGA_NAME] -q [QUANTITY_SOLD] -p [PRICE_PER_COPY] instead!\"";

    public SalesArgsWrongOrderException() {
        super(SALES_ARGUMENTS_WRONG_ORDER_EXCEPTION);
    }
}
