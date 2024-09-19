package parser;

import commands.AddAuthorCommand;
import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.GreetCommand;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static constants.Command.ADD_COMMAND;
import static constants.Command.BYE_COMMAND;
import static constants.Command.COMMAND_INDEX;
import static constants.Regex.SPACE_REGEX;

public class Parser {
    private CommandLineParser ownParser;
    private CommandLine command;
    private Options options;

    public Parser() {
        this.ownParser = new DefaultParser();
        this.options = new Options();
        this.options.addOption("a", "author", true, "Author name");
    }

    public Command getUserCommand(String userInput) {
        userInput = userInput.trim();
        String[] inputList = getUserInputAsList(userInput);
        switch (inputList[COMMAND_INDEX]) {
        case BYE_COMMAND:
            return new ByeCommand();
        case ADD_COMMAND:
            if (isValidAddAuthor(userInput)) {
                return new AddAuthorCommand(userInput);
            }
            return new GreetCommand();
        default:
            return new GreetCommand();
        }
    }

    public String[] getUserInputAsList(String userInput) {
        return userInput.split(SPACE_REGEX);
    }

    public String getAuthorName(String[] inputList) throws ParseException {
        command = ownParser.parse(options, inputList);

        if (command.hasOption("a")) {
            return command.getOptionValue("a");
        }

        return "";
    }

    public boolean isValidAddAuthor(String userInput) {
        try {
            command = ownParser.parse(options, userInput.split(SPACE_REGEX));
            return command.hasOption("a") && !command.getOptionValue("a").isEmpty();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
