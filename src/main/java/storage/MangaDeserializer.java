package storage;

import author.Author;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import commands.CommandValidity;
import constants.Command;
import exceptions.TantouException;
import manga.Manga;
import sales.Sale;
import tantou.Tantou;

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
            throw new JsonParseException("redundant comma or invalid Manga object");
        }
        JsonObject mangaJsonObject = json.getAsJsonObject();

        // Ensure mangaName is valid
        String mangaName = "";
        if (!mangaJsonObject.has("mangaName")
                || !mangaJsonObject.get("mangaName").isJsonPrimitive()
                || !mangaJsonObject.get("mangaName").getAsJsonPrimitive().isString()) {
            throw new JsonParseException("invalid manga name");
        }
        mangaName = mangaJsonObject.get("mangaName").getAsString();
        try {
            CommandValidity.ensureValidMangaName(mangaName);
        } catch (TantouException e) {
            throw new JsonParseException("invalid manga name");
        }

        // Ensure deadline is valid
        String deadlineString = "";
        if (!mangaJsonObject.has("deadline")
                || !mangaJsonObject.get("deadline").isJsonPrimitive()
                || !mangaJsonObject.get("deadline").getAsJsonPrimitive().isString()) {
            throw new JsonParseException("invalid deadline");
        }
        deadlineString = mangaJsonObject.get("deadline").getAsString();
        try {
            CommandValidity.ensureValidDeadline(deadlineString);
        } catch (TantouException e) {
            throw new JsonParseException("invalid deadline");
        }


        // Initialise without sales data
        if (!mangaJsonObject.has("salesData")) {
            return new Manga(mangaName, author, deadlineString);
        }

        JsonElement salesJsonElement = mangaJsonObject.get("salesData");
        Sale salesData = new SaleDeserializer(author, mangaName)
                .deserialize(salesJsonElement, Sale.class, context);
        return new Manga(mangaName, author, deadlineString, salesData);
    }
}
