package exceptions;

public class NumberLessThanZeroException extends TantouException {
    public static final String NUMBER_LESS_THAN_ZERO_MESSAGE =
            "Hey hey... didn't we say only NON-NEGATIVE VALUES are accepted?";
    public NumberLessThanZeroException() {
        super(NUMBER_LESS_THAN_ZERO_MESSAGE);
    }
}
