package author;

import manga.Manga;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {
    private final PrintStream STANDARD_OUT = System.out;
    private final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();

    private final Author AUTHOR_WITH_NO_MANGAS = new Author("AUTHOR_WITH_NO_MANGAS");

    private final Author AUTHOR_WITH_TWO_MANGAS = new Author("AUTHOR_WITH_TWO_MANGAS") {
        // instance initializer for anonymous subclass of Author
        {
            addManga(new Manga("manga1", this));
            addManga(new Manga("manga2", this));
        }
    };

    @Test
    public void printMangaListFromAuthor_twoMangas() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
        try {
            assertEquals(2, AUTHOR_WITH_TWO_MANGAS.getMangaList().size());
            AUTHOR_WITH_TWO_MANGAS.printMangaList();
            assertEquals("1. manga1 | Deadline: None" + System.lineSeparator()
                            + "2. manga2 | Deadline: None" + System.lineSeparator(),
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
