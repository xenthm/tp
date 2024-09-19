package commands;

import ui.Ui;

import static constants.Command.BYE_COMMAND;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(BYE_COMMAND);
    }

    @Override
    public void execute(Ui ui) {
        ui.sayGoodbye();
        setExit(true);
    }
}
