package parser;

import exceptions.NoAuthorProvidedException;
import exceptions.TantouException;

import static constants.Options.AUTHOR_OPTION;
import static constants.Regex.AUTHOR_NAME_EXTRACT0R_PATTERN;

/**
 * A concrete implementation of {@link ArgumentFinder} that extracts an author's name
 * from a user input string based on a predefined pattern.
 *
 * <p>The {@code AuthorArgumentFinder} class uses a regular expression pattern to
 * locate and extract the author name within the input string. If the author's name
 * is found, it returns an {@link ArgumentResult} containing both the name and the
 * remaining portion of the input string. If no valid author name is found, or if
 * the extracted name is empty, it throws a {@link NoAuthorProvidedException}.
 */
public class AuthorArgumentFinder extends ArgumentFinder {
    public AuthorArgumentFinder() {
        super(AUTHOR_NAME_EXTRACT0R_PATTERN, AUTHOR_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) {
        String authorName = null;
        String outputString = null;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            authorName = matcher.group(0).trim();

            // Remove the argument and its matching flag
            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(authorName, outputString);
    }
}
