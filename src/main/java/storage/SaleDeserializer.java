package storage;

import author.Author;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import sales.Sale;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This package private class defines a custom <code>Gson</code> deserializer for the <code>Sale</code> class. It
 * provides informative errors messages as feedback for users who manually edit the data file, and allows for skipping
 * of corrupted <code>Sale</code> elements while allowing valid ones to still be restored.
 */
class SaleDeserializer implements JsonDeserializer<Sale> {
    private final Author author;
    private final String mangaName;

    public SaleDeserializer(Author author, String mangaName) {
        this.author = author;
        this.mangaName = mangaName;
    }

    private void printErrorMessage(String message) {
        System.out.println("Author \""
                + author.getAuthorName()
                + "\": "
                + message
                + " for manga \""
                + mangaName
                + "\""
        );
    }

    @Override
    public Sale deserialize(JsonElement json, Type typeOfSale, JsonDeserializationContext context) {
        if (json == null || !json.isJsonObject()) {
            printErrorMessage("corrupted Sales object");
            return null;
        }
        JsonObject saleJsonObject = json.getAsJsonObject();

        Integer quantitySold = null;
        // Ensure quantitySold is valid
        if (saleJsonObject.has("quantitySold")) {
            try {
                if (!saleJsonObject.get("quantitySold").isJsonPrimitive()
                        || !saleJsonObject.get("quantitySold").getAsJsonPrimitive().isNumber()) {
                    throw new JsonParseException("quantitySold is not a number");
                }

                quantitySold = saleJsonObject.get("quantitySold").getAsInt();
            } catch (JsonParseException | NumberFormatException e) {
                printErrorMessage("corrupted quantity sold");
            }
        }

        Double unitPrice = null;
        // Ensure unitPrice is valid
        if (saleJsonObject.has("unitPrice")) {
            try {
                if (!saleJsonObject.get("unitPrice").isJsonPrimitive()
                        || !saleJsonObject.get("unitPrice").getAsJsonPrimitive().isNumber()) {
                    throw new JsonParseException("unitPrice is not a number");
                }

                unitPrice = saleJsonObject.get("unitPrice").getAsDouble();
            } catch (JsonParseException | NumberFormatException e) {
                printErrorMessage("corrupted unit price");
            }
        }

        // Assertion: salesData is valid
        return new Sale(
                quantitySold,
                unitPrice
        );
    }
}
