package exceptions;

public class NumberLessThanZeroException extends TantouException {
    public static final String NUMBER_LESS_THAN_ZERO_MESSAGE =
            "Negative profits means you're getting fired by the way.";
    public NumberLessThanZeroException() {
        super(NUMBER_LESS_THAN_ZERO_MESSAGE);
    }
}
