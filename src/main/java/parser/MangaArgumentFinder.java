package parser;

import static constants.Options.MANGA_OPTION;
import static constants.Regex.MANGA_NAME_EXTRACTOR_PATTERN;

/**
 * A concrete implementation of {@link ArgumentFinder} that extracts a manga name
 * from a user input string based on a predefined pattern.
 *
 * <p>The {@code MangaArgumentFinder} class applies a regular expression pattern to
 * locate and extract the manga name from the input string. If a manga name is
 * successfully matched, it removes the matched portion and any associated flag
 * from the input, returning an {@link ArgumentResult} with both the extracted name
 * and the remaining input. If no match is found, an {@code ArgumentResult} is returned
 * with a {@code null} manga name.
 */
public class MangaArgumentFinder extends ArgumentFinder {
    public MangaArgumentFinder() {
        super(MANGA_NAME_EXTRACTOR_PATTERN, MANGA_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) {
        String mangaName = null;
        String outputString = userInput;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            mangaName = matcher.group(0).trim();

            outputString = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(mangaName, outputString);
    }
}
