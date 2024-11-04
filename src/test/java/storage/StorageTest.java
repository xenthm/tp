package storage;

import author.Author;
import author.AuthorList;
import manga.Manga;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import sales.Sale;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

//@@author xenthm
class StorageTest {
    private static final String STORAGE_TEST_DATA_FILE_NAME = "storageTestCatalog.json";
    private static URI storageTestDataFile;
    private static final Storage STORAGE_TEST_INSTANCE = Storage.getInstance();
    private static final AuthorList AUTHOR_LIST_TEST = new AuthorList();

    private static void setupAuthorListTest() {
        Author author1 = new Author("author1");
        author1.addManga(new Manga("manga1", author1, "deadline1"));
        author1.addManga(new Manga("manga2", author1, "deadline2"));
        author1.getManga("manga1").addSalesData(new Sale(10, 5.0));
        Author author2 = new Author("author2");
        author2.addManga(new Manga("manga3", author2, "deadline3"));
        author2.addManga(new Manga("manga4", author2, "deadline4"));
        author2.getManga("manga3").addSalesData(new Sale(314, 15.92));
        Author author3 = new Author("author3");
        author3.addManga(new Manga("manga5", author3, "deadline5"));
        author3.addManga(new Manga("manga6", author3, "deadline6"));
        author3.getManga("manga6").addSalesData(new Sale(10000, 2.5));

        AUTHOR_LIST_TEST.add(author1);
        AUTHOR_LIST_TEST.add(author2);
        AUTHOR_LIST_TEST.add(author3);
    }

    @BeforeAll
    public static void setup() throws URISyntaxException {
        storageTestDataFile = Objects.requireNonNull(
                StorageTest.class
                        .getClassLoader()
                        .getResource(STORAGE_TEST_DATA_FILE_NAME)
        ).toURI();
        STORAGE_TEST_INSTANCE.setDataFile(Paths.get(storageTestDataFile).toFile());
        setupAuthorListTest();
    }

    @Test
    void getInstance_returnsSameSingletonInstanceAcrossMultipleCalls() {
        Storage storage1 = Storage.getInstance();
        Storage storage2 = Storage.getInstance();
        assertSame(storage1, storage2);
    }

    @Test
    void readAuthorListFromDataFile_returnsNullWhenDataFileIsInvalid() {
        File temp = STORAGE_TEST_INSTANCE.getDataFile();
        STORAGE_TEST_INSTANCE.setDataFile(new File("invalid"));
        assertNull(STORAGE_TEST_INSTANCE.readAuthorListFromDataFile());
        STORAGE_TEST_INSTANCE.setDataFile(temp);
    }

    @Test
    void readAuthorListFromDataFile_matchesTestCase() {
        assertEquals(AUTHOR_LIST_TEST, STORAGE_TEST_INSTANCE.readAuthorListFromDataFile());
    }

    /**
     * Tests <code>saveAuthorListToDataFile()</code> independently of the private <code>createFileIfNeeded()</code>
     * method, by only testing <code>toJson()</code> to check if the correct fields are being serialized.
     */
    @Test
    void toJson_serializesCorrectly() {
        StringWriter stringWriter = new StringWriter();
        STORAGE_TEST_INSTANCE
                .getGson()
                .toJson(AUTHOR_LIST_TEST, stringWriter);
        String actualJson = stringWriter.toString()
                .replaceAll("\\r\\n", "\n");
        try {
            String expectedJson = new String(Files.readAllBytes(Paths.get(storageTestDataFile)))
                    .replaceAll("\\r\\n", "\n");
            assertEquals(expectedJson, actualJson);
        } catch (IOException e) {
            fail("Error reading test data file");
        }
    }
}
