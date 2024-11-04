package commands;

import author.AuthorList;
import ui.Ui;

import static constants.Command.BYE_COMMAND;
import static storage.StorageHelper.saveFile;

//@@author averageandyyy
/**
 * Represents a command to terminate {@code Tantou}.
 *
 * <p>The {@code ByeCommand} class extends the {@link Command} class and defines
 * the behavior for gracefully exiting the application. It invokes a farewell message
 * and saves the current state of the application before exiting.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(BYE_COMMAND);
    }

    /**
     * Executes the command to terminate {@code Tantou}.
     *
     * <p>This method triggers a farewell message through the user interface and sets the
     * exit state to true, indicating that the application should close. It also saves the
     * current state of the author list before exiting.
     *
     * @param ui The user interface through which the command interacts with the user.
     * @param authorList The list of authors whose state will be saved upon exit.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) {
        ui.sayGoodbye();
        setExit(true);
        saveFile(authorList);
    }
}
