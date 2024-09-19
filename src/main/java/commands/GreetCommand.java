package commands;

import ui.Ui;

public class GreetCommand extends Command {
    public GreetCommand() {
        super("hi");
    }

    @Override
    public void execute(Ui ui) {
        ui.greetUser();
    }
}
