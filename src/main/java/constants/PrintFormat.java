package constants;

import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static constants.Options.MAX_DEADLINE_LENGTH;
import static constants.Options.MAX_MANGA_NAME_LENGTH;
import static constants.Options.MAX_QUANTITY_VALUE;
import static constants.Options.MAX_UNIT_PRICE_VALUE;

public class PrintFormat {
    public static final String DATA_NOT_AVAILABLE = "N/A";

    public static final String NUMBER_COLUMN_HEADER = "no.";
    public static final int NUMBER_COLUMN_WIDTH = 3;

    public static final String AUTHOR_NAME_COLUMN_HEADER = "Author Name";
    public static final int AUTHOR_NAME_COLUMN_WIDTH = MAX_AUTHOR_NAME_LENGTH;

    public static final String MANGA_NAME_COLUMN_HEADER = "Manga Name";
    public static final int MANGA_NAME_COLUMN_WIDTH = MAX_MANGA_NAME_LENGTH;

    public static final String DEADLINE_COLUMN_HEADER = "Deadline";
    public static final int DEADLINE_COLUMN_WIDTH = MAX_DEADLINE_LENGTH;

    public static final String UNIT_PRICE_COLUMN_HEADER = "Unit Price";
    public static final int UNIT_PRICE_COLUMN_WIDTH = String.valueOf(MAX_UNIT_PRICE_VALUE).length();

    public static final String UNITS_SOLD_COLUMN_HEADER = "Units Sold";
    public static final int UNITS_SOLD_COLUMN_WIDTH = String.valueOf(MAX_QUANTITY_VALUE).length();

    public static final String REVENUE_COLUMN_HEADER = "Revenue";
    public static final int REVENUE_COLUMN_WIDTH = UNIT_PRICE_COLUMN_WIDTH + UNITS_SOLD_COLUMN_WIDTH;
}
