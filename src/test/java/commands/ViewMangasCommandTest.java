package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorDoesNotExistException;
import exceptions.AuthorListEmptyException;
import exceptions.AuthorNameTooLongException;
import exceptions.MangaListEmptyException;
import exceptions.MangaNameTooLongException;
import exceptions.NoAuthorProvidedException;
import manga.Manga;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xenthm
class ViewMangasCommandTest {
    private static final Ui UI = new Ui();
    private static final AuthorList AUTHOR_LIST = new AuthorList();
    private static final Author VALID_AUTHOR = new Author("IAmAValidAuthor");
    private ViewMangasCommand commandUnderTest;

    @BeforeAll
    public static void setup() {
        AUTHOR_LIST.add(VALID_AUTHOR);
    }

    @Test
    public void viewMangasCommand_emptyAuthorList_throwsAuthorListEmptyException() {
        commandUnderTest = new ViewMangasCommand(
                "test",
                false,
                false
        );
        assertThrows(
            AuthorListEmptyException.class,
            () -> commandUnderTest.execute(UI, new AuthorList())
        );
    }

    @Test
    public void viewMangasCommand_containsAuthorWhoseNameIsTooLong_throwsAuthorNameTooLongException() {
        Author longNameAuthor = new Author(
                "IAmAnAuthorWithAReallyLongNameLongerThanEvenTheGreatWallOfChina"
        );
        AUTHOR_LIST.add(longNameAuthor);
        commandUnderTest = new ViewMangasCommand(
                "IAmAnAuthorWithAReallyLongNameLongerThanEvenTheGreatWallOfChina",
                false,
                false
        );
        assertThrows(
                AuthorNameTooLongException.class,
                () -> commandUnderTest.execute(UI, AUTHOR_LIST)
        );
    }

    @Test
    public void viewMangasCommand_noAuthorNameProvided_throwsNoAuthorProvidedException() {
        commandUnderTest = new ViewMangasCommand(
                "",
                false,
                false
        );
        assertThrows(
                NoAuthorProvidedException.class,
                () -> commandUnderTest.execute(UI, AUTHOR_LIST)
        );
    }

    @Test
    public void viewMangasCommand_authorDoesNotExist_throwsAuthorDoesNotExistException() {
        commandUnderTest = new ViewMangasCommand(
                "ThisAuthorDoesntExist",
                false,
                false
        );
        assertThrows(
                AuthorDoesNotExistException.class,
                () -> commandUnderTest.execute(UI, AUTHOR_LIST)
        );
    }

    @Test
    public void viewMangasCommand_emptyMangaList_throwsMangaListEmptyException() {
        commandUnderTest = new ViewMangasCommand(
                "IAmAValidAuthor",
                false,
                false
        );
        assertThrows(
                MangaListEmptyException.class,
                () -> commandUnderTest.execute(UI, AUTHOR_LIST)
        );
    }

    @Test
    public void viewMangasCommand_containsMangaWhoseNameIsTooLong_throwsMangaNameTooLongException() {
        Manga longNameManga = new Manga(
                "IAmAMangaWithAReallyLongNameLongerThanEvenTheGreatWallOfChina",
                VALID_AUTHOR
        );
        AUTHOR_LIST.getAuthor(VALID_AUTHOR).addManga(longNameManga);
        commandUnderTest = new ViewMangasCommand(
                "IAmAValidAuthor",
                false,
                false
        );
        assertThrows(
                MangaNameTooLongException.class,
                () -> commandUnderTest.execute(UI, AUTHOR_LIST)
        );
        AUTHOR_LIST.getAuthor(VALID_AUTHOR).deleteManga(longNameManga);
    }

    @Test
    public void viewMangasCommand_validAuthorName_doesNotThrowAnException() {
        Manga validManga = new Manga("IAmAValidManga", VALID_AUTHOR);
        AUTHOR_LIST.getAuthor(VALID_AUTHOR).addManga(validManga);
        commandUnderTest = new ViewMangasCommand(
                "IAmAValidAuthor",
                false,
                false
        );
        assertDoesNotThrow(() -> commandUnderTest.execute(UI, AUTHOR_LIST));
        AUTHOR_LIST.getAuthor(VALID_AUTHOR).deleteManga(validManga);
    }

    @AfterAll
    public static void teardown() {
        AUTHOR_LIST.remove(VALID_AUTHOR);
    }
}
