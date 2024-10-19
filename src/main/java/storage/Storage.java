package storage;

import author.Author;
import author.AuthorList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import manga.Manga;

// singleton
public class Storage {
    public static final String DATA_PATH = "data/catalog.json";

    private static Logger logger;
    private static Storage storage = null;
    private static File dataFile;
    private static Gson gson;

    private Storage() {
        logger = Logger.getLogger(this.getClass().getName());
        dataFile = new File(DATA_PATH);
        checkDataFile();
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(Author.class, new AuthorDeserializer())
                .create();
    }

    // for singleton
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    private void checkDataFile() {
        try {
            if (dataFile.getParentFile() != null && dataFile.getParentFile().mkdirs()) {
                logger.info("Directories Created");
            }

            if (dataFile.createNewFile()) {
                logger.info("File Created");
            }
        } catch (IOException | SecurityException e) {
            // TODO: need to handle app operation when data cannot be saved!
            logger.warning("Problems with data file, data will not be saved!" + e.getMessage());
        }
    }

    public AuthorList readDataFile() {
        try (FileReader reader = new FileReader(dataFile)) {
            Type authorListType = new TypeToken<AuthorList>() {
            }.getType();
            return gson.fromJson(reader, authorListType);
        } catch (IOException e) {
            logger.warning("Problems reading file, data was not restored!" + e.getMessage());
        }
        return null;
    }

    public void saveDataFile(AuthorList authorList) {
        try (FileWriter writer = new FileWriter("data/catalog.json")) {
            gson.toJson(authorList, writer);
        } catch (IOException e) {
            logger.warning("Problems saving file, data will not be saved!" + e.getMessage());
        }
    }
}

class AuthorDeserializer implements JsonDeserializer<Author> {
    @Override
    public Author deserialize(JsonElement json, Type typeOfAuthor, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String authorName = jsonObject.get("authorName").getAsString();
        Author author = new Author(authorName);

        JsonArray mangaArray = jsonObject.getAsJsonArray("mangaList");
        for (JsonElement mangaElement : mangaArray) {
            Manga manga = context.deserialize(mangaElement, Manga.class);
            manga.setAuthor(author);
            author.addManga(manga);
        }
        return author;
    }
}
