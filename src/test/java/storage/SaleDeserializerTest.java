package storage;

import author.Author;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import manga.Manga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sales.Sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@@author xenthm
class SaleDeserializerTest {
    private final Author testAuthor = new Author("testAuthor");
    private final Manga testManga = new Manga("testManga", testAuthor);

    private SaleDeserializer saleDeserializer;

    @BeforeEach
    void setUp() {
        saleDeserializer = new SaleDeserializer(testAuthor, testManga.getMangaName());
    }

    @Test
    void deserialize_nullSaleObject_returnsNullSaleObject() {
        assertEquals(new Sale(),
                saleDeserializer.deserialize(null, Sale.class, null)
        );
    }

    @Test
    void deserialize_nonJsonObject_returnsNullSaleObject() {
        JsonElement jsonElement = JsonParser.parseString("\"Not an object\"");
        assertEquals(new Sale(),
                saleDeserializer.deserialize(jsonElement, Sale.class, null)
        );
    }

    @Test
    void deserialize_missingQuantitySoldAndUnitPrice_returnsNullSaleObject() {
        JsonObject saleJson = new JsonObject();
        assertEquals(new Sale(),
                saleDeserializer.deserialize(saleJson, Sale.class, null)
        );
    }

    @Test
    void deserialize_invalidQuantitySoldTypeString_returnsNullSaleObject() {
        JsonObject saleJson = new JsonObject();
        saleJson.addProperty("quantitySold", "Not a number");  // invalid type, expect Integer
        assertEquals(new Sale(),
                saleDeserializer.deserialize(saleJson, Sale.class, null)
        );
    }

    @Test
    void deserialize_invalidQuantitySoldTypeDouble_returnsNullSaleObject() {
        JsonObject saleJson = new JsonObject();
        saleJson.addProperty("quantitySold", 3.14);  // invalid type, expect Integer
        assertEquals(new Sale(),
                saleDeserializer.deserialize(saleJson, Sale.class, null)
        );
    }

    @Test
    void deserialize_invalidUnitPriceTypeBoolean_returnsNullSaleObject() {
        JsonObject saleJson = new JsonObject();
        saleJson.addProperty("quantitySold", true);  // invalid type, expect Double
        assertEquals(new Sale(),
                saleDeserializer.deserialize(saleJson, Sale.class, null)
        );
    }

    @Test
    void deserialize_invalidQuantitySoldOnly_returnsPartialSaleObject() {
        JsonObject saleJson = new JsonObject();
        saleJson.addProperty("quantitySold", true);  // invalid type, expect Double
        saleJson.addProperty("unitPrice", 10);
        assertEquals(new Sale(null, 10.0),
                saleDeserializer.deserialize(saleJson, Sale.class, null)
        );
    }

    @Test
    void deserialize_invalidUnitPriceOnly_returnsPartialSaleObject() {
        JsonObject saleJson = new JsonObject();
        saleJson.addProperty("quantitySold", 12);  // invalid type, expect Double
        assertEquals(new Sale(12, null),
                saleDeserializer.deserialize(saleJson, Sale.class, null)
        );
    }

    @Test
    void deserialize_validSaleObject_returnsSale() {
        Sale expectedSale = new Sale(314, 15.92);

        JsonObject saleJson = new JsonObject();
        saleJson.addProperty("quantitySold", 314);
        saleJson.addProperty("unitPrice", 15.92);
        Sale actualSale = saleDeserializer.deserialize(saleJson, Sale.class, null);

        assertNotNull(actualSale);
        assertEquals(expectedSale, actualSale);
    }
}
