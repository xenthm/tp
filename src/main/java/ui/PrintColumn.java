package ui;

import java.util.function.Function;

public class PrintColumn<T> {
    private final String header;
    private final int width;
    private final Function<T, String> valueProvider;

    public PrintColumn(String header, int width, Function<T, String> valueProvider) {
        this.header = header;
        this.width = width;
        this.valueProvider = valueProvider;
    }

    public String getHeader() {
        return String.format("%-" + width + "s", header);   // Align left
    }

    public String getValue(T object) {
        return valueProvider == null
                ? ""
                : String.format("%-" + width + "s", valueProvider.apply(object));   // Align left
    }

    public int getWidth() {
        return width;
    }
}
