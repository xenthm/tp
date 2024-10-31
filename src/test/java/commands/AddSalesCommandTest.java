package commands;

import author.Author;
import author.AuthorList;
import exceptions.NoAuthorProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.PriceTooLargeException;
import exceptions.QuantityTooLargeException;
import exceptions.TantouException;
import manga.Manga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sales.Sale;
import ui.Ui;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author sarahchow03
class AddSalesCommandTest {
    private final PrintStream standardOut = System.out;
    private AuthorList authorList;
    private Ui ui;
    private Author existingAuthor;
    private AddSalesCommand commandUnderTest;

    @BeforeEach
    public void setUp() {
        authorList = new AuthorList();
        ui = new Ui();
        existingAuthor = new Author("test author");
        Manga existingManga = new Manga("test manga", existingAuthor);
        existingAuthor.addManga(existingManga);
        authorList.add(existingAuthor);
    }

    @Test
    public void execute_validInput_addSalesDataSuccessfully() {
        try {
            String[] userInputList = {"test author", "test manga", "10", "11.90"};
            commandUnderTest = new AddSalesCommand(userInputList);
            commandUnderTest.execute(ui, authorList);
            Sale sale = existingAuthor.getManga("test manga").getSalesData();
            assertEquals(10, sale.getQuantitySold());
            assertEquals(11.90, sale.getUnitPrice());
        } catch (TantouException e) {
            fail();
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void execute_negativeQuantity_cannotBeLessThanZeroExceptionThrown() {
        try {
            String[] userInputList = {"test author", "test manga", "-20", "10.90"};
            commandUnderTest = new AddSalesCommand(
                    userInputList
            );
            Exception exception = assertThrows(TantouException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

            assertEquals("Quantity sold cannot be less than 0!", exception.getMessage());
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void execute_negativeUnitPrice_cannotBeLessThanZeroExceptionThrown() {
        try {
            String[] userInputList = {"test author", "test manga", "20", "-10.90"};
            commandUnderTest = new AddSalesCommand(
                    userInputList
            );
            Exception exception = assertThrows(TantouException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

            assertEquals("Unit price cannot be less than 0!", exception.getMessage());
        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void execute_quantityTooLarge_exceptionThrown() {
        try {
            String[] userInputList = {"test", "test", "1000000000", "10.90"};
            commandUnderTest = new AddSalesCommand(
                    userInputList
            );
            Exception exception = assertThrows(QuantityTooLargeException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void execute_priceTooLarge_exceptionThrown() {
        try {
            String[] userInputList = {"test", "test", "10", "9999999999"};
            commandUnderTest = new AddSalesCommand(
                    userInputList
            );
            Exception exception = assertThrows(PriceTooLargeException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void execute_authorNameTooLong_exceptionThrown() {
        try {
            String[] userInputList = {"testttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt",
                    "test", "10", "10.90"};
            commandUnderTest = new AddSalesCommand(
                    userInputList
            );
            Exception exception = assertThrows(AuthorNameTooLongException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

        } finally {
            System.setOut(standardOut);
        }
    }

    @Test
    public void execute_mangaNameTooLong_exceptionThrown() {
        try {
            String[] userInputList = {"test",
                    "testttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt",
                    "10", "10.90"};
            commandUnderTest = new AddSalesCommand(
                    userInputList
            );
            Exception exception = assertThrows(MangaNameTooLongException.class, () -> {
                commandUnderTest.execute(ui, authorList);
            });

        } finally {
            System.setOut(standardOut);
        }
    }


}
