package commands;

import author.AuthorList;
import storage.Storage;
import ui.Ui;

import static constants.Command.BYE_COMMAND;

//@@author averageandyyy
public class ByeCommand extends Command {
    public ByeCommand() {
        super(BYE_COMMAND);
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) {
        ui.sayGoodbye();
        setExit(true);
        Storage.getInstance().saveAuthorListToDataFile(authorList);
    }
}
