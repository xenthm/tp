package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorListEmptyException;
import exceptions.AuthorNameTooLongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xenthm
class ViewAuthorsCommandTest {
    private static final Ui UI = new Ui();
    private AuthorList authorList;
    private final ViewAuthorsCommand commandUnderTest = new ViewAuthorsCommand();

    @BeforeEach
    public void setUp() {
        authorList = new AuthorList();
    }

    @Test
    public void viewAuthorsCommand_emptyAuthorList_throwsAuthorListEmptyException() {
        assertThrows(
            AuthorListEmptyException.class,
            () -> commandUnderTest.execute(UI, authorList)
        );
    }

    @Test
    public void viewAuthorsCommand_containsAuthorWhoseNameIsTooLong_throwsAuthorNameTooLongException() {
        authorList.add(new Author("IAmAnAuthorWithAReallyLongNameLongerThanEvenTheGreatWallOfChina"));
        assertThrows(
                AuthorNameTooLongException.class,
                () -> commandUnderTest.execute(UI, authorList)
        );
    }

    @Test
    public void viewAuthorsCommand_validAuthorName_doesNotThrowAnException() {
        authorList.add(new Author("IAmAValidAuthor"));
        assertDoesNotThrow(() -> commandUnderTest.execute(UI, authorList));
    }
}
