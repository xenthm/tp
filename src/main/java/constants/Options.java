package constants;

public class Options {
    public static final String AUTHOR_OPTION = "-a";
    public static final String AUTHOR_LONG_OPTION = "author";
    public static final String AUTHOR_OPTION_DESC = "Author name";
    public static final int MAX_AUTHOR_NAME_LENGTH = 40;

    public static final String MANGA_OPTION = "-m";
    public static final String MANGA_LONG_OPTION = "manga";
    public static final String MANGA_OPTION_DESC = "Manga name";
    public static final int MAX_MANGA_NAME_LENGTH = 40;

    public static final String BY_DATE_OPTION = "-b";
    public static final String BY_DATE_LONG_OPTION = "by";
    public static final String BY_DATE_OPTION_DESC = "Deadline due date";

    public static final String DELETE_OPTION = "-d";
    public static final String DELETE_LONG_OPTION = "delete";
    public static final String DELETE_OPTION_DESC = "To delete";

    public static final String SALES_OPTION = "-s";
    public static final String SALES_LONG_OPTION = "sales";
    public static final String SALES_OPTION_DESC = "Sales data";

    public static final String QUANTITY_OPTION = "-q";
    public static final String QUANTITY_LONG_OPTION = "quantity";
    public static final String QUANTITY_OPTION_DESC = "Quantity of copies sold";

    public static final String PRICE_OPTION = "-p";
    public static final String PRICE_LONG_OPTION = "price";
    public static final String PRICE_OPTION_DESC = "Unit price per copy";

    public static final String REQUIRE_ARGS_TRUE = "true";
    public static final String REQUIRE_ARGS_FALSE = "false";

    public static final String[] OPTIONS_ARRAY = {
//            {AUTHOR_OPTION, REQUIRE_ARGS_TRUE, AUTHOR_LONG_OPTION, AUTHOR_OPTION_DESC},
//            {MANGA_OPTION, REQUIRE_ARGS_TRUE, MANGA_LONG_OPTION, MANGA_OPTION_DESC},
//            {BY_DATE_OPTION, REQUIRE_ARGS_FALSE, BY_DATE_LONG_OPTION, BY_DATE_OPTION_DESC},
//            {DELETE_OPTION, REQUIRE_ARGS_FALSE, DELETE_LONG_OPTION, DELETE_OPTION_DESC},
//            {SALES_OPTION, REQUIRE_ARGS_FALSE, SALES_LONG_OPTION, SALES_OPTION_DESC},
//            {QUANTITY_OPTION, REQUIRE_ARGS_TRUE, QUANTITY_LONG_OPTION, QUANTITY_OPTION_DESC},
//            {PRICE_OPTION, REQUIRE_ARGS_TRUE, PRICE_LONG_OPTION, PRICE_OPTION_DESC},
            AUTHOR_OPTION,
            MANGA_OPTION,
            BY_DATE_OPTION,
            DELETE_OPTION,
            SALES_OPTION,
            QUANTITY_OPTION,
            PRICE_OPTION
    };

    public static final int SHORT_OPTION_INDEX = 0;
    public static final int REQUIRE_ARGS_INDEX = 1;
    public static final int LONG_OPTION_INDEX = 2;
    public static final int OPTION_DESC_INDEX = 3;
}
