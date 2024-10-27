package manga;

import sales.Sale;
import ui.PrintColumn;

import java.util.ArrayList;

//@@author xenthm
/**
 * Represents a list of <code>Manga</code>. Extends {@code ArrayList<Manga>}
 */
public class MangaList extends ArrayList<Manga> {
    /**
     * Specifies the column print configuration for the {@code view manga} command.
     *
     * @param includeDeadline specifies whether the {@code deadline} of each {@code Manga} should be printed
     * @param includeSales specifies whether the {@code sales data} of each {@code Manga} should be printed
     * @return {@code ArrayList<PrintColumn<Manga>>}; a list of columns to be printed
     */
    public static ArrayList<PrintColumn<Manga>> mangaColumnsToPrint(boolean includeDeadline, boolean includeSales) {
        ArrayList<PrintColumn<Manga>> columns = new ArrayList<>();

        columns.add(new PrintColumn<>("no.", 3, null));
        columns.add(new PrintColumn<>("Manga Name", 40, Manga::getMangaName));

        if (includeDeadline) {
            columns.add(new PrintColumn<>("Deadline", 15, Manga::getDeadline));
        }

        if (includeSales) {
            columns.add(new PrintColumn<>("Unit Price", 10, manga -> {
                Sale salesData = manga.getSalesData();
                return salesData != null ? String.valueOf(salesData.getUnitPrice()) : "N/A";
            }));
            columns.add(new PrintColumn<>("Units Sold", 10, manga -> {
                Sale salesData = manga.getSalesData();
                return salesData != null ? String.valueOf(salesData.getQuantitySold()) : "N/A";
            }));
            columns.add(new PrintColumn<>("Revenue", 10, manga -> {
                Sale salesData = manga.getSalesData();
                return salesData != null ? String.valueOf(salesData.getTotalRevenue()) : "N/A";
            }));
        }

        return columns;
    }
}
