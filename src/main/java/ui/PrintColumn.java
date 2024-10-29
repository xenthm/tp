package ui;

import java.util.function.Function;

/**
 * Represents a column of data to be printed via the {@code view} command. Contains information on what should be
 * printed as the header of the table column, the character width of the column, and the getter method belonging to
 * class {@code <T>} that returns the {@code String} representation of its attributes (e.g. {@code getAuthorName()},
 * {@code getTotalRevenue()}, {@code getDeadline()}, etc.).
 * <p>
 * Also formats the text to be printed to align with the columns.
 *
 * @param <T> the type of object whose attributes are to be printed as data in columns
 *            <p>(e.g. {@code Author} with {@code authorName}, or {@code Manga} with {@code mangaName},
 *            {@code deadline}, and {@code salesData})
 */
public class PrintColumn<T> {
    private final String header;
    private final int width;
    private final Function<T, String> valueProvider;

    public PrintColumn(String header, int width, Function<T, String> valueProvider) {
        this.header = header;
        this.width = width;
        this.valueProvider = valueProvider;
    }

    /**
     * Returns the formatted header {@code String}.
     */
    public String getHeader() {
        return String.format("%-" + width + "s", header);   // Align left
    }

    /**
     * Returns the formatted class attribute {@code String}.
     */
    public String getValue(T object) {
        return valueProvider == null
                ? ""
                : String.format("%-" + width + "s", valueProvider.apply(object));   // Align left
    }

    public int getWidth() {
        return width;
    }
}
