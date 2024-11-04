package parser;

/**
 * A container class that holds the result of an argument extraction operation,
 * including the extracted argument and any remaining unparsed portion of the input.
 *
 * <p>The {@code ArgumentResult} class provides access to both the extracted argument
 * as a {@code String} and the remaining unprocessed portion of the input string,
 * enabling further parsing or processing if necessary.
 */
public class ArgumentResult {
    private String argument;
    private String outputString;

    public ArgumentResult(String argument, String outputString) {
        this.argument = argument;
        this.outputString = outputString;
    }

    public String getArgument() {
        return argument;
    }

    public String getOutputString() {
        return outputString;
    }
}
