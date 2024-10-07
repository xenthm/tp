package author;

import manga.Manga;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void printMangaListFromAuthor_twoMangas() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            Author author = new Author("test1");
            author.addManga(new Manga("manga1", author));
            author.addManga(new Manga("manga2", author));
            assertEquals(2, author.getMangaList().size());
            author.printMangaList();
            assertEquals("1. manga1" + System.lineSeparator() + "2. manga2" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void printMangaListFromAuthor_noMangas() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            Author author = new Author("test2");
            assertEquals(0, author.getMangaList().size());
            author.printMangaList();
            assertEquals("",  outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }
}
