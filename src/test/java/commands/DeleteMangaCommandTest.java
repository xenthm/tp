package commands;

import author.Author;
import manga.Manga;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteMangaCommandTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void deleteOneManga() {
        try {
            Author author = new Author("test");
            Manga manga = new Manga("test", author);
            author.addManga(manga);
            assertEquals(1, author.getMangaList().size());
            author.deleteManga(manga);
            assertEquals(0, author.getMangaList().size());
        } finally {
            System.setOut(standardOut);
        }
    }

}
