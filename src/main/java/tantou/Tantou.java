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

import static commands.Command.COMMAND_LOGGER;
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
                ui.printAccessLogFileFailureMessage();
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
            Ui.printResolveURIFailureMessage(e);
        }
        return Paths.get("").toAbsolutePath();  // Fallback to current directory
    }

    private static FileHandler getFileHandler() throws IOException {
        FileHandler fileHandler = new FileHandler(LOG_LOCATION.getPath(), true);
        fileHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                /* Change log message format based on if the command logger is being used.
                 * This allows the log message to contain the caller's (i.e. the command's) name, instead of the
                 * CommandValidator utility class.
                 */
                if (record.getLoggerName().equals(COMMAND_LOGGER.getName())) {
                    String callerClassName = "";
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();

                    String executeMethodName = "execute";
                    try {
                        Command.class.getMethod(executeMethodName, Ui.class, AuthorList.class);
                    } catch (NoSuchMethodException e) {
                        assert false : "Command class does not have an execute method that has Ui and AuthorList as " +
                                "parameters.";
                    }

                    for (StackTraceElement element : stackTrace) {
                        if (element.getMethodName().equals(executeMethodName)) {
                            callerClassName = element.getClassName();
                            break;
                        }
                    }
                    return String.format(
                            "%1$tF %1$tT %2$s [%3$s - %4$s] %n%5$s%n",
                            record.getMillis(),     // Timestamp
                            record.getLevel(),      // Log level
                            record.getLoggerName(), // Logger name
                            callerClassName,        // Caller's class name
                            record.getMessage()     // Log message
                    );
                }
                return String.format(
                        "%1$tF %1$tT %2$s [%3$s - %4$s::%5$s] %n%6$s%n",
                        record.getMillis(),                     // Timestamp
                        record.getLevel(),                      // Log level
                        record.getLoggerName(),                 // Logger name
                        record.getSourceClassName(),            // Class name
                        record.getSourceMethodName(),           // Method name
                        record.getMessage()                     // Log message
                );
            }
        });
        return fileHandler;
    }

    public void setAuthorList(AuthorList authorList) {
        this.authorList = authorList;
    }

    //@@author averageandyyy
    public void greetUser() throws TantouException {
        Command greetCommand = new GreetCommand();
        greetCommand.execute(ui, authorList);
    }

    //@@author xenthm
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
        try {
            greetUser();
        } catch (TantouException e) {
            ui.printErrorMessage(e);
        }

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command userCommand = parser.getUserCommand(userInput);
                userCommand.execute(ui, authorList);
                isExit = userCommand.isExit();
            } catch (TantouException e) {
                ui.printErrorMessage(e);
            }
        }
    }
}
