package commands;

import author.AuthorList;
import exceptions.TantouException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

//@@author iaso1774
public class AddDeadlineCommandTest {
    private final PrintStream standardOut = System.out;
    private AuthorList authorList;
    private Ui ui;
    private AddDeadlineCommand commandUnderTest;
    private AddMangaCommand addMangaCommand;

    @BeforeEach
    public void setUp() {
        authorList = new AuthorList();
        ui = new Ui();
    }

    @Test
    public void addDeadlineCommand_addSingleDeadline_deadlineMatchAuthorCountOne() {
        try {
            addMangaCommand = new AddMangaCommand("catalog -a \"Gege Akutami\"" +
                    " -m \"Jujutsu Kaisen\"");
            addMangaCommand.execute(ui, authorList);
            commandUnderTest = new AddDeadlineCommand("schedule -a \"Gege Akutami\" " +
                    "-m \"Jujutsu Kaisen\" -b \"September 29\"");
            commandUnderTest.execute(ui, authorList);
            assertEquals(1, authorList.size());
            assertEquals("September 29", authorList.getAuthor("Gege Akutami")
                    .getMangaList().get(0).getDeadline());
        } catch (TantouException e) {
            // The code should not fail at this point
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addDeadlineCommand_changeDeadline_deadlineMatchAuthorCountOne() {
        try {
            addMangaCommand = new AddMangaCommand("catalog -a \"Gege Akutami\"" +
                    " -m \"Jujutsu Kaisen\"");
            addMangaCommand.execute(ui, authorList);
            commandUnderTest = new AddDeadlineCommand("schedule -a \"Gege Akutami\" " +
                    "-m \"Jujutsu Kaisen\" -b \"October 29\"");
            commandUnderTest.execute(ui, authorList);
            assertEquals(1, authorList.size());
            assertEquals("October 29", authorList.getAuthor("Gege Akutami")
                    .getMangaList().get(0).getDeadline());
            commandUnderTest = new AddDeadlineCommand("schedule -a \"Gege Akutami\" " +
                    "-m \"Jujutsu Kaisen\" -b \"September 30\"");
            commandUnderTest.execute(ui, authorList);
            assertEquals(1, authorList.size());
            assertEquals("September 30", authorList.getAuthor("Gege Akutami")
                    .getMangaList().get(0).getDeadline());
        } catch (TantouException e) {
            // The code should not fail at this point
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void addDeadlineCommand_authorNonexistent_AuthorNotFoundExceptionThrown() {
        commandUnderTest = new AddDeadlineCommand("schedule -a \"Gege Akutami\" " +
                "-m \"Jujutsu Kaisen\" -b \"October 29\"");
        Exception exception = assertThrows(TantouException.class, () -> {
            commandUnderTest.execute(ui, authorList);
        });

        assertEquals("Author not found!", exception.getMessage());
        System.setOut(standardOut);
    }

    @Test
    public void addDeadlineCommand_mangaNonexistent_MangaNotFoundExceptionThrown() {
        try {
            AddAuthorCommand addAuthorCommand =
                    new AddAuthorCommand("catalog -a \"Gege Akutami\"");
            addAuthorCommand.execute(ui, authorList);

            commandUnderTest = new AddDeadlineCommand("schedule -a \"Gege Akutami\" " +
                    "-m \"Jujutsu Kaisen\" -b \"October 29\"");
            Exception exception = assertThrows(TantouException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

            assertEquals("Manga not found!", exception.getMessage());
        } catch (TantouException e) {
            // The code should not fail at this point
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }
}
