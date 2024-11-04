package parser;

import exceptions.NoAuthorProvidedException;
import exceptions.TantouException;

import static constants.Options.QUANTITY_OPTION;
import static constants.Regex.QUANTITY_EXTRACTOR_PATTERN;

public class QuantityArgumentFinder extends ArgumentFinder {
    public QuantityArgumentFinder() {
        super(QUANTITY_EXTRACTOR_PATTERN, QUANTITY_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) throws TantouException {
        String quantity = null;
        String outputString = userInput;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            quantity = matcher.group(0).trim();
            if (quantity.isEmpty()) {
                throw new NoAuthorProvidedException();
            }

            // Remove the argument and its matching flag
            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(quantity, outputString);
    }
}
