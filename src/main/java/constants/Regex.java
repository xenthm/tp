package constants;

import java.util.regex.Pattern;

import static constants.Options.AUTHOR_OPTION;
import static constants.Options.DELETE_OPTION;
import static constants.Options.MANGA_OPTION;
import static constants.Options.OPTIONS_ARRAY;
import static constants.Options.PRICE_OPTION;
import static constants.Options.QUANTITY_OPTION;
import static constants.Options.BY_DATE_OPTION;

public class Regex {
    public static final String SPACE_REGEX = " ";
    public static final String ANY_SPACE_REGEX = "\\s+";
    public static final String EMPTY_REGEX = "";
    public static final String DIVIDER_REGEX = " | ";
    public static final String AUTHOR_OPTION_REGEX = SPACE_REGEX + AUTHOR_OPTION + SPACE_REGEX;
    public static final String DELETE_OPTION_REGEX = SPACE_REGEX + DELETE_OPTION;
    public static final String MANGA_OPTION_REGEX = SPACE_REGEX + MANGA_OPTION + SPACE_REGEX;
    public static final String PRICE_OPTION_REGEX = SPACE_REGEX + PRICE_OPTION + SPACE_REGEX;
    public static final String QUANTITY_OPTION_REGEX = SPACE_REGEX + QUANTITY_OPTION + SPACE_REGEX;
    public static final String BY_DATE_OPTION_REGEX = SPACE_REGEX + BY_DATE_OPTION + SPACE_REGEX;

    //@@author xenthm
    /**
     * Regex pattern to extract the author name out of a given input
     */
    public static final Pattern AUTHOR_NAME_EXTRACT0R_PATTERN = generateExtractorPattern(AUTHOR_OPTION);

    //@@author averageandyyy
    /**
     * Regex pattern to extract the manga name out of a given input
     */
    public static final Pattern MANGA_NAME_EXTRACTOR_PATTERN = generateExtractorPattern(MANGA_OPTION);

    //@@author iaso1774
    /**
     * Regex pattern to extract the deadline out of a given input
     */
    public static final Pattern BY_DATE_EXTRACTOR_PATTERN = generateExtractorPattern(BY_DATE_OPTION);
    //@@author sarahchow03
    /**
     * Regex pattern to extract price per unit out of a given input
     */
    public static final Pattern PRICE_EXTRACTOR_PATTERN = generateExtractorPattern(PRICE_OPTION);

    //@@author sarahchow03
    /**
     * Regex pattern to extract the manga name out of a given input
     */
    public static final Pattern QUANTITY_EXTRACTOR_PATTERN = generateExtractorPattern(QUANTITY_OPTION);

    //@@author xenthm 
    /**
     * This method automatically generates a valid regex pattern to use with a field (e.g. author name) extractor. The
     * regex works by looking for the <code>includedOptionFlag</code> within the input and taking every character after
     * that as a possibly valid part of the field of interest.
     * If any other flags from <code>OPTIONS_ARRAY</code> are found
     * after the <code>includedOptionFlag</code>, the field of interest is terminated.
     * <p>
     * Developers should write their own logic if their <code>Command</code> only has an
     * <code>includedOptionFlag</code> and no <code>excludedOptionFlags</code> as the parsing logic does not require
     * this complex regex!
     *
     * @param includedOptionFlag  the option flag preceding a field of interest
     * @return compiled regex <code>Pattern</code> to be used with a regex <code>Matcher</code>
     */
    private static Pattern generateExtractorPattern(String includedOptionFlag) {
        assert includedOptionFlag != null : "Must provide option of interest!";

        StringBuilder regex = new StringBuilder();

        regex.append("(?<=\\s").append(includedOptionFlag).append(")$");    // The input ends with includedOptionFlag
        regex.append("|");                                                  // or
        regex.append("(?<=\\s").append(includedOptionFlag).append("\\s)");  // (there exists includedOptionFlag before
        regex.append(".*?");                                                // any number of any character
        regex.append("(?=");                                                // and what comes after them is either:
        {
            regex.append("(?<=\\s)");                                       // [any of the excludedOptionFlags
            regex.append("-[");
            for (String option : OPTIONS_ARRAY) {
                regex.append(option.replace("-", EMPTY_REGEX));
            }
            regex.append("]");
            regex.append("(?:\\s|$)");                                      // followed by either a space or the end]
        }
        regex.append("|$)");                                                // or the end of the input)

        return Pattern.compile(regex.toString());
    }
}
