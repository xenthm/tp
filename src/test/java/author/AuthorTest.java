package author;

import manga.Manga;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

//@@author xenthm
public class AuthorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Manga manga2;

    private final Author authorWithNoMangas = new Author("AUTHOR_WITH_NO_MANGAS");

    private final Author authorWithThreeMangas = new Author("AUTHOR_WITH_THREE_MANGAS") {
        // instance initializer for anonymous subclass of Author
        {
            addManga(new Manga("manga1", this));
            manga2 = new Manga("manga2", this);
            addManga(manga2);
            addManga(new Manga("manga3", this));
        }
    };

    @Test
    public void getManga_threeMangas_returnsMangaIfFound() {
        assertEquals(manga2, authorWithThreeMangas.getManga("manga2"));
    }

    @Test
    public void getManga_threeMangas_returnsNullIfNotFound() {
        assertNull(authorWithNoMangas.getManga("mangaThatDoesntExist"));
    }

    @Test
    public void equals_comparingNames_returnsTrueIfMatch() {
        Author author1 = new Author("author1");
        Author author2 = new Author("author1");
        assertNotSame(author1, author2);
        assertEquals(author1, author2);
    }

    @Test
    public void equals_comparingNames_returnsFalseIfNotMatch() {
        assertNotSame(authorWithNoMangas, authorWithThreeMangas);
        assertNotEquals(authorWithNoMangas, authorWithThreeMangas);
    }

    @Test
    public void printMangaListFromAuthor_threeMangas() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            assertEquals(3, authorWithThreeMangas.getMangaList().size());
            authorWithThreeMangas.printMangaList();
            assertEquals("1. manga1 | Deadline: None" + System.lineSeparator()
                            + "2. manga2 | Deadline: None" + System.lineSeparator()
                            + "3. manga3 | Deadline: None" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void printMangaListFromAuthor_noMangas() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            assertEquals(0, authorWithNoMangas.getMangaList().size());
            authorWithNoMangas.printMangaList();
            assertEquals("", outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }
}
