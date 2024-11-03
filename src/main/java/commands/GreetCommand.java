package commands;

import author.AuthorList;
import ui.Ui;

//@@author averageandyyy
/**
 * Represents a command to greet the user.
 *
 * <p>The {@code GreetCommand} class extends the {@link Command} class and defines
 * the behavior for greeting the user when the application starts or when requested.
 */
public class GreetCommand extends Command {
    public GreetCommand() {
        super("hi");
    }

    /**
     * Executes the command to greet the user.
     *
     * <p>This method invokes the user interface to display a greeting message
     * to the user.
     *
     * @param ui The user interface through which the command interacts with the user.
     * @param authorList The list of authors (not used in this command, but required by the method signature).
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) {
        ui.greetUser();
    }
}
