package commands;

import author.Author;
import author.AuthorList;
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
    public void execute(Ui ui, AuthorList authorList) {
        assert ui != null : "Ui must not be null";
        assert authorList != null : "authorList must not be null";

        if (authorList.isEmpty()) {
            System.out.println("You have no authors under you! Maybe you are the one slacking...");
            logger.info("authorList is empty");
            return;
        }

        System.out.println("Here are the sla-I mean authors under you! Total: " + authorList.size());
        Ui.printList(authorList, authorColumnsToPrint());
    }
}
