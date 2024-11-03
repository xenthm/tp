package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorListEmptyException;
import exceptions.AuthorNameTooLongException;
import exceptions.TantouException;
import ui.Ui;

import static author.AuthorList.authorColumnsToPrint;
import static constants.Command.VIEW_COMMAND;
import static constants.Options.MAX_AUTHOR_NAME_LENGTH;

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

        if (authorList.isEmpty()) {
            logger.info("authorList is empty");
            throw new AuthorListEmptyException();
        }

        for (Author author : authorList) {
            if (author.getAuthorName().length() > MAX_AUTHOR_NAME_LENGTH) {
                logger.warning("Author name " + author.getAuthorName() + " exceeds maximum length");
                throw new AuthorNameTooLongException();
            }
        }

        System.out.println("Here are the sla-I mean authors under you! Total: " + authorList.size());
        Ui.printList(authorList, authorColumnsToPrint());
    }
}
