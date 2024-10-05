package author;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorListTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void print_twoAuthors() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            AuthorList authorList = new AuthorList();
            authorList.addAuthor(new Author("test1"));
            authorList.addAuthor(new Author("test2"));
            assertEquals(2, authorList.size());
            authorList.print();
            assertEquals("Here are the sla-I mean authors under you! Total: 2" + System.lineSeparator() +
                            "1. test1" + System.lineSeparator() +
                            "2. test2" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }
}
