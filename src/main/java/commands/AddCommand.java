package commands;

import static constants.Command.ADD_COMMAND;

public abstract class AddCommand extends Command {
    protected String userInput;

    public AddCommand(String userInput) {
        super(ADD_COMMAND);
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
