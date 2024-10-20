package storage;

import author.Author;
import author.AuthorList;

import manga.Manga;

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

//@@author xenthm
// singleton
public class Storage {
    public static final String DATA_PATH = "data/catalog.json";

    private static Logger logger;
    private static Storage storage = null;
    private static File dataFile;
    private static Gson gson;

    private Storage() {
        assert DATA_PATH.endsWith(".json") : "data file path should be of type .json";
        logger = Logger.getLogger(this.getClass().getName());
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(Author.class, new AuthorDeserializer())
                .create();
        dataFile = new File(DATA_PATH);
        setupDataFile();
    }

    private void setupDataFile() {
        try {
            // check with short-circuiting if path has a parent directory,
            // then if the directories were created with mkdirs()
            if (dataFile.getParentFile() != null && dataFile.getParentFile().mkdirs()) {
                logger.info("Directories for data file created");
            }

            // check if data file was created
            if (dataFile.createNewFile()) {
                logger.info("Data file created");
            }
        } catch (IOException | SecurityException e) {
            // TODO: need to handle app operation when data cannot be saved!
            logger.warning("Problems setting up data file, data will not be saved!" + e.getMessage());
        }
    }

    public Gson getGson() {
        return gson;
    }

    public File getDataFile() {
        return dataFile;
    }

    public void setDataFile(File dataFile) {
        Storage.dataFile = dataFile;
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public AuthorList readAuthorListFromDataFile() {
        assert dataFile != null : "dataFile cannot be null";
        try (FileReader reader = new FileReader(dataFile)) {
            Type authorListType = (new TypeToken<AuthorList>() {}).getType();
            return gson.fromJson(reader, authorListType);
        } catch (IOException e) {
            logger.warning("Problems reading file, data was not restored!" + e.getMessage());
        }
        return null;
    }

    public void saveAuthorListToDataFile(AuthorList authorList) {
        assert authorList != null : "authorList cannot be null";
        try (FileWriter writer = new FileWriter(dataFile)) {
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
