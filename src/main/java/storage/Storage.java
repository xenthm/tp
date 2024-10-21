package storage;

import author.Author;
import author.AuthorList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Logger;

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
        dataFile = new File(DATA_PATH);
        logger.info("Data file path initialized to: " + dataFile.getAbsolutePath());
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(Author.class, new AuthorDeserializer())
                .create();
    }

    public Gson getGson() {
        return gson;
    }

    public File getDataFile() {
        return dataFile;
    }

    public void setDataFile(File dataFile) {
        Storage.dataFile = dataFile;
        logger.info("Data file path changed to: " + dataFile.getAbsolutePath());
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
            logger.info("Singleton Storage first initialized");
        }
        return storage;
    }

    public AuthorList readAuthorListFromDataFile() {
        assert dataFile != null : "dataFile cannot be null";
        try (FileReader reader = new FileReader(dataFile)) {
            Type authorListType = (new TypeToken<AuthorList>() {}).getType();
            AuthorList authorList = gson.fromJson(reader, authorListType);
            logger.info("Data restored");
            return authorList;
        } catch (IOException e) {
            logger.warning("Problems accessing file, data was not restored!" + e.getMessage());
        } catch (JsonParseException e) {
            logger.warning("Problems parsing JSON from file, data was not restored!" + e.getMessage());
        }
        return null;
    }

    /**
     * Checks if the data storage file <code>dataFile</code> can be found at its specified location. If not, creates
     * the corresponding directories and file, logging the process.
     */
    private void createFileIfNeeded() {
        assert dataFile != null : "dataFile cannot be null";
        try {
            // check with short-circuiting if path has a parent directory,
            // then if the directories were created with mkdirs()
            if (dataFile.getParentFile() != null && dataFile.getParentFile().mkdirs()) {
                logger.info("Directories not found; created them");
            }

            // check if data file was created
            if (dataFile.createNewFile()) {
                logger.info("Data file not found; created it");
            }
        } catch (IOException | SecurityException e) {
            logger.warning("Problems creating data file, data will not be saved!" + e.getMessage());
        }
    }

    public void saveAuthorListToDataFile(AuthorList authorList) {
        assert authorList != null : "authorList cannot be null";
        createFileIfNeeded();
        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(authorList, writer);
            logger.info("Data saved");
        } catch (IOException e) {
            logger.warning("Problems saving file, data will not be saved!" + e.getMessage());
        }
    }
}
