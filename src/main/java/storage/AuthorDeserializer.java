package storage;

import author.Author;
import manga.Manga;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class AuthorDeserializer implements JsonDeserializer<Author> {
    @Override
    public Author deserialize(JsonElement json, Type typeOfAuthor, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String authorName = jsonObject.get("authorName").getAsString();
        Author author = new Author(authorName);

        JsonArray mangaArray = jsonObject.getAsJsonArray("mangaList");
        for (JsonElement mangaElement : mangaArray) {
            Manga manga = context.deserialize(mangaElement, Manga.class);
            manga.setAuthor(author);
            author.addManga(manga);
        }
        return author;
    }
}
