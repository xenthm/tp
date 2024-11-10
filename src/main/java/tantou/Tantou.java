package tantou;

import author.AuthorList;
import commands.Command;
import commands.GreetCommand;
import exceptions.TantouException;
import parser.Parser;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static storage.StorageHelper.readFile;

public class Tantou {
    public static final File BASE_LOCATION = getBaseDirectory().toFile();
    public static final File LOG_LOCATION = new File(BASE_LOCATION, "logs/tantou.log");

    private static final Logger TANTOU_LOGGER = Logger.getLogger("Tantou");

    private Ui ui;
    private Parser parser;
    private boolean isExit;
    private AuthorList authorList;

    //@@author averageandyyy
    public Tantou(boolean loggingEnabled) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.isExit = false;
        this.authorList = new AuthorList();

        //@@author xenthm
        // Remove default handlers
        LogManager.getLogManager().reset();

        if (loggingEnabled) {
            try {
                if (LOG_LOCATION.getParentFile() != null) {
                    LOG_LOCATION.getParentFile().mkdirs();
                    LOG_LOCATION.createNewFile();
                }

                FileHandler fileHandler = getFileHandler();
                TANTOU_LOGGER.addHandler(fileHandler);
            } catch (IOException e) {
                System.out.println("Problems accessing log file!");
            }
        }
    }

    //@@author xenthm
    /**
     * This method tries to get the directory the jar file is located for a more consistent data and log directory
     * location.
     *
     * @return The jar file directory, or if the program was not run from a jar file, an empty {@code Path}.
     */
    private static Path getBaseDirectory() {
        try {
            Path jarPath = Paths.get(Tantou.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if (jarPath.toString().endsWith(".jar")) {
                return jarPath.getParent();
            }
        } catch (URISyntaxException e) {
            System.out.println("Cannot resolve URI of jar file!" + e.getMessage());
            System.out.println("Continuing with the working directory set to be the path you ran the jar file in.");
        }
        return Paths.get("").toAbsolutePath();  // Fallback to current directory
    }

    private static FileHandler getFileHandler() throws IOException {
        FileHandler fileHandler = new FileHandler(LOG_LOCATION.getPath(), true);
        fileHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return String.format(
                        "%1$tF %1$tT %5$s [%2$s - %3$s::%4$s] %n%6$s%n",
                        record.getMillis(),               // Timestamp
                        record.getLoggerName(),           // Logger name
                        record.getSourceClassName(),      // Class name
                        record.getSourceMethodName(),     // Method name
                        record.getLevel(),                // Log level
                        record.getMessage()               // Log message
                );
            }
        });
        return fileHandler;
    }

    public void setAuthorList(AuthorList authorList) {
        this.authorList = authorList;
    }

    //@@author
    public void greetUser() {
        Command greetCommand = new GreetCommand();
        try {
            greetCommand.execute(ui, authorList);
        } catch (TantouException e) {
            System.out.printf("Something went wrong!: %s%n", e.getMessage());
        }
    }

    /**
     * Restores <code>authorList</code> from data file in the <code>Storage</code> singleton if available. If not, it
     * remains as a newly initialized one.
     */
    private void restoreDataIfAvailable() {
        AuthorList existingList = readFile();
        if (existingList != null) {
            setAuthorList(existingList);
        }
    }

    public void run() {
        restoreDataIfAvailable();

        //@@author averageandyyy
        greetUser();

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command userCommand = parser.getUserCommand(userInput);
                userCommand.execute(ui, authorList);
                isExit = userCommand.isExit();
            } catch (TantouException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
