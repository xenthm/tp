package exceptions;

public class MangaExistsException extends TantouException {
    public static final String MANGA_EXISTS_MESSAGE = "Manga exists!";

    public MangaExistsException() {
        super(MANGA_EXISTS_MESSAGE);
    }
}
