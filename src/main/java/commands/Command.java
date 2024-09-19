package commands;

import parser.Parser;
import ui.Ui;

public abstract class Command {
    private String command;
    protected Parser parser;
    private boolean isExit;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public Command(String command) {
        this.command = command;
        this.parser = new Parser();
        this.isExit = false;
    }

    public abstract void execute(Ui ui);
}
