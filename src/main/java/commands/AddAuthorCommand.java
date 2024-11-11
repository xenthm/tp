package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorNameTooLongException;
import exceptions.NoAuthorProvidedException;
import exceptions.TantouException;
import ui.Ui;

import static storage.StorageHelper.saveFile;
import static constants.Command.CATALOG_COMMAND;

//@@author averageandyyy
/**
 * Represents a command to add a new author to the author's list.
 *
 * <p>The {@code AddAuthorCommand} class extends the {@link Command} class and
 * defines the behavior for adding an author. It checks for validity of the author name and ensures that no duplicate
 * authors are added to the list. If successful, it updates the author list and logs the action.
 */
public class AddAuthorCommand extends Command {
    private String authorName;

    public AddAuthorCommand(String authorName) {
        super(CATALOG_COMMAND);
        this.authorName = authorName;
    }

    /**
     * Executes the command to add the author to the specified author list.
     *
     * <p>This method validates the author name, checks if it exceeds the maximum
     * allowed length, and verifies that the author does not already exist in the
     * list. If all checks pass, the author is added, and the action is logged.
     * If the author already exists, a {@link TantouException} is thrown.
     *
     * @param ui The user interface through which the command interacts with the user.
     * @param authorList The list of authors to which the new author will be added.
     * @throws TantouException if an error occurs during command execution, such as
     *                         if the author name is missing, exceeds the maximum length,
     *                         or if the author already exists.
     * @throws NoAuthorProvidedException if the provided author name is null or empty.
     * @throws AuthorNameTooLongException if the provided author name exceeds the
     *                                     maximum length defined by {@code MAX_AUTHOR_NAME_LENGTH}.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        //@@author xenthm
        CommandValidator.ensureValidAuthorName(authorName);
        CommandValidator.ensureNoDuplicateAuthor(authorName, authorList);

        //@@author averageandyyy
        Author incomingAuthor = new Author(authorName);
        authorList.add(incomingAuthor);
        ui.printAddAuthorSuccessMessage(incomingAuthor);

        // Assert that the addition was successfully executed
        assert authorList.hasAuthor(incomingAuthor) : "Author is missing";
        assert authorList.getAuthor(incomingAuthor).getAuthorName()
                .equals(incomingAuthor.getAuthorName()) : "Author was not added";

        saveFile(authorList);
    }
}
