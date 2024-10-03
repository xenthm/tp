package commands;

import author.AuthorList;
import exceptions.TantouException;
import parser.Parser;
import ui.Ui;

public abstract class Command {
    protected Parser parser;
    private String command;
    private boolean isExit;

    public Command(String command) {
        this.command = command;
        this.parser = new Parser();
        this.isExit = false;
    }

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

    public abstract void execute(Ui ui, AuthorList authorList) throws TantouException;
}
