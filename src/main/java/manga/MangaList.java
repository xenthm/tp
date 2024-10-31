package manga;

import ui.PrintColumn;

import java.util.ArrayList;

import static constants.PrintFormat.DEADLINE_COLUMN_HEADER;
import static constants.PrintFormat.DEADLINE_COLUMN_WIDTH;
import static constants.PrintFormat.MANGA_NAME_COLUMN_HEADER;
import static constants.PrintFormat.MANGA_NAME_COLUMN_WIDTH;
import static constants.PrintFormat.NUMBER_COLUMN_HEADER;
import static constants.PrintFormat.NUMBER_COLUMN_WIDTH;
import static constants.PrintFormat.REVENUE_COLUMN_HEADER;
import static constants.PrintFormat.REVENUE_COLUMN_WIDTH;
import static constants.PrintFormat.UNITS_SOLD_COLUMN_HEADER;
import static constants.PrintFormat.UNITS_SOLD_COLUMN_WIDTH;
import static constants.PrintFormat.UNIT_PRICE_COLUMN_HEADER;
import static constants.PrintFormat.UNIT_PRICE_COLUMN_WIDTH;

//@@author xenthm
/**
 * Represents a list of <code>Manga</code>. Extends {@code ArrayList<Manga>}
 */
public class MangaList extends ArrayList<Manga> {
    /**
     * Specifies the column print configuration for the {@code view manga} command.
     *
     * @param includeDeadline specifies whether the {@code deadline} of each {@code Manga} should be printed
     * @param includeSales    specifies whether the {@code sales data} of each {@code Manga} should be printed
     * @return {@code ArrayList<PrintColumn<Manga>>}; a list of columns to be printed
     */
    public static ArrayList<PrintColumn<Manga>> mangaColumnsToPrint(boolean includeDeadline, boolean includeSales) {
        ArrayList<PrintColumn<Manga>> columns = new ArrayList<>();

        columns.add(new PrintColumn<>(NUMBER_COLUMN_HEADER, NUMBER_COLUMN_WIDTH, null));
        columns.add(new PrintColumn<>(MANGA_NAME_COLUMN_HEADER, MANGA_NAME_COLUMN_WIDTH, Manga::getMangaName));

        if (includeDeadline) {
            columns.add(new PrintColumn<>(DEADLINE_COLUMN_HEADER, DEADLINE_COLUMN_WIDTH, Manga::getDeadline));
        }

        if (includeSales) {
            columns.add(new PrintColumn<>(UNIT_PRICE_COLUMN_HEADER,
                    UNIT_PRICE_COLUMN_WIDTH,
                    manga -> manga.getSalesData().getUnitPriceAsString())
            );
            columns.add(new PrintColumn<>(UNITS_SOLD_COLUMN_HEADER,
                    UNITS_SOLD_COLUMN_WIDTH,
                    manga -> manga.getSalesData().getQuantitySoldAsString())
            );
            columns.add(new PrintColumn<>(REVENUE_COLUMN_HEADER,
                    REVENUE_COLUMN_WIDTH,
                    manga -> manga.getSalesData().getTotalRevenueAsString())
            );
        }

        return columns;
    }
}
