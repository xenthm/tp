package author;

import org.junit.jupiter.api.Test;
import ui.PrintColumn;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

//@@author xenthm
public class AuthorListTest {
    private final Author author2 = new Author("author2");

    private final AuthorList authorlistWithThreeAuthors = new AuthorList() {
        // instance initializer for anonymous subclass of AuthorList
        {
            add(new Author("author1"));
            add(author2);
            add(new Author("author3"));
        }
    };

    @Test
    public void hasAuthor_withString_falseIfNotFound() {
        assertFalse(authorlistWithThreeAuthors.hasAuthor("authorThatDoesntExist"));
    }

    @Test
    public void hasAuthor_withString_trueIfFound() {
        assertTrue(authorlistWithThreeAuthors.hasAuthor("author2"));
    }

    @Test
    public void getAuthor_withAuthor_returnsNullIfNotFound() {
        assertNull(authorlistWithThreeAuthors.getAuthor(new Author("authorThatDoesntExist")));
    }

    @Test
    public void getAuthor_withAuthor_returnsAuthorIfFound() {
        assertEquals(new Author("author2"), authorlistWithThreeAuthors.getAuthor(author2));
    }

    @Test
    public void getAuthor_withString_returnsNullIfNotFound() {
        assertNull(authorlistWithThreeAuthors.getAuthor("authorThatDoesntExist"));
    }

    @Test
    public void getAuthor_withString_returnsAuthorIfFound() {
        assertEquals(author2, authorlistWithThreeAuthors.getAuthor("author2"));
    }

    @Test
    public void authorColumnsToPrint_containsAllColumns() {
        ArrayList<PrintColumn<Author>> columns = AuthorList.authorColumnsToPrint();
        assertEquals(2, columns.size());
        assertEquals("no.", columns.get(0).getHeader());
        assertEquals("Author Name", columns.get(1).getHeader().trim());
    }
}
