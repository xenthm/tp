package parser;

import commands.AddAuthorCommand;
import commands.AddMangaCommand;
import commands.AddSalesCommand;
import commands.AddDeadlineCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteAuthorCommand;
import commands.DeleteMangaCommand;
import commands.ViewAuthorsCommand;
import commands.ViewMangasCommand;
import exceptions.InvalidViewCommandException;
import exceptions.NoAuthorProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.NoPriceProvidedException;
import exceptions.NoQuantityProvidedException;
import exceptions.TantouException;

import static constants.Command.BYE_COMMAND;
import static constants.Command.CATALOG_COMMAND;
import static constants.Command.SALES_COMMAND;
import static constants.Command.SCHEDULE_COMMAND;
import static constants.Command.VIEW_COMMAND;
import static constants.Command.COMMAND_INDEX;
import static constants.Options.BY_DATE_OPTION;
import static constants.Options.SALES_OPTION;
import static constants.Regex.ANY_SPACE_REGEX;
import static constants.Regex.DELETE_OPTION_REGEX;
import static constants.Regex.EMPTY_REGEX;
import static constants.Regex.SPACE_REGEX;
import static constants.Regex.BY_DATE_OPTION_REGEX;
import static constants.Regex.MANGA_OPTION_REGEX;
import static constants.Regex.AUTHOR_OPTION_REGEX;

public class Parser {
    /**
     * Processes a user input string and returns a specific {@link Command} object based on the
     * detected command keyword within the input.
     *
     * <p>This method trims the user input, checks if it is empty, and parses it into a list of
     * command elements. It then uses a switch expression to identify the command type
     * and returns the appropriate {@link Command} subclass (e.g., {@link ByeCommand}).
     * If the input is invalid or does not match any known command, it throws a {@link TantouException}.
     *
     * @param userInput The raw user input string containing the command keyword and any arguments.
     * @return A specific {@link Command} object based on the command type in the user input.
     * @throws TantouException if the user input is empty or does not match any known command.
     */
    public Command getUserCommand(String userInput) throws TantouException {
        String trimmedUserInput = userInput.trim();

        if (trimmedUserInput.isEmpty()) {
            throw new TantouException("Hey! Say something!");
        }

        String[] inputList = getUserInputAsList(trimmedUserInput);

        return switch (inputList[COMMAND_INDEX]) {
        case BYE_COMMAND -> new ByeCommand();
        case CATALOG_COMMAND ->
                processCatalogCommand(trimmedUserInput);
        case VIEW_COMMAND ->
                processViewCommand(trimmedUserInput);
        case SALES_COMMAND ->
                processAddSalesCommand(trimmedUserInput);
        case SCHEDULE_COMMAND ->
                processScheduleCommand(trimmedUserInput);
        default ->
                throw new TantouException("Invalid command provided!");
        };
    }

    //@@author averageandyyy
    private String[] getUserInputAsList(String userInput) throws TantouException {
        return userInput.split(SPACE_REGEX);
    }

    // Catalog Functions
    //@@author averageandyyy
    /**
     * Processes a catalog-related command based on the user input string and returns the
     * appropriate {@link Command} object.
     *
     * <p>This method removes the catalog prefix from the user input and determines if it is a
     * deletion command. If so, it calls {@link #processAuthorMangaCommand(String, boolean)}
     * with a flag indicating a deletion; otherwise, it processes the command as an addition
     * or view command.
     *
     * @param userInput The user input string containing catalog-related command details.
     *                  This string should include a prefix to identify it as a catalog command.
     * @return A specific {@link Command} object that represents the parsed catalog operation.
     * @throws TantouException if the user input is invalid or does not follow expected format.
     */
    private Command processCatalogCommand(String userInput) throws TantouException {
        userInput = removeCatalogPrefix(userInput);
        if (isValidDeleteCommand(userInput)) {
            // userInput = removeDeleteOption(userInput);
            return processAuthorMangaCommand(userInput, true);
        }
        return processAuthorMangaCommand(userInput, false);
    }

    private String removeCatalogPrefix(String userInput) {
        return userInput.replace(CATALOG_COMMAND, EMPTY_REGEX);
    }

    //@@author averageandyyy
    /**
     * Checks if the given user input string represents a valid delete command.
     *
     * <p>This method verifies that the user input contains the delete option pattern,
     * either as a standalone word or at the end of the string, by matching against
     * a regular expression.
     *
     * @param userInput The user input string to be validated as a delete command.
     * @return {@code true} if the input contains a valid delete option; {@code false} otherwise.
     * @throws TantouException if the user input cannot be processed as expected.
     */
    private boolean isValidDeleteCommand(String userInput) throws TantouException {
        return userInput.contains(DELETE_OPTION_REGEX + SPACE_REGEX) || userInput.endsWith(DELETE_OPTION_REGEX);
    }

    //@@author averageandyyy
    /**
     * Processes a command related to adding or deleting an author or manga, based on the
     * provided user input and deletion flag.
     *
     * <p>This method extracts author and manga names from the user input using
     * {@link AuthorArgumentFinder} and {@link MangaArgumentFinder}. Based on the
     * extracted arguments and the deletion flag, it creates and returns the appropriate
     * {@link Command} object (e.g., {@link AddAuthorCommand}, {@link DeleteAuthorCommand}).
     *
     * @param userInput The user input string containing the author and/or manga details.
     * @param isDelete A flag indicating if the command is a deletion ({@code true}) or
     *                 an addition ({@code false}).
     * @return A specific {@link Command} object representing the intended action, such as
     *         adding or deleting an author or manga.
     * @throws TantouException if the user input is invalid or the command cannot be processed.
     */
    private Command processAuthorMangaCommand(String userInput, boolean isDelete) throws TantouException {
        String authorName = null;
        String mangaName = null;

        AuthorArgumentFinder authorArgumentFinder = new AuthorArgumentFinder();
        MangaArgumentFinder mangaArgumentFinder = new MangaArgumentFinder();

        ArgumentResult authorResult = authorArgumentFinder.getArgumentResult(userInput);
        authorName = authorResult.getArgument();
        String userInputPostAuthorExtraction = authorResult.getOutputString();

        ArgumentResult mangaResult = mangaArgumentFinder.getArgumentResult(userInputPostAuthorExtraction);
        mangaName = mangaResult.getArgument();
        String userInputPostMangaExtraction = mangaResult.getOutputString();

        if (mangaName == null) {
            if (isDelete) {
                return new DeleteAuthorCommand(authorName);
            }
            return new AddAuthorCommand(authorName);
        }

        if (isDelete) {
            return new DeleteMangaCommand(new String[]{authorName, mangaName});
        }

        return new AddMangaCommand(new String[]{authorName, mangaName});
    }

    // Sales Functions
    //@@author sarahchow03
    /**
     * Processes the user input to create a Sales command for either an Author.
     * <p>
     * If the input is a valid sales command, it returns an `DeleteMangaCommand`.
     *
     * @param userInput the input string provided by the user,
     *                  which should include the author, manga, quantity sold, and unit price.
     * @return a Command object that adds the sales data for the specified manga.
     * @throws TantouException if the user input is missing a parameter.
     */
    private Command processAddSalesCommand(String userInput) throws TantouException {
        userInput = removeSalesPrefix(userInput);
        String[] salesArguments = getSalesArguments(userInput);
        return new AddSalesCommand(salesArguments);
    }

    //@@author averageandyyy
    private String removeSalesPrefix(String userInput) {
        return userInput.replace(SALES_COMMAND, EMPTY_REGEX);
    }

    //@@author sarahchow03
    /**
     * Parses and retrieves arguments (author name, manga name, quantity, and price) for AddSalesCommand.
     *
     * <p>This method utilizes argument finders to extract specific sales-related arguments from the input string.
     * If any required argument is missing, a corresponding exception is thrown.</p>
     *
     * @param userInput The input string containing potential sales information.
     * @return A String array containing extracted arguments in the following order: author name, manga name, quantity,
     *         and price.
     * @throws NoAuthorProvidedException   If the author name or argument is not found in the input.
     * @throws NoMangaProvidedException    If the manga name or argument is not found in the input.
     * @throws NoQuantityProvidedException If the quantity or quantity argument is not found in the input.
     * @throws NoPriceProvidedException    If the price or price argument is not found in the input.
     * @throws TantouException             For any other exception that may occur during processing.
     */
    private String[] getSalesArguments(String userInput) throws TantouException {
        String authorName = null;
        String mangaName = null;
        String price = null;
        String quantity = null;

        AuthorArgumentFinder authorArgumentFinder = new AuthorArgumentFinder();
        MangaArgumentFinder mangaArgumentFinder = new MangaArgumentFinder();
        PriceArgumentFinder priceArgumentFinder = new PriceArgumentFinder();
        QuantityArgumentFinder quantityArgumentFinder = new QuantityArgumentFinder();

        ArgumentResult authorResult = authorArgumentFinder.getArgumentResult(userInput);
        authorName = authorResult.getArgument();
        String userInputPostAuthorExtraction = authorResult.getOutputString();

        ArgumentResult mangaResult = mangaArgumentFinder.getArgumentResult(userInputPostAuthorExtraction);
        mangaName = mangaResult.getArgument();
        String userInputPostMangaExtraction = mangaResult.getOutputString();

        ArgumentResult quantityResult = quantityArgumentFinder.getArgumentResult(userInputPostMangaExtraction);
        quantity = quantityResult.getArgument();
        String userInputPostQuantityExtraction = quantityResult.getOutputString();

        ArgumentResult priceResult = priceArgumentFinder.getArgumentResult(userInputPostQuantityExtraction);
        price = priceResult.getArgument();

        return new String[]{authorName, mangaName, quantity, price};
    }


    //@@author xenthm
    /**
     * Processes the user input to create a <code>View</code> command to either show the list of authors, or the list
     * of manga authored by a specific author. The latter allows for additional option flags to show more information
     * about the manga deadlines or sales data.
     * <p>
     * Parsing is done with regex and <code>String</code> splitting. Allows for duplicate option flags, except for the
     * author option flag. The regex matcher will recognise all valid text after a valid author option flag as part
     * of the author name, including duplicate author option flags.
     *
     * @param userInput the input string provided by the user
     * @return <code>ViewAuthorsCommand</code> or <code>ViewMangasCommand</code>, represents the view operation
     * @throws NoAuthorProvidedException   if the author is not specified and it is needed
     * @throws InvalidViewCommandException if an invalid option flag is provided
     */
    private Command processViewCommand(String userInput) throws TantouException {
        userInput = removeViewPrefix(userInput);
        String authorName = null;
        boolean hasByDateFlag = false;
        boolean hasSalesFlag = false;

        AuthorArgumentFinder authorArgumentFinder = new AuthorArgumentFinder();
        ArgumentResult result = authorArgumentFinder.getArgumentResult(userInput);
        authorName = result.getArgument();
        String userInputPostAuthorExtraction = result.getOutputString();

        if (userInputPostAuthorExtraction != null) {
            // Split the remaining around any number of spaces
            String[] tokens = userInputPostAuthorExtraction.split(ANY_SPACE_REGEX);
            for (String token : tokens) {
                switch (token) {
                case BY_DATE_OPTION:
                    hasByDateFlag = true;
                    break;
                case SALES_OPTION:
                    hasSalesFlag = true;
                    break;
                case EMPTY_REGEX:
                    break;
                default:
                    throw new InvalidViewCommandException(token);

                }
            }

            if ((hasByDateFlag || hasSalesFlag) && authorName == null) {
                throw new NoAuthorProvidedException();
            }
        }

        if (authorName == null) {
            return new ViewAuthorsCommand();
        }
        return new ViewMangasCommand(authorName, hasByDateFlag, hasSalesFlag);
    }

    //@@author
    private String removeViewPrefix(String userInput) {
        return userInput.replace(VIEW_COMMAND, EMPTY_REGEX);
    }
  
    //@@author iaso1774
    /**
     * Processes the user input to change the deadline for a Manga.
     * Determines whether the input corresponds to an existing Author and Manga.
     * Converts the inputted date to LocalDate type if possible.
     * <p>
     * If the input is valid, it returns an `AddDeadlineCommand`.
     *
     * @param userInput the input string provided by the user,
     *                  which should specify a Schedule operation for a specified Manga and Author
     * @return an AddDeadlineCommand object
     * @throws TantouException if the user input is invalid
     */
    private Command processScheduleCommand(String userInput) throws TantouException {
        String authorName = null;
        String mangaName = null;
        String deadline = null;

        AuthorArgumentFinder authorArgumentFinder = new AuthorArgumentFinder();
        MangaArgumentFinder mangaArgumentFinder = new MangaArgumentFinder();
        DeadlineArgumentFinder deadlineArgumentFinder = new DeadlineArgumentFinder();

        ArgumentResult authorResult = authorArgumentFinder.getArgumentResult(userInput);
        authorName = authorResult.getArgument();
        String userInputPostAuthorExtraction = authorResult.getOutputString();

        ArgumentResult mangaResult = mangaArgumentFinder.getArgumentResult(userInputPostAuthorExtraction);
        mangaName = mangaResult.getArgument();
        String userInputPostMangaExtraction = mangaResult.getOutputString();

        ArgumentResult deadlineResult = deadlineArgumentFinder.getArgumentResult(userInputPostMangaExtraction);
        deadline = deadlineResult.getArgument();

        return new AddDeadlineCommand(new String[]{authorName, mangaName, deadline});
    }

    /**
     * Checks that the deadline comes after the manga and author arguments.
     * The ordering of the manga and author don't matter.
     * <p>
     * If the input is valid, it returns true.
     *
     * @param userInput the input string provided by the user,
     *                  which should specify a Schedule operation for a specified Manga and Author
     * @return true if the ordering is valid, false otherwise
     */
    private Boolean isValidOrdering(String userInput) {
        int indexOfManga = userInput.indexOf(MANGA_OPTION_REGEX);
        int indexOfAuthor = userInput.indexOf(AUTHOR_OPTION_REGEX);
        int indexOfDeadline = userInput.indexOf(BY_DATE_OPTION_REGEX);
        return indexOfDeadline > indexOfManga && indexOfDeadline > indexOfAuthor;
    }

}
