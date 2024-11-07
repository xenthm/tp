package commands;

import author.AuthorList;
import exceptions.NoAuthorProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.TantouException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author averageandyyy
public class AddMangaCommandTest {
    private final PrintStream standardOut = System.out;
    private AuthorList authorList;
    private Ui ui;
    private AddMangaCommand commandUnderTest;

    @BeforeEach
    public void setUp() {
        authorList = new AuthorList();
        ui = new Ui();
    }

    @Test
    public void addMangaCommand_addOneManga_authorCountOneMangaNameMatch() {
        try {
            String[] userInputList = {"Kubo Tite", "Bleach"};
            commandUnderTest = new AddMangaCommand(userInputList);
            commandUnderTest.execute(ui, authorList);
            assertEquals(1, authorList.size());
            assertEquals("Bleach", authorList.getAuthor("Kubo Tite").getMangaList().get(0).getMangaName());
        } catch (TantouException e) {
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addMangaCommand_addDuplicateManga_mangaExistsExceptionThrown() {
        try {
            String[] userInputList = {"Kubo Tite", "Bleach"};
            commandUnderTest = new AddMangaCommand(userInputList);
            commandUnderTest.execute(ui, authorList);
            Exception exception = assertThrows(TantouException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

            assertEquals("Manga exists!", exception.getMessage());
        } catch (TantouException e) {
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addMangaCommand_addMangaToExistingAuthor_mangaAddedSuccess() {
        try {
            String authorName = "Kubo Tite";
            Command addAuthor = new AddAuthorCommand(authorName);
            addAuthor.execute(ui, authorList);
            String[] mangaInputList = {"Kubo Tite", "Bleach"};
            commandUnderTest = new AddMangaCommand(mangaInputList);
            commandUnderTest.execute(ui, authorList);

            assertEquals(1, authorList.size());
            assertEquals("Bleach", authorList.getAuthor("Kubo Tite").getMangaList().get(0).getMangaName());
        } catch (TantouException e) {
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addMangaCommand_emptyAuthorName_noAuthorProvidedExceptionThrown() {
        try {
            String[] argsAuthorManga = {"", "Bleach"};
            commandUnderTest = new AddMangaCommand(argsAuthorManga);
            Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addMangaCommand_nullAuthorName_noAuthorProvidedExceptionThrown() {
        try {
            String[] argsAuthorManga = {null, "Bleach"};
            commandUnderTest = new AddMangaCommand(argsAuthorManga);
            Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addMangaCommand_emptyMangaName_noMangaProvidedExceptionThrown() {
        try {
            String[] argsAuthorManga = {"Kubo Tite", ""};
            commandUnderTest = new AddMangaCommand(argsAuthorManga);
            Exception exception = assertThrows(NoMangaProvidedException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addMangaCommand_nullMangaName_noMangaProvidedExceptionThrown() {
        try {
            String[] argsAuthorManga = {"Kubo Tite", null};
            commandUnderTest = new AddMangaCommand(argsAuthorManga);
            Exception exception = assertThrows(NoMangaProvidedException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });
        } finally {
            System.setOut(standardOut);
        }
    }
}
