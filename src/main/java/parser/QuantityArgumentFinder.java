package parser;


import static constants.Options.QUANTITY_OPTION;
import static constants.Regex.QUANTITY_EXTRACTOR_PATTERN;

/**
 * A concrete implementation of {@link ArgumentFinder} that extracts a quantity
 * from a user input string based on a predefined pattern.
 *
 * <p>The {@code QuantityArgumentFinder} class applies a regular expression pattern to
 * locate and extract the quantity from the input string. If a price is
 * successfully matched, it removes the matched portion and any associated flag
 * from the input, returning an {@link ArgumentResult} with both the extracted quantity
 * and the remaining input. If no match is found, an {@code ArgumentResult} is returned
 * with a {@code null} quantity.
 */
public class QuantityArgumentFinder extends ArgumentFinder {
    public QuantityArgumentFinder() {
        super(QUANTITY_EXTRACTOR_PATTERN, QUANTITY_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) {
        String quantity = null;
        String outputString = userInput;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            quantity = matcher.group(0).trim();

            // Remove the argument and its matching flag
            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(quantity, outputString);
    }
}
