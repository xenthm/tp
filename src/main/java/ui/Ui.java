package ui;

import java.util.ArrayList;
import java.util.Scanner;

import static constants.Message.EXIT_MESSAGE;
import static constants.Message.GREETING_MESSAGE;
import static constants.Regex.DIVIDER_REGEX;

public class Ui {
    private final Scanner in = new Scanner(System.in);

    public void greetUser() {
        System.out.println(GREETING_MESSAGE);
    }

    public void sayGoodbye() {
        System.out.println(EXIT_MESSAGE);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    //@@author xenthm
    /**
     * Prints the contents of the provided list with the given column print configuration after formatting.
     *
     * @param arrayList the list (which must extend {@code ArrayList}) to be printed
     * @param columns   the print configuration for the columns of data to be printed
     * @param <T>       the type of the constituents of {@code arrayList}
     */
    public static <T> void printList(ArrayList<T> arrayList, ArrayList<PrintColumn<T>> columns) {
        // Print the header
        for (int i = 0; i < columns.size(); i++) {
            System.out.print(columns.get(i).getHeader());
            if (i < columns.size() - 1) {
                System.out.print(DIVIDER_REGEX);
            }
        }
        System.out.println();

        // Print the top line based on total width
        int totalWidth = columns
                .stream()
                .mapToInt(PrintColumn::getWidth)
                .sum()
                + (columns.size() - 1) * DIVIDER_REGEX.length();
        System.out.println("-".repeat(totalWidth));

        // Print each entry
        for (int i = 0; i < arrayList.size(); i++) {
            T item = arrayList.get(i);
            // Display the row number and each column's value
            System.out.printf("%3s", i + 1);    // Align right
            for (int j = 0; j < columns.size(); j++) {
                System.out.print(columns.get(j).getValue(item));
                if (j < columns.size() - 1) {
                    System.out.print(DIVIDER_REGEX);
                }
            }
            System.out.println();
        }
    }
}
