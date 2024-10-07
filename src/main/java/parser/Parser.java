package parser;

import commands.Command;
import commands.AddAuthorCommand;
import commands.AddMangaCommand;
import commands.ByeCommand;
import commands.DeleteAuthorCommand;
import commands.DeleteMangaCommand;
import commands.ViewAuthorsCommand;
import exceptions.TantouException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static constants.Command.ADD_COMMAND;
import static constants.Command.BYE_COMMAND;
import static constants.Command.VIEW_COMMAND;
import static constants.Command.COMMAND_INDEX;
import static constants.Command.DELETE_COMMAND;
import static constants.Options.LONG_OPTION_INDEX;
import static constants.Options.OPTIONS_ARRAY;
import static constants.Options.OPTION_DESC_INDEX;
import static constants.Options.SHORT_OPTION_INDEX;
import static constants.Regex.USER_COMMAND_REGEX;

public class Parser {
    private CommandLineParser ownParser;
    private Options options;
    private CommandLine command;

    public Parser() {
        this.ownParser = new DefaultParser();
        this.options = new Options();
        initializeOptions();
    }

    /**
     * Initializes command-line options by adding each option defined in
     * the {@link constants.Options#OPTIONS_ARRAY} to the options list.
     *
     * <p>This method iterates over the options defined in the
     * {@link constants.Options#OPTIONS_ARRAY} and adds each option to the
     * internal options collection. Each option is configured with
     * its short name, long name, and description, and is marked as
     * requiring an argument.</p>
     *
     * @see constants.Options#OPTIONS_ARRAY
     * @see #options
     */
    public void initializeOptions() {
        for (String[] option : OPTIONS_ARRAY) {
            this.options.addOption(option[SHORT_OPTION_INDEX], option[LONG_OPTION_INDEX],
                    true, option[OPTION_DESC_INDEX]);
        }
    }

    public Command getUserCommand(String userInput) throws TantouException {
        userInput = userInput.trim();
        String[] inputList = getUserInputAsList(userInput);

        if (inputList.length == 0) {
            throw new TantouException("Hey! Say something!");
        }

        switch (inputList[COMMAND_INDEX]) {
        case BYE_COMMAND:
            return new ByeCommand();
        case ADD_COMMAND:
            if (isValidMangaCommand(userInput)) {
                return new AddMangaCommand(userInput);
            } else if (isValidAuthorCommand(userInput)) {
                return new AddAuthorCommand(userInput);
            }
            throw new TantouException("Invalid add command provided!");
        case VIEW_COMMAND:
            return new ViewAuthorsCommand();
        case DELETE_COMMAND:
            if (isValidMangaCommand(userInput)) {
                return new DeleteMangaCommand(userInput);
            } else if (isValidAuthorCommand(userInput)) {
                return new DeleteAuthorCommand(userInput);
            }
            throw new TantouException("Invalid delete command provided!");
        default:
            throw new TantouException("Invalid command provided!");
        }
    }

    /**
     * Parses the user input string into an array of strings, capturing
     * quoted strings and individual words, while validating the argument
     * format.
     *
     * <p>This method uses a regular expression to extract quoted strings
     * (including the quotes) and unquoted words from the provided user input.
     * It then calls {@link #validateArguments(ArrayList)} to ensure that
     * all options have their corresponding arguments in quotes. If any
     * argument is incorrectly formatted, a {@link TantouException} is thrown.</p>
     *
     * @param userInput a String representing the user input containing
     *                  command-line arguments. It may include options
     *                  and arguments in quoted format.
     * @return an array of strings representing the parsed user input,
     *         with each option and argument as separate entries.
     * @throws TantouException if any option's argument is not enclosed in quotes
     *                         or if the input format is invalid.
     */
    public String[] getUserInputAsList(String userInput) throws TantouException {
        Matcher matcher = USER_COMMAND_REGEX.matcher(userInput);
        ArrayList<String> list = new ArrayList<>();

        while (matcher.find()) {
            String match = matcher.group();
            list.add(match);
        }

        validateArguments(list);

        return list.toArray(new String[0]);
    }

    /**
     * Validates the arguments provided in the list to ensure that
     * all options have their corresponding arguments enclosed in quotes.
     *
     * <p>This method iterates through the list of arguments and checks each
     * option against the predefined options in the {@link constants.Options#OPTIONS_ARRAY}.
     * If an option is found, it verifies that the next element in the list is a
     * quoted string. If the argument is not quoted, a {@link TantouException}
     * is thrown with a descriptive message.</p>
     *
     * @param list an ArrayList of Strings representing the command-line arguments
     *             provided by the user. Each option must have its argument in
     *             quotes (e.g., "-a \"Kubo Tite\"").
     * @throws TantouException if any option's argument is not enclosed in quotes.
     */
    public void validateArguments(ArrayList<String> list) throws TantouException {
        for (int i = 0; i < list.size(); i++) {
            for (String [] option : OPTIONS_ARRAY) {
                String optionWithDash = "-" + option[SHORT_OPTION_INDEX];
                validateArgument(list, optionWithDash, i);
            }
        }
    }

    /**
     * Validates that the argument following an option is a quoted string.
     *
     * @param list the command-line arguments
     * @param optionIndex the index of the option being validated
     * @throws TantouException if the argument is not enclosed in quotes
     */
    public void validateArgument(ArrayList<String> list, String optionWithDash, int optionIndex)
            throws TantouException {
        if (optionWithDash.equals(list.get(optionIndex))) {
            // Check if the next element is a quoted string
            if (optionIndex + 1 >= list.size() || !isQuotedString(list.get(optionIndex + 1))) {
                throw new TantouException("Arguments must be in quotes, e.g., -a \"Kubo Tite\"");
            }
        }
    }

    /**
     * Checks if a given string is enclosed in quotes.
     *
     * @param argument the string to check
     * @return true if the string starts and ends with a quote, false otherwise
     */
    public boolean isQuotedString(String argument) {
        return argument.startsWith("\"") && argument.endsWith("\"");
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

    public boolean isValidAuthorCommand(String userInput) throws TantouException {
        try {
            command = ownParser.parse(options, getUserInputAsList(userInput));
            return command.hasOption("a");
        } catch (ParseException e) {
            throw new TantouException(String.format("Something went wrong when parsing: %s", e.getMessage()));
        }
    }

    public boolean isValidMangaCommand(String userInput) throws TantouException {
        try {
            command = ownParser.parse(options, getUserInputAsList(userInput));
            return command.hasOption("a") && command.hasOption("m");
        } catch (ParseException e) {
            throw new TantouException(String.format("Something went wrong when parsing: %s", e.getMessage()));
        }
    }

}
