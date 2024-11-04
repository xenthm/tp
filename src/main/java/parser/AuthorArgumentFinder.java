package parser;

import static constants.Options.AUTHOR_OPTION;
import static constants.Regex.AUTHOR_NAME_EXTRACT0R_PATTERN;

/**
 * A concrete implementation of {@link ArgumentFinder} that extracts an author's name
 * from a user input string based on a predefined pattern.
 *
 * <p>The {@code AuthorArgumentFinder} class applies a regular expression pattern to
 * locate and extract the author name from the input string. If an author name is
 * successfully matched, it removes the matched portion and any associated flag
 * from the input, returning an {@link ArgumentResult} with both the extracted name
 * and the remaining input. If no match is found, an {@code ArgumentResult} is returned
 * with a {@code null} author name.
 */
public class AuthorArgumentFinder extends ArgumentFinder {
    public AuthorArgumentFinder() {
        super(AUTHOR_NAME_EXTRACT0R_PATTERN, AUTHOR_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) {
        String authorName = null;
        String outputString = userInput;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            authorName = matcher.group(0).trim();

            // Remove the argument and its matching flag
            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(authorName, outputString);
    }
}
