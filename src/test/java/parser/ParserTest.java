package parser;

import commands.AddAuthorCommand;
import commands.AddMangaCommand;
import commands.AddSalesCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteAuthorCommand;
import commands.DeleteMangaCommand;
import commands.ViewAuthorsCommand;
import commands.ViewMangasCommand;
import exceptions.NoAuthorProvidedException;
import exceptions.SalesArgsWrongOrderException;
import exceptions.TantouException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author xenthm
public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_byeTantou_parsedCorrectly() {
        parseInputAssertCommandType("bye", ByeCommand.class);
    }

    //@@author xenthm
    @Test
    public void getUserCommand_viewAuthors_parsedCorrectly() {
        parseInputAssertCommandType("view", ViewAuthorsCommand.class);
    }

    @Test
    public void getUserCommand_viewMangas_parsedCorrectly() {
        parseInputAssertCommandType("view -a test", ViewMangasCommand.class);
    }

    //@@author sarahchow03
    @Test
    public void getUserCommand_deleteAuthor_parsedCorrectly() {
        parseInputAssertCommandType("catalog -a test -d", DeleteAuthorCommand.class);
    }

    @Test
    public void getUserCommand_deleteManga_parsedCorrectly() {
        parseInputAssertCommandType("catalog -a test -m test -d", DeleteMangaCommand.class);
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_addAuthor_parsedCorrectly() {
        parseInputAssertCommandType("catalog -a test", AddAuthorCommand.class);
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_addManga_parsedCorrectly() {
        parseInputAssertCommandType("catalog -a test -m test", AddMangaCommand.class);
    }

    //@@author xenthm
    private void parseInputAssertCommandType(String input, Class<? extends Command> expectedClass) {
        try {
            Command actual = parser.getUserCommand(input);
            assertEquals(actual.getClass(), expectedClass);
        } catch (TantouException e) {
            // Should not reach here as the input should be valid for testing
            fail();
        }
    }

    //@@author sarahchow03
    @Test
    public void getUserCommand_addSales_parsedCorrectly() {
        parseInputAssertCommandType("sales -a test -m test -q 20 -p 10.90", AddSalesCommand.class);
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_emptyUserInput_exceptionThrown() {
        String userInput = "";
        Exception exception = assertThrows(TantouException.class, () -> {
            parser.getUserCommand(userInput);
        });

        assertEquals("Hey! Say something!", exception.getMessage());
    }

    // Negative Cases for Catalog
    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogNoFlagsProvided_exceptionThrown() {
        // Within the processing of Parser, it will catch that no relevant arguments has been provided
        String userInput = "catalog";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogNoAuthorArgument_exceptionThrown() {
        String userInput = "catalog -a";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogNoAuthorArgumentNoMangaArgument_exceptionThrown() {
        String userInput = "catalog -a -m";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogNoAuthorArgumentHasMangaArgument_exceptionThrown() {
        String userInput = "catalog -a -m Bleach";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogDeleteNoArgument_exceptionThrown() {
        String userInput = "catalog -d";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogDeleteNoAuthorArgument_exceptionThrown() {
        String userInput = "catalog -a -d";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogDeleteNoAuthorArgumentNoMangaArgument_exceptionThrown() {
        String userInput = "catalog -a -m -d";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author averageandyyy
    @Test
    public void getUserCommand_catalogDeleteRandomPosition_exceptionThrown() {
        String userInput = "catalog -a -d -m ";
        Exception exception = assertThrows(NoAuthorProvidedException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }

    //@@author sarahchow03
    // Negative cases for sales command
    @Test
    public void getUserCommand_salesArgumentWrongOrder_exceptionThrown() {
        String userInput = "sales -a test -m test -p 10.90 -q 11";
        Exception exception = assertThrows(SalesArgsWrongOrderException.class, () -> {
            parser.getUserCommand(userInput);
        });
    }
}
