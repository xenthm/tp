package storage;

import author.Author;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import manga.Manga;
import manga.MangaList;
import ui.Ui;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This package private class defines a custom <code>Gson</code> deserializer for the <code>MangaList</code> class. It
 * contains a reference to the <code>Author</code>. It provides informative errors messages as feedback for users who
 * manually edit the data file, and allows for skipping of invalid <code>Manga</code> elements while allowing valid
 * ones to still be restored. The <code>Author</code> reference is passed to the <code>MangaDeserializer</code> so that
 * circular reference (bidirectional navigability) between an <code>Author</code> and their <code>Manga</code> can be
 * maintained. The <code>MangaList</code> itself is also passed down to allow for duplicate manga checking.
 */
class MangaListDeserializer implements JsonDeserializer<MangaList> {
    private final Author author;

    public MangaListDeserializer(Author author) {
        this.author = author;
    }

    private String generateErrorMessage(Exception e, int index) {
        return "Author \""
                + author.getAuthorName()
                + "\": skipping invalid manga entry at index " + index + " due to "
                + e.getMessage();
    }

    @Override
    public MangaList deserialize(JsonElement json, Type typeOfMangaList, JsonDeserializationContext context)
            throws JsonParseException {
        // Ensure mangaList is a JSON array
        if (json == null || !json.isJsonArray()) {
            throw new JsonParseException("invalid MangaList array");
        }
        JsonArray mangaListJsonArray = json.getAsJsonArray();

        MangaList mangaList = new MangaList();
        for (int i = 0; i < mangaListJsonArray.size(); i++) {
            JsonElement mangaJsonElement = mangaListJsonArray.get(i);
            // Ensure manga is valid, skipping if not
            try {
                // pass Author reference
                Manga manga = new MangaDeserializer(author, mangaList)
                        .deserialize(mangaJsonElement, Manga.class, context);
                mangaList.add(manga);
            } catch (JsonParseException e) {
                Ui.printString(generateErrorMessage(e, i));
            }
        }

        // Assertion: mangaList is either empty, or contains only valid mangas
        return mangaList;
    }
}
