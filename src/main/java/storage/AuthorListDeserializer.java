package storage;

import author.Author;
import author.AuthorList;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ui.Ui;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This package private class defines a custom <code>Gson</code> deserializer for the top-level <code>AuthorList</code>
 * class. It provides informative errors messages as feedback for users who manually edit the data file, and allows
 * for skipping of corrupted <code>Author</code> elements while allowing valid ones to still be restored.
 */
class AuthorListDeserializer implements JsonDeserializer<AuthorList> {
    @Override
    public AuthorList deserialize(JsonElement json, Type typeOfAuthorList, JsonDeserializationContext context)
            throws JsonParseException {
        // Ensure authorList is a JSON array
        if (json == null || !json.isJsonArray()) {
            throw new JsonParseException("corrupt AuthorList array");
        }
        JsonArray authorListJsonArray = json.getAsJsonArray();

        AuthorList authorList = new AuthorList();
        for (int i = 0; i < authorListJsonArray.size(); i++) {
            JsonElement authorJsonElement = authorListJsonArray.get(i);
            // Ensure author is valid, skipping if not
            try {
                Author author = new AuthorDeserializer().deserialize(authorJsonElement, Author.class, context);
                authorList.add(author);
            } catch (JsonParseException e) {
                Ui.printString(generateErrorMessage(e, i));
            }
        }

        // Assertion: authorList is either empty, or contains only valid authors
        return authorList;
    }

    private String generateErrorMessage(JsonParseException e, int index) {
        return "Skipping corrupted author entry at index " + index + " due to "
                + e.getMessage();
    }
}
