package parser;

import exceptions.NoMangaProvidedException;
import exceptions.TantouException;


import static constants.Options.MANGA_OPTION;
import static constants.Regex.MANGA_NAME_EXTRACTOR_PATTERN;

public class MangaArgumentFinder extends ArgumentFinder {
    public MangaArgumentFinder() {
        super(MANGA_NAME_EXTRACTOR_PATTERN, MANGA_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) throws TantouException {
        String mangaName = null;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            mangaName = matcher.group(0).trim();
            if (mangaName.isEmpty()) {
                throw new NoMangaProvidedException();
            }

            userInput = userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(mangaName, userInput);
    }
}
