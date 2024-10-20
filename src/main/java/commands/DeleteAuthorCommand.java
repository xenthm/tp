package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import storage.Storage;
import ui.Ui;

import java.util.logging.Level;

import static constants.Command.DELETE_COMMAND;

public class DeleteAuthorCommand extends Command {
    // private static final Logger logger = Logger.getLogger(DeleteAuthorCommand.class.getName());
    private String userInput;

    public DeleteAuthorCommand(String userInput) {
        super(DELETE_COMMAND);
        this.userInput = userInput;
    }

    /**
     * Conducts several checks on the userInput and authorList before deleting the author from the list.
     * If any of the checks fail, a TantouException is thrown.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        String authorName = parser.getAuthorNameFromInput(userInput);

        if (authorName.isEmpty()) {
            logger.log(Level.SEVERE, "Author name cannot be empty");
            throw new TantouException("No author provided!");
        }
        assert !authorName.isEmpty(): "Author name is empty";
        logger.log(Level.INFO, "Deleting author... " + authorName);

        Author deletingAuthor = new Author(authorName);

        if (authorList.hasAuthor(deletingAuthor)) {
            authorList.remove(deletingAuthor);
            System.out.println("Bye bye~");
            System.out.printf("Successfully deleted author: %s\n", deletingAuthor.getAuthorName());
            logger.log(Level.INFO, "Successfully deleted author: " + deletingAuthor.getAuthorName());

            Storage.getInstance().saveAuthorListToDataFile(authorList);
            return;
        }
        assert !authorList.hasAuthor(deletingAuthor): "Author not found";
        logger.log(Level.SEVERE, "Author not found");

        throw new TantouException("Author does not exist!");
    }

}
