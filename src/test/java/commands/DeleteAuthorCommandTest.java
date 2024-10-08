package commands;

import author.Author;
import author.AuthorList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteAuthorCommandTest {
    private final PrintStream standardOut = System.out;
    private AuthorList authorList;

    @BeforeEach
    public void setUp() {
        authorList = new AuthorList();
    }

    @Test
    public void deleteOneAuthor() {
        try {
            Author author = new Author("test1");
            authorList.addAuthor(author);
            assertEquals(1, authorList.getAuthorList().size());
            authorList.deleteAuthor(author);
            assertEquals(0, authorList.getAuthorList().size());
        } finally {
            System.setOut(standardOut);
        }
    }
}
