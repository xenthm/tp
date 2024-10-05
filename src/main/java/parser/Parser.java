package parser;

import commands.AddAuthorCommand;
import commands.AddMangaCommand;
import commands.ByeCommand;
import commands.Command;
import exceptions.TantouException;

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
    private Options options;
    private CommandLine command;

    public Parser() {
        this.ownParser = new DefaultParser();
        this.options = new Options();
        this.options.addOption("a", "author", true, "Author name");
        this.options.addOption("m", "manga", true, "Manga name");
    }

    public Command getUserCommand(String userInput) throws TantouException {
        userInput = userInput.trim();
        String[] inputList = getUserInputAsList(userInput);
        switch (inputList[COMMAND_INDEX]) {
        case BYE_COMMAND:
            return new ByeCommand();
        case ADD_COMMAND:
            if (isValidAddMangaCommand(userInput)) {
                return new AddMangaCommand(userInput);
            } else if (isValidAddAuthorCommand(userInput)) {
                return new AddAuthorCommand(userInput);
            }

            throw new TantouException("Invalid add command provided!");
        default:
            throw new TantouException("Invalid command provided!");
        }
    }

    public String[] getUserInputAsList(String userInput) {
        // Ensures that spaces within quotes are not considered split points
        return userInput.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    public String getAuthorNameFromInput(String userInput) throws TantouException {
        try {
            command = ownParser.parse(options, getUserInputAsList(userInput));
            return command.getOptionValue("a");
        } catch (ParseException e) {
            throw new TantouException(String.format("Something went wrong when parsing: %s", e.getMessage()));
        }
    }

    public String getMangaNameFromInput(String userInput) throws TantouException {
        try {
            command = ownParser.parse(options, getUserInputAsList(userInput));
            return command.getOptionValue("m");
        } catch (ParseException e) {
            throw new TantouException(String.format("Something went wrong when parsing: %s", e.getMessage()));
        }
    }

    public boolean isValidAddAuthorCommand(String userInput) throws TantouException {
        try {
            command = ownParser.parse(options, userInput.split(SPACE_REGEX));
            return command.hasOption("a") && !command.getOptionValue("a").isEmpty();
        } catch (ParseException e) {
            throw new TantouException(String.format("Something went wrong when parsing: %s", e.getMessage()));
        }
    }

    public boolean isValidAddMangaCommand(String userInput) throws TantouException {
        try {
            command = ownParser.parse(options, getUserInputAsList(userInput));
            return command.hasOption("a") && command.hasOption("m");
        } catch (ParseException e) {
            throw new TantouException(String.format("Something went wrong when parsing: %s", e.getMessage()));
        }
    }
}
