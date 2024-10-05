package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import static constants.Command.DELETE_COMMAND;

public class DeleteAuthorCommand extends Command {
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
        String authorName = parser.getAuthorName(userInput);

        if (authorName.isEmpty()) {
            throw new TantouException("No author provided!");
        }

        Author deletingAuthor = new Author(authorName);

        if (authorList.hasAuthor(deletingAuthor)) {
            authorList.deleteAuthor(deletingAuthor);
            System.out.print("Bye bye~");
            System.out.printf("Successfully deleted author: %s\n", deletingAuthor.getAuthorName());
            return;
        }

        throw new TantouException("Author does not exist!");
    }

}
