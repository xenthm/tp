package exceptions;

public class MangaDoesNotExistException extends TantouException {
    public MangaDoesNotExistException(String mangaName) {
        super(generateMangaDoesNotExistMessage(mangaName));
    }

    private static String generateMangaDoesNotExistMessage(String mangaName) {
        return "Manga \"" + mangaName + "\" does not exist! "
                + "Manga needs to exist in our catalog first before adding their sales data!";
    }
}
