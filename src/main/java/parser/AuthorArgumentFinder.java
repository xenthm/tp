package parser;

import exceptions.NoAuthorProvidedException;
import exceptions.TantouException;

import static constants.Options.AUTHOR_OPTION;
import static constants.Regex.AUTHOR_NAME_EXTRACT0R_PATTERN;
import static constants.Regex.EMPTY_REGEX;

public class AuthorArgumentFinder extends ArgumentFinder {
    public AuthorArgumentFinder() {
        super(AUTHOR_NAME_EXTRACT0R_PATTERN, AUTHOR_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) throws TantouException {
        String authorName = null;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            authorName = matcher.group(0).trim();
            if (authorName.isEmpty()) {
                throw new NoAuthorProvidedException();
            }

            userInput = userInput
                    .replace(matcher.group(0), EMPTY_REGEX)    // Remove author name part from userInput
                    .replace(option, EMPTY_REGEX)                // Remove AUTHOR_OPTION from userInput
                    .trim();
        }

        return new ArgumentResult(authorName, userInput);
    }
}
