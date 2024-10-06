package constants;

import java.util.regex.Pattern;

public class Regex {
    public static final String SPACE_REGEX = " ";

    /** Pattern to match quoted strings or individual words */
    public static final Pattern USER_COMMAND_REGEX = Pattern.compile("\"[^\"]*\"|\\S+");
}
