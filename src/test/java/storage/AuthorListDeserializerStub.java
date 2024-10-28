package storage;

import author.Author;
import author.AuthorList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This class is a stub of <code>AuthorListDeserializer</code> for testing.
 */
public class AuthorListDeserializerStub extends AuthorListDeserializer {
    @Override
    public AuthorList deserialize(JsonElement json, Type typeOfAuthorList, JsonDeserializationContext context)
            throws JsonParseException {
        // Ensure authorList is a JSON array
        if (json == null || !json.isJsonArray()) {
            throw new JsonParseException("corrupt AuthorList object");
        }

        AuthorList authorList = new AuthorList();
        authorList.add(new Author("test"));

        return authorList;
    }
}

