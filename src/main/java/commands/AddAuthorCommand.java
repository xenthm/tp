package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import static constants.Command.ADD_COMMAND;

public class AddAuthorCommand extends Command {
    private String userInput;

    public AddAuthorCommand(String userInput) {
        super(ADD_COMMAND);
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        String authorName = parser.getAuthorNameFromInput(userInput);

        if (authorName.isEmpty()) {
            throw new TantouException("No author provided!");
        }

        Author incomingAuthor = new Author(authorName);

        if (!authorList.hasAuthor(incomingAuthor)) {
            authorList.addAuthor(incomingAuthor);
            System.out.printf("Successfully added author: %s\n", incomingAuthor.getAuthorName());
            return;
        }

        throw new TantouException("Author exists!");
    }
}
