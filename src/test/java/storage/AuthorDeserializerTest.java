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
class AuthorDeserializerTest {
    private AuthorDeserializer authorDeserializer;

    @BeforeEach
    void setUp() {
        authorDeserializer = new AuthorDeserializer();
    }

    @Test
    void deserialize_nullAuthorObject_throwsJsonParseException() {
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> authorDeserializer.deserialize(null, Author.class, null)
        );
        assertEquals("corrupt Author object", exception.getMessage());
    }

    @Test
    void deserialize_nonJsonObject_throwsJsonParseException() {
        JsonElement jsonElement = JsonParser.parseString("\"Not an object\"");
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> authorDeserializer.deserialize(jsonElement, Author.class, null)
        );
        assertEquals("corrupt Author object", exception.getMessage());
    }

    @Test
    void deserialize_missingAuthorName_throwsJsonParseException() {
        JsonObject authorJson = new JsonObject();
        authorJson.add("mangaList", new JsonObject());  // isolate authorName
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> authorDeserializer.deserialize(authorJson, Author.class, null)
        );
        assertEquals("corrupt author name", exception.getMessage());
    }

    @Test
    void deserialize_invalidAuthorNameTypeInt_throwsJsonParseException() {
        JsonObject authorJson = new JsonObject();
        authorJson.addProperty("authorName", 1);  // invalid type, expect String
        authorJson.add("mangaList", new JsonObject());    // isolate authorName
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> authorDeserializer.deserialize(authorJson, Author.class, null)
        );
        assertEquals("corrupt author name", exception.getMessage());
    }

    @Test
    void deserialize_validAuthorObject_returnsAuthor() {
        Author expectedAuthor = new Author("test");
        Manga mangaOne = new Manga("manga1", expectedAuthor, "deadline1");
        Manga mangaTwo = new Manga("manga2", expectedAuthor, "deadline2");
        expectedAuthor.addManga(mangaOne);
        expectedAuthor.addManga(mangaTwo);

        JsonObject authorJson = new JsonObject();
        authorJson.addProperty("authorName", "test");
        authorJson.add("mangaList", JsonParser.parseString("""
                    [
                       {
                         "mangaName": "manga1",
                         "deadline": "deadline1"
                       },
                       {
                         "mangaName": "manga2",
                         "deadline": "deadline2"
                       }
                    ]
                """
        ));
        Author actualAuthor = authorDeserializer.deserialize(authorJson, Author.class, null);

        assertNotNull(actualAuthor);
        assertEquals(expectedAuthor.getAuthorName(), actualAuthor.getAuthorName());
        assertNotNull(actualAuthor.getMangaList());
        assertEquals(2, actualAuthor.getMangaList().size());
        assertEquals("manga1", actualAuthor.getMangaList().get(0).getMangaName());
        assertEquals("deadline1", actualAuthor.getMangaList().get(0).getDeadline());
        assertEquals("manga2", actualAuthor.getMangaList().get(1).getMangaName());
        assertEquals("deadline2", actualAuthor.getMangaList().get(1).getDeadline());
    }
}