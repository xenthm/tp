package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import java.util.logging.Level;

import static constants.Command.DELETE_COMMAND;
import static storage.StorageHelper.saveFile;

//@@author sarahchow03
/**
 * Represents a command to delete an author from the authorList.
 * It checks for the existence of the author in the list before deletion.
 * If the author is not found, an exception is thrown.
 *
 * This class extends the Command class and overrides the execute method to handle the delete operation.
 */
public class DeleteAuthorCommand extends Command {
    private String authorName;

    /**
     * Constructs a DeleteAuthorCommand with the given user input.
     *
     * @param authorName The name of the author that is to be deleted.
     */
    public DeleteAuthorCommand(String authorName) {
        super(DELETE_COMMAND);
        this.authorName = authorName;
    }

    /**
     * Executes the delete command by conducting several checks on the user input and author list.
     * If the checks are successful, the specified author is deleted from the list.
     *
     * @param ui         The Ui object that handles user interface interactions.
     * @param authorList The list of authors from which the author will be deleted from.
     * @throws TantouException If the author does not exist or if the input is invalid.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        CommandValidity.checkAuthorName(authorName);

        COMMAND_LOGGER.log(Level.INFO, "Deleting author... " + authorName);

        Author deletingAuthor = new Author(authorName);
        CommandValidity.checkIfAuthorDoesNotExist(deletingAuthor.getAuthorName(), authorList);

        authorList.remove(deletingAuthor);
        ui.printDeleteAuthorSuccessMessage(deletingAuthor);
        COMMAND_LOGGER.log(Level.INFO, "Successfully deleted author: " + deletingAuthor.getAuthorName());
        saveFile(authorList);
    }

}
