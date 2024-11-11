package storage;

import author.Author;
import author.AuthorList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import commands.CommandValidator;
import exceptions.AuthorExistsException;
import exceptions.AuthorNameTooLongException;
import exceptions.NoAuthorProvidedException;
import manga.MangaList;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This package private class defines a custom <code>Gson</code> deserializer for the <code>Author</code> class. It
 * provides informative errors messages as feedback for users who manually edit the data file. The
 * <code>Author</code> reference is passed to the <code>MangaDeserializer</code> so that circular reference
 * (bidirectional navigability) between an <code>Author</code> and their <code>Manga</code> can be maintained.
 */
class AuthorDeserializer implements JsonDeserializer<Author> {
    private final AuthorList authorList;

    public AuthorDeserializer(AuthorList authorList) {
        this.authorList = authorList;
    }

    @Override
    public Author deserialize(JsonElement json, Type typeOfAuthor, JsonDeserializationContext context)
            throws JsonParseException {
        if (json == null || !json.isJsonObject()) {
            throw new JsonParseException("redundant comma or invalid Author object");
        }
        JsonObject authorJsonObject = json.getAsJsonObject();

        // Ensure authorName is valid
        String authorName = "";
        if (!authorJsonObject.has("authorName")
                || !authorJsonObject.get("authorName").isJsonPrimitive()
                || !authorJsonObject.get("authorName").getAsJsonPrimitive().isString()) {
            throw new JsonParseException("invalid author name");
        }
        authorName = authorJsonObject.get("authorName").getAsString();
        try {
            CommandValidator.ensureValidAuthorName(authorName);
            CommandValidator.ensureNoDuplicateAuthor(authorName, authorList);
        } catch (NoAuthorProvidedException | AuthorNameTooLongException e) {
            throw new JsonParseException("invalid author name");
        } catch (AuthorExistsException e) {
            throw new JsonParseException("duplicate author found");
        }
        Author author = new Author(authorName);

        // Ensure mangaList is valid
        try {
            // Pass Author reference
            MangaList mangaList = new MangaListDeserializer(author).deserialize(
                    authorJsonObject.get("mangaList"),
                    MangaList.class,
                    context
            );
            author.setMangaList(mangaList);
        } catch (JsonParseException e) {
            throw new JsonParseException("invalid manga list");
        }

        // Assertion: author is valid now
        return author;
    }
}
