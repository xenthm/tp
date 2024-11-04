package parser;

import exceptions.TantouException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract base class responsible for finding and extracting specific arguments
 * (e.g., author or manga names) from a user input string based on a defined pattern.
 *
 * <p>Each subclass of {@code ArgumentFinder} must implement the
 * {@link #getArgumentResult(String)} method, which extracts the argument
 * and returns it as an {@link ArgumentResult} object.
 *
 * <p>The {@code ArgumentFinder} class stores a {@link Pattern} for matching
 * the desired option in the input string, as well as an optional argument option label.
 *
 * @see AuthorArgumentFinder
 * @see MangaArgumentFinder
 */
public abstract class ArgumentFinder {

    /** The regex pattern used to match the specific argument within the user input. */
    protected Pattern pattern;

    /** The matcher used to apply the pattern to the user input string. */
    protected Matcher matcher;

    /** A label representing the option to be matched, such as a prefix or identifier. */
    protected String option;

    /**
     * Constructs an {@code ArgumentFinder} with a specified pattern and option label.
     *
     * @param pattern The {@link Pattern} used to identify and extract the argument.
     * @param option The option label representing the argument type, which may be used
     *               in matching or validation.
     */
    public ArgumentFinder(Pattern pattern, String option) {
        this.pattern = pattern;
        this.option = option;
    }

    /**
     * Abstract method to be implemented by subclasses to parse and extract
     * the argument from the user input string.
     *
     * @param userInput The raw user input string to be parsed.
     * @return An {@link ArgumentResult} containing the extracted argument and any
     *         remaining unparsed portion of the user input.
     * @throws TantouException if the argument cannot be successfully extracted or is invalid.
     */
    public abstract ArgumentResult getArgumentResult(String userInput) throws TantouException;
}

