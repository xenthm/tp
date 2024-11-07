package exceptions;

public class MangaListEmptyException extends TantouException {
    public MangaListEmptyException(String authorName) {
        super(generateInvalidViewCommandMessage(authorName));
    }

    private static String generateInvalidViewCommandMessage(String authorName) {
        return authorName + " has no mangas... You know what has to be done.";
    }
}
