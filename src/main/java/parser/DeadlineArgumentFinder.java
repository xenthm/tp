package parser;

import exceptions.TantouException;

import static constants.Options.BY_DATE_OPTION;
import static constants.Regex.BY_DATE_EXTRACTOR_PATTERN;

public class DeadlineArgumentFinder extends ArgumentFinder {
    public DeadlineArgumentFinder() {
        super(BY_DATE_EXTRACTOR_PATTERN, BY_DATE_OPTION);
    }

    @Override
    public ArgumentResult getArgumentResult(String userInput) throws TantouException {
        String deadline = null;
        String outputString = userInput;
        matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            deadline = matcher.group(0).trim();

            outputString =
                    userInput.substring(0, matcher.start(0) - 3) + userInput.substring(matcher.end(0));
        }

        return new ArgumentResult(deadline, outputString);
    }
}
