package commands;

import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import java.util.logging.Logger;

//@@author averageandyyy
/**
 * An abstract base class representing a command that can be executed by {@code Tantou}.
 *
 * <p>The {@code Command} class encapsulates the basic structure of a command,
 * including its string representation and a flag indicating whether the command
 * signifies an exit operation. Subclasses should implement the {@link #execute(Ui, AuthorList)}
 * method to define specific command behavior.
 */
public abstract class Command {
    public static final Logger COMMAND_LOGGER = Logger.getLogger("Tantou.Commands");
    private String command;
    private boolean isExit;


    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Executes the command using the provided user interface and author list.
     *
     * <p>Subclasses must implement this method to define the specific behavior of the command.
     *
     * @param ui The user interface through which the command will interact with the user.
     * @param authorList The list of authors that the command may operate on.
     * @throws TantouException if an error occurs during the command execution.
     */
    public abstract void execute(Ui ui, AuthorList authorList) throws TantouException;
}
