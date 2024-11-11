package ui;

import author.Author;
import author.AuthorList;
import manga.Manga;

import java.net.URISyntaxException;
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
     * Prints the header with the correct number of dividers.
     *
     * @param columns the print configuration for the columns of data to be printed
     * @param <T>     the type of the constituents of the rows to be printed
     */
    private static <T> void printHeaders(ArrayList<PrintColumn<T>> columns) {
        for (int i = 0; i < columns.size(); i++) {
            System.out.print(columns.get(i).getHeader());
            if (i < columns.size() - 1) {
                System.out.print(DIVIDER_REGEX);
            }
        }
        System.out.println();
    }

    //@@author xenthm
    /**
     * Prints the header horizontal divider line with the correct width.
     *
     * @param width width of the divider line to print
     */
    private static void printHorizontalDivider(int width) {
        System.out.println("-".repeat(width));
    }

    //@@author xenthm
    /**
     * Prints all the requested columns of each entry in the list with the correct number of dividers.
     *
     * @param arrayList the list (which must extend {@code ArrayList}) to be printed
     * @param columns   the print configuration for the columns of data to be printed
     * @param <T>       the type of the constituents of {@code arrayList}
     */
    private static <T> void printEntries(ArrayList<T> arrayList, ArrayList<PrintColumn<T>> columns) {
        for (int i = 0; i < arrayList.size(); i++) {
            T item = arrayList.get(i);

            // Print the row number
            System.out.printf("%3s", i + 1);    // Align right

            // Print each column for the entry
            for (int j = 0; j < columns.size(); j++) {
                System.out.print(columns.get(j).getValue(item));
                if (j < columns.size() - 1) {
                    System.out.print(DIVIDER_REGEX);
                }
            }
            System.out.println();
        }
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
        int totalWidthOfTable = columns
                .stream()
                .mapToInt(PrintColumn::getWidth)                    // get width of each column
                .sum()                                              // add them together
                + (columns.size() - 1) * DIVIDER_REGEX.length();    // add each divider that is added

        printHeaders(columns);
        printHorizontalDivider(totalWidthOfTable);
        printEntries(arrayList, columns);
    }

    //@@author
    public static void printString(String string) {
        System.out.println(string);
    }


    public void printAddAuthorSuccessMessage(Author incomingAuthor) {
        System.out.printf("Successfully added author: %s\n", incomingAuthor.getAuthorName());
    }

    public void printAddMangaSuccessMessage(Manga incomingManga) {
        System.out.printf("Manga %s added successfully to author %s\n",
                incomingManga.getMangaName(), incomingManga.getAuthor().getAuthorName());
    }

    public void printDeleteAuthorSuccessMessage(Author deletingAuthor) {
        System.out.println("Bye bye~");
        System.out.printf("Successfully deleted author: %s\n", deletingAuthor.getAuthorName());
    }

    public void printDeleteMangaSuccessMessage(Manga deletingManga) {
        System.out.printf("Manga %s successfully deleted from author %s\n",
                deletingManga.getMangaName(), deletingManga.getAuthor().getAuthorName());
    }

    public void printAddDeadlineSuccessMessage(Manga incomingManga) {
        System.out.printf("Deadline %s added successfully to manga %s\n",
                incomingManga.getDeadline(), incomingManga.getMangaName());
    }

    public void printAddSalesDataSuccessMessage(Manga incomingManga) {
        System.out.printf("Sales data added for %s %s\n", incomingManga.getMangaName(), incomingManga.getSalesData());
    }

    public void printPreAuthorListMessage(AuthorList authorList) {
        System.out.println("Here are the sla-I mean authors under you! Total: " + authorList.size());
    }

    public void printPreMangaListMessage(Author author) {
        System.out.println("Mangas authored by \"" + author.getAuthorName() + "\", Total: "
                + author.getMangaList().size());
    }

    public void printAccessLogFileFailureMessage() {
        System.out.println("Problems accessing log file!");
    }

    public static void printResolveURIFailureMessage(URISyntaxException e) {
        System.out.println("Cannot resolve URI of jar file!" + e.getMessage());
        System.out.println("Continuing with the working directory set to be the path you ran the jar file in.");
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printStorageDataRestoredMessage() {
        System.out.println("Data restored!");
    }

    public static void printStorageDataNotRestoredMessage() {
        System.out.println("Problems accessing file, data was not restored! Continuing with an empty list.");
    }

    public static void printMalformedJSONMessage() {
        System.out.println(
                "JSON from file is malformed, data was not restored! Continuing with an empty list."
        );
        System.out.println(
                "If you want to try and manually fix this, CTRL-C out of the program and check catalog.json!"
        );
    }

    public static void printCorruptedAuthorListMessage() {
        System.out.println(
                "Corrupted AuthorList object. Continuing with an empty list."
        );
    }

    public static void printCreateDataFileFailureMessage() {
        System.out.println("Problems creating data file, data will not be saved!");
    }

    public static void printSaveDataFileFailureMessage() {
        System.out.println("Problems saving file, data will not be saved!");
    }
}
