package parser;

import exceptions.TantouException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ArgumentFinder {
    protected Pattern pattern;
    protected Matcher matcher;
    protected String option;

    public ArgumentFinder(Pattern pattern, String option) {
        this.pattern = pattern;
        this.option = option;
    }

    public abstract ArgumentResult getArgumentResult(String userInput) throws TantouException;
}
