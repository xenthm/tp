package parser;

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
