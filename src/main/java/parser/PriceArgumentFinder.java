package parser;


import static constants.Options.PRICE_OPTION;
import static constants.Regex.PRICE_EXTRACTOR_PATTERN;

/**
 * A concrete implementation of {@link ArgumentFinder} that extracts a price
 * from a user input string based on a predefined pattern.
 *
 * <p>The {@code PriceArgumentFinder} class applies a regular expression pattern to
 * locate and extract the price from the input string. If a price is
 * successfully matched, it removes the matched portion and any associated flag
 * from the input, returning an {@link ArgumentResult} with both the extracted price
 * and the remaining input. If no match is found, an {@code ArgumentResult} is returned
 * with a {@code null} price.
 */
public class PriceArgumentFinder extends ArgumentFinder {

    public PriceArgumentFinder() {
        super(PRICE_EXTRACTOR_PATTERN, PRICE_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) {
        String price = null;
        String outputString = userInput;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            price = matcher.group(0).trim();

            // Remove the argument and its matching flag
            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(price, outputString);
    }

}
