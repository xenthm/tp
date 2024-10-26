package exceptions;

public class MangaArgsWrongOrderException extends TantouException {
    public static final String MANGA_ARGUMENTS_WRONG_ORDER_EXCEPTION = "Specify the author before the manga!";
    public MangaArgsWrongOrderException() {
        super(MANGA_ARGUMENTS_WRONG_ORDER_EXCEPTION);
    }
}
