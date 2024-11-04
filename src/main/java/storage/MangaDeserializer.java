package storage;

import author.Author;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import manga.Manga;
import sales.Sale;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This package private class defines a custom <code>Gson</code> deserializer for the <code>Manga</code> class. It
 * contains a reference to its <code>Author</code>. It provides informative errors messages as feedback for users who
 * manually edit the data file.
 */
class MangaDeserializer implements JsonDeserializer<Manga> {
    private final Author author;

    public MangaDeserializer(Author author) {
        this.author = author;
    }

    @Override
    public Manga deserialize(JsonElement json, Type typeOfManga, JsonDeserializationContext context)
            throws JsonParseException {
        // Ensure Manga is a JSON Object
        if (json == null || !json.isJsonObject()) {
            throw new JsonParseException("corrupt Manga object");
        }
        JsonObject mangaJsonObject = json.getAsJsonObject();

        // Ensure mangaName is valid
        if (!mangaJsonObject.has("mangaName")
                || !mangaJsonObject.get("mangaName").isJsonPrimitive()
                || !mangaJsonObject.get("mangaName").getAsJsonPrimitive().isString()) {
            throw new JsonParseException("corrupt manga name");
        }
        String mangaName = mangaJsonObject.get("mangaName").getAsString();

        // Ensure deadline is valid
        if (!mangaJsonObject.has("deadline")
                || !mangaJsonObject.get("deadline").isJsonPrimitive()
                || !mangaJsonObject.get("deadline").getAsJsonPrimitive().isString()) {
            throw new JsonParseException("corrupt deadline");
        }
        String deadline = mangaJsonObject.get("deadline").getAsString();

        // Initialise without sales data
        if (!mangaJsonObject.has("salesData")) {
            return new Manga(mangaName, author, deadline);
        }

        JsonElement salesJsonElement = mangaJsonObject.get("salesData");
        Sale salesData = new SaleDeserializer(author, mangaName)
                .deserialize(salesJsonElement, Sale.class, context);
        return new Manga(mangaName, author, deadline, salesData);
    }
}
