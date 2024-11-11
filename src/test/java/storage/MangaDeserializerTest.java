package storage;

import author.Author;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import manga.Manga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xenthm
class MangaDeserializerTest {
    private final Author testAuthor = new Author("test");

    private MangaDeserializer mangaDeserializer;

    @BeforeEach
    void setUp() {
        mangaDeserializer = new MangaDeserializer(testAuthor, testAuthor.getMangaList());
    }

    @Test
    void deserialize_nullMangaObject_throwsJsonParseException() {
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaDeserializer.deserialize(null, Manga.class, null)
        );
        assertEquals("redundant comma or invalid Manga object", exception.getMessage());
    }

    @Test
    void deserialize_nonJsonObject_throwsJsonParseException() {
        JsonElement jsonElement = JsonParser.parseString("\"Not an object\"");
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaDeserializer.deserialize(jsonElement, Manga.class, null)
        );
        assertEquals("redundant comma or invalid Manga object", exception.getMessage());
    }

    @Test
    void deserialize_missingMangaName_throwsJsonParseException() {
        JsonObject mangaJson = new JsonObject();
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaDeserializer.deserialize(mangaJson, Manga.class, null)
        );
        assertEquals("invalid manga name", exception.getMessage());
    }

    @Test
    void deserialize_invalidMangaNameTypeInt_throwsJsonParseException() {
        JsonObject authorJson = new JsonObject();
        authorJson.addProperty("mangaName", 1);  // invalid type, expect String
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaDeserializer.deserialize(authorJson, Manga.class, null)
        );
        assertEquals("invalid manga name", exception.getMessage());
    }

    @Test
    void deserialize_missingDeadline_throwsJsonParseException() {
        JsonObject mangaJson = new JsonObject();
        mangaJson.addProperty("mangaName", "valid manga name"); // isolate deadline
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaDeserializer.deserialize(mangaJson, Manga.class, null)
        );
        assertEquals("invalid deadline", exception.getMessage());
    }

    @Test
    void deserialize_invalidDeadlineTypeInt_throwsJsonParseException() {
        JsonObject mangaJson = new JsonObject();
        mangaJson.addProperty("mangaName", "valid manga name"); // isolate deadline
        mangaJson.addProperty("deadline", 1);  // invalid type, expect String
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaDeserializer.deserialize(mangaJson, Manga.class, null)
        );
        assertEquals("invalid deadline", exception.getMessage());
    }

    @Test
    void deserialize_validMangaObject_returnsManga() {
        Author expectedAuthor = new Author("test");
        Manga expectedManga = new Manga("manga1", expectedAuthor, "deadline1");

        JsonObject mangaJson = new JsonObject();
        mangaJson.addProperty("mangaName", "manga1");
        mangaJson.addProperty("deadline", "deadline1");
        Manga actualManga = mangaDeserializer.deserialize(mangaJson, Manga.class, null);

        assertNotNull(actualManga);
        assertEquals(expectedManga.getMangaName(), actualManga.getMangaName());
        assertEquals(expectedManga.getDeadline(), actualManga.getDeadline());
    }
}
