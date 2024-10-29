package parser;

import exceptions.NoAuthorProvidedException;
import exceptions.TantouException;

import static constants.Options.AUTHOR_OPTION;
import static constants.Regex.AUTHOR_NAME_EXTRACT0R_PATTERN;

public class AuthorArgumentFinder extends ArgumentFinder {
    public AuthorArgumentFinder() {
        super(AUTHOR_NAME_EXTRACT0R_PATTERN, AUTHOR_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) throws TantouException {
        String authorName = null;
        String outputString = null;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            authorName = matcher.group(0).trim();
            if (authorName.isEmpty()) {
                throw new NoAuthorProvidedException();
            }

            // Remove the argument and its matching flag
            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(authorName, outputString);
    }
}
