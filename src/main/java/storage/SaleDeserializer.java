package storage;

import author.Author;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import commands.CommandValidator;
import exceptions.TantouException;
import sales.Sale;
import ui.Ui;

import java.lang.reflect.Type;

//@@author xenthm
/**
 * This package private class defines a custom <code>Gson</code> deserializer for the <code>Sale</code> class. It
 * provides informative errors messages as feedback for users who manually edit the data file, and allows for skipping
 * of invalid <code>Sale</code> elements while allowing valid ones to still be restored.
 */
class SaleDeserializer implements JsonDeserializer<Sale> {
    private final Author author;
    private final String mangaName;

    public SaleDeserializer(Author author, String mangaName) {
        this.author = author;
        this.mangaName = mangaName;
    }

    private String generateErrorMessage(String message) {
        return "Author \""
                + author.getAuthorName()
                + "\": "
                + message
                + " for manga \""
                + mangaName
                + "\", "
                + "continuing with an empty field";
    }

    @Override
    public Sale deserialize(JsonElement json, Type typeOfSale, JsonDeserializationContext context) {
        if (json == null || !json.isJsonObject()) {
            Ui.printString(generateErrorMessage("invalid Sales object"));
            return new Sale();
        }
        JsonObject saleJsonObject = json.getAsJsonObject();

        Integer quantitySold = null;
        // Ensure quantitySold is valid
        if (!saleJsonObject.has("quantitySold")
                || !saleJsonObject.get("quantitySold").isJsonPrimitive()
                || !saleJsonObject.get("quantitySold").getAsJsonPrimitive().isNumber()) {
            Ui.printString(generateErrorMessage("invalid quantity sold"));
        } else {
            String quantitySoldString = saleJsonObject.get("quantitySold").getAsString();
            try {
                CommandValidator.ensureValidQuantitySold(quantitySoldString);
                quantitySold = Integer.parseInt(quantitySoldString);
            } catch (TantouException e) {
                Ui.printString(generateErrorMessage("invalid quantity sold"));
            }
        }

        Double unitPrice = null;
        // Ensure unitPrice is valid
        if (!saleJsonObject.has("unitPrice")
                || !saleJsonObject.get("unitPrice").isJsonPrimitive()
                || !saleJsonObject.get("unitPrice").getAsJsonPrimitive().isNumber()) {
            Ui.printString(generateErrorMessage("invalid unit price"));
        } else {
            String unitPriceString = saleJsonObject.get("unitPrice").getAsString();
            try {
                CommandValidator.ensureValidUnitPrice(unitPriceString);
                unitPrice = Double.parseDouble(unitPriceString);
            } catch (TantouException e) {
                Ui.printString(generateErrorMessage("invalid unit price"));
            }
        }

        // Assertion: salesData is valid
        return new Sale(
                quantitySold,
                unitPrice
        );
    }
}
