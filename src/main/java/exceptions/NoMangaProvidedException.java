package exceptions;

public class NoMangaProvidedException extends TantouException {
    public static final String NO_MANGA_PROVIDED_MESSAGE = "You have not provided a manga argument!";
    public NoMangaProvidedException() {
        super(NO_MANGA_PROVIDED_MESSAGE);
    }
}
