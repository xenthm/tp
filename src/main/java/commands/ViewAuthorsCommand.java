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
        if (authorList.size() == 0) {
            System.out.println("You have no authors under you! Maybe you are the one slacking...");
            return;
        }
        authorList.print();
    }
}
