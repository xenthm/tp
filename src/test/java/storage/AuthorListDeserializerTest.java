package storage;

import author.Author;
import author.AuthorList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xenthm
class AuthorListDeserializerTest {
    private static final Type AUTHOR_LIST_TYPE = new TypeToken<AuthorList>() {}.getType();

    private AuthorListDeserializer authorListDeserializer;

    @BeforeEach
    void setUp() {
        authorListDeserializer = new AuthorListDeserializer();
    }

    @Test
    void deserialize_nullAuthorListObject_throwsJsonParseException() {
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> authorListDeserializer.deserialize(null, AUTHOR_LIST_TYPE, null)
        );
        assertEquals("invalid AuthorList array", exception.getMessage());
    }

    @Test
    void deserialize_nonJsonArray_throwsJsonParseException() {
        JsonElement jsonElement = JsonParser.parseString("\"Not an array\"");
        JsonParseException exception = assertThrows(
                JsonParseException.class,
                () -> authorListDeserializer.deserialize(jsonElement, AUTHOR_LIST_TYPE, null)
        );
        assertEquals("invalid AuthorList array", exception.getMessage());
    }

    @Test
    void deserialize_validAuthorListObject_returnsAuthorList() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AUTHOR_LIST_TYPE, new AuthorListDeserializerStub())
                .create();;


        AuthorList expectedAuthorList = new AuthorList();
        Author expectedAuthor = new Author("test");
        expectedAuthorList.add(expectedAuthor);

        JsonArray authorListJson = new JsonArray();
        authorListJson.add(JsonParser.parseString("""
                       {
                         "authorName": "test",
                         "mangaList": []
                       }
                """
        ));
        AuthorList actualAuthorList = gson.fromJson(authorListJson, AUTHOR_LIST_TYPE);

        assertNotNull(actualAuthorList);
        assertEquals(1, actualAuthorList.size());
        assertEquals(expectedAuthorList.get(0).getAuthorName(), actualAuthorList.get(0).getAuthorName());
        assertEquals(expectedAuthorList.get(0).getMangaList(), actualAuthorList.get(0).getMangaList());
    }
}
