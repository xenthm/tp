package commands;

import author.AuthorList;
import ui.Ui;

import static constants.Command.VIEW_COMMAND;

public class ViewAuthorsCommand extends Command {
    public ViewAuthorsCommand() {
        super(VIEW_COMMAND);
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) {
        assert ui != null : "Ui must not be null";
        assert authorList != null : "authorList must not be null";

        if (authorList.size() == 0) {
            System.out.println("You have no authors under you! Maybe you are the one slacking...");
            logger.info("authorList is empty");
            return;
        }
        authorList.print();
    }
}
