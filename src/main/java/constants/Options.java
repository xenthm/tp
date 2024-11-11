package constants;

public class Options {
    public static final String AUTHOR_OPTION = "-a";
    public static final int MAX_AUTHOR_NAME_LENGTH = 40;

    public static final String MANGA_OPTION = "-m";
    public static final int MAX_MANGA_NAME_LENGTH = 40;

    public static final String BY_DATE_OPTION = "-b";
    public static final int MAX_DEADLINE_LENGTH = 20;

    public static final String DELETE_OPTION = "-d";
    public static final String SALES_OPTION = "-s";

    public static final String QUANTITY_OPTION = "-q";
    public static final int MAX_QUANTITY_VALUE = 1000000000;

    public static final String PRICE_OPTION = "-p";
    public static final int MAX_UNIT_PRICE_VALUE = 1000000000;

    public static final String[] OPTIONS_ARRAY = {
        AUTHOR_OPTION,
        MANGA_OPTION,
        BY_DATE_OPTION,
        DELETE_OPTION,
        SALES_OPTION,
        QUANTITY_OPTION,
        PRICE_OPTION
    };
}
