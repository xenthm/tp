package storage;

import author.Author;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import manga.Manga;
import manga.MangaList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@@author xenthm
class MangaListDeserializerTest {
    private final Author testAuthor = new Author("test");
    private static final Type MANGA_LIST_TYPE = new TypeToken<MangaList>() {}.getType();

    private MangaListDeserializer mangaListDeserializer;

    @BeforeEach
    void setUp() {
        mangaListDeserializer = new MangaListDeserializer(testAuthor);
    }

    @Test
    void deserialize_nullMangaListObject_throwsJsonParseException() {
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaListDeserializer.deserialize(null, MANGA_LIST_TYPE, null)
        );
        assertEquals("corrupt MangaList object", exception.getMessage());
    }

    @Test
    void deserialize_nonJsonArray_throwsJsonParseException() {
        JsonElement jsonElement = JsonParser.parseString("\"Not an array\"");
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> mangaListDeserializer.deserialize(jsonElement, MANGA_LIST_TYPE, null)
        );
        assertEquals("corrupt MangaList object", exception.getMessage());
    }

    @Test
    void deserialize_validMangaListObject_returnsMangaList() {
        Author expectedAuthor = new Author("test");
        Manga mangaOne = new Manga("manga1", expectedAuthor, "deadline1");
        Manga mangaTwo = new Manga("manga2", expectedAuthor, "deadline2");
        MangaList expectedMangaList = new MangaList();
        expectedMangaList.add(mangaOne);
        expectedMangaList.add(mangaTwo);

        JsonArray mangaListJson = new JsonArray();
        mangaListJson.add(JsonParser.parseString("""
                       {
                         "mangaName": "manga1",
                         "deadline": "deadline1"
                       }
                """
        ));
        mangaListJson.add(JsonParser.parseString("""
                       {
                         "mangaName": "manga2",
                         "deadline": "deadline2"
                       }
                """
        ));
        MangaList actualMangaList = mangaListDeserializer.deserialize(mangaListJson, MANGA_LIST_TYPE, null);

        assertNotNull(actualMangaList);
        assertEquals(2, actualMangaList.size());
        assertEquals("manga1", actualMangaList.get(0).getMangaName());
        assertEquals("deadline1", actualMangaList.get(0).getDeadline());
        assertEquals("manga2", actualMangaList.get(1).getMangaName());
        assertEquals("deadline2", actualMangaList.get(1).getDeadline());
    }
}