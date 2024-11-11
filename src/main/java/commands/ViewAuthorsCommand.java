package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import static author.AuthorList.authorColumnsToPrint;
import static constants.Command.VIEW_COMMAND;

//@@author xenthm
/**
 * Represents the <code>view authors</code> command. Handles its execution.
 */
public class ViewAuthorsCommand extends Command {
    public ViewAuthorsCommand() {
        super(VIEW_COMMAND);
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        assert ui != null : "Ui must not be null";
        assert authorList != null : "authorList must not be null";

        CommandValidator.ensureAuthorListNotEmpty(authorList);

        for (Author author : authorList) {
            CommandValidator.ensureValidAuthorName(author.getAuthorName());
        }

        ui.printPreAuthorListMessage(authorList);
        Ui.printList(authorList, authorColumnsToPrint());
    }
}
