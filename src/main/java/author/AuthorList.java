package author;

import ui.PrintColumn;

import java.util.ArrayList;

import static constants.PrintFormat.AUTHOR_NAME_COLUMN_HEADER;
import static constants.PrintFormat.AUTHOR_NAME_COLUMN_WIDTH;
import static constants.PrintFormat.NUMBER_COLUMN_HEADER;
import static constants.PrintFormat.NUMBER_COLUMN_WIDTH;

/**
 * Represents a list of <code>Author</code>. Extends {@code ArrayList<Author>}
 */
public class AuthorList extends ArrayList<Author> {
    public boolean hasAuthor(Author author) {
        assert author != null : "author must not be null";
        return contains(author);
    }

    //@@author xenthm
    /**
     * Checks if the <code>authorList</code> contains an author instance with the name given by the parameter
     * <code>authorName</code> in <code>String</code> format.
     *
     * @param authorName the author's name in <code>String</code> format
     * @return <code>true</code> if the author was found, <code>false</code> otherwise
     */
    public boolean hasAuthor(String authorName) {
        assert !authorName.isEmpty() : "author name must not be empty";
        for (Author author : this) {
            if (author.getAuthorName().equals(authorName)) {
                return true;
            }
        }
        return false;
    }

    //@@author averageandyyy
    /**
     * Searches for existing author in {@code authorList} based on incoming author's name and returns {@code Author}
     * instance in {@code authorList}
     *
     * @param author The incoming author to search for based on name
     * @return {@code Author} instance in {@code authorList}, {@code null} if no such author can be found
     */
    public Author getAuthor(Author author) {
        assert author != null : "author must not be null";
        for (Author a : this) {
            if (a.equals(author)) {
                return a;
            }
        }

        return null;
    }

    //@@author xenthm
    /**
     * Searches for existing author in {@code authorList} based on incoming author's name (in <code>String</code>
     * format) and returns {@code Author} instance in {@code authorList}
     *
     * @param authorName the author's name in <code>String</code> format
     * @return {@code Author} instance in {@code authorList}, {@code null} if no such author can be found
     */
    public Author getAuthor(String authorName) {
        assert !authorName.isEmpty() : "author name must not be empty";
        for (Author a : this) {
            if (a.getAuthorName().equals(authorName)) {
                return a;
            }
        }
        return null;
    }

    //@@author xenthm
    /**
     * Specifies the column print configuration for the {@code view authors} command.
     *
     * @return {@code ArrayList<PrintColumn<Author>>}; a list of columns to be printed
     */
    public static ArrayList<PrintColumn<Author>> authorColumnsToPrint() {
        ArrayList<PrintColumn<Author>> columns = new ArrayList<>();

        columns.add(new PrintColumn<>(NUMBER_COLUMN_HEADER, NUMBER_COLUMN_WIDTH, null));
        columns.add(new PrintColumn<>(AUTHOR_NAME_COLUMN_HEADER, AUTHOR_NAME_COLUMN_WIDTH, Author::getAuthorName));

        return columns;
    }
}
