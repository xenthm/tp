package author;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthorListTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final Author author2 = new Author("author2");

    private final AuthorList authorlistWithNoAuthors = new AuthorList();

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
    public void printAuthorList_noAuthors() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            assertEquals(0, authorlistWithNoAuthors.size());
            authorlistWithNoAuthors.print();
            assertEquals("Here are the sla-I mean authors under you! Total: 0" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void printAuthorList_threeAuthors() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            assertEquals(3, authorlistWithThreeAuthors.size());
            authorlistWithThreeAuthors.print();
            assertEquals("Here are the sla-I mean authors under you! Total: 3" + System.lineSeparator() +
                            "1. author1" + System.lineSeparator() +
                            "2. author2" + System.lineSeparator() +
                            "3. author3" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }
}
