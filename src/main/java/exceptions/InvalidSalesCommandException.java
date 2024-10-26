package exceptions;

//@@author sarahchow03
public class InvalidSalesCommandException extends TantouException {
    public static final String INVALID_SALES_COMMAND_MESSAGE = "Invalid sales command provided! " +
            "Try \"sales -a [AUTHOR_NAME] -m [MANGA_NAME] -q [QUANTITY_SOLD] -p [PRICE_PER_COPY] instead!\"";


    public InvalidSalesCommandException() {
        super(INVALID_SALES_COMMAND_MESSAGE);
    }
}
