package author;

import manga.Manga;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthorTest {
    private final PrintStream STANDARD_OUT = System.out;
    private final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();

    private Manga manga2;

    private final Author AUTHOR_WITH_NO_MANGAS = new Author("AUTHOR_WITH_NO_MANGAS");

    private final Author AUTHOR_WITH_THREE_MANGAS = new Author("AUTHOR_WITH_THREE_MANGAS") {
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
        assertEquals(manga2, AUTHOR_WITH_THREE_MANGAS.getManga("manga2"));
    }

    @Test
    public void getManga_threeMangas_returnsNullIfNotFound() {
        assertNull(AUTHOR_WITH_NO_MANGAS.getManga("mangaThatDoesntExist"));
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
        assertNotSame(AUTHOR_WITH_NO_MANGAS, AUTHOR_WITH_THREE_MANGAS);
        assertNotEquals(AUTHOR_WITH_NO_MANGAS, AUTHOR_WITH_THREE_MANGAS);
    }

    @Test
    public void printMangaListFromAuthor_threeMangas() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
        try {
            assertEquals(3, AUTHOR_WITH_THREE_MANGAS.getMangaList().size());
            AUTHOR_WITH_THREE_MANGAS.printMangaList();
            assertEquals("1. manga1 | Deadline: None" + System.lineSeparator()
                            + "2. manga2 | Deadline: None" + System.lineSeparator()
                            + "3. manga3 | Deadline: None" + System.lineSeparator(),
                    OUTPUT_STREAM_CAPTOR.toString());
        } finally {
            System.setOut(STANDARD_OUT);
        }
    }

    @Test
    public void printMangaListFromAuthor_noMangas() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
        try {
            assertEquals(0, AUTHOR_WITH_NO_MANGAS.getMangaList().size());
            AUTHOR_WITH_NO_MANGAS.printMangaList();
            assertEquals("", OUTPUT_STREAM_CAPTOR.toString());
        } finally {
            System.setOut(STANDARD_OUT);
        }
    }
}
