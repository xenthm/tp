package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorDoesNotExistException;
import exceptions.AuthorNameTooLongException;
import exceptions.MangaDoesNotExistException;
import exceptions.MangaNameTooLongException;
import exceptions.NoAuthorProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.NoPriceProvidedException;
import exceptions.NoQuantityProvidedException;
import exceptions.NumberLessThanZeroException;
import exceptions.PriceTooLargeException;
import exceptions.QuantityTooLargeException;
import exceptions.TantouException;
import manga.Manga;
import sales.Sale;
import ui.Ui;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.MANGA_INDEX;
import static constants.Command.PRICE_INDEX;
import static constants.Command.QUANTITY_INDEX;
import static constants.Command.SALES_COMMAND;
import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static constants.Options.MAX_MANGA_NAME_LENGTH;
import static constants.Options.QUANTITY_MAX_VALUE;
import static constants.Options.UNIT_PRICE_MAX_VALUE;
import static storage.StorageHelper.saveFile;

//@@author sarahchow03

/**
 * Represents the <code>sales</code> command that handles adding sales data to a manga.
 * This command validates the user input, checks if the author and manga exist,
 * and adds sales data (quantity sold and unit price) to the specified manga.
 */
public class AddSalesCommand extends Command {
    // Maximum allowed values for unit price and quantity
    private String[] argsAuthorMangaQtyPrice;

    public AddSalesCommand(String[] argsAuthorMangaQtyPrice) {
        super(SALES_COMMAND);
        this.argsAuthorMangaQtyPrice = argsAuthorMangaQtyPrice;
    }

    /**
     * Represents the <code>AddSalesCommand</code>, a command that handles the process of adding sales data
     * for a specified manga title. This command performs several validation steps to ensure that
     * all required input parameters are provided, that values are within acceptable limits, and that
     * the author and manga exist in the system. If all validations pass, it updates the sales data
     * for the specified manga.
     * <p>
     * The command expects input values for the author name, manga title, quantity sold, and unit price.
     * If any of these validations fail, a specific exception is thrown, providing detailed feedback
     * about the cause of the error.
     * </p>
     *
     * @param ui The user interface used for interacting with the user.
     * @param authorList The list of authors against which the existence of the author and manga is verified.
     * @throws TantouException if any validation check fails, such as missing or invalid input, non-existent
     *                         author or manga, or values exceeding maximum limits.
     */

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        String authorName = argsAuthorMangaQtyPrice[AUTHOR_INDEX];
        String mangaName = argsAuthorMangaQtyPrice[MANGA_INDEX];
        String quantityString = argsAuthorMangaQtyPrice[QUANTITY_INDEX];
        String priceString = argsAuthorMangaQtyPrice[PRICE_INDEX];

        if (authorName == null || authorName.isEmpty()) {
            throw new NoAuthorProvidedException();
        }

        if (mangaName == null || mangaName.isEmpty()) {
            throw new NoMangaProvidedException();
        }

        if (quantityString == null || quantityString.isEmpty()) {
            throw new NoQuantityProvidedException();
        }

        if (priceString == null || priceString.isEmpty()) {
            throw new NoPriceProvidedException();
        }

        Integer quantitySold = null;
        Double unitPrice = null;
        try {
            quantitySold = Integer.parseInt(quantityString);
            unitPrice = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            throw new TantouException("Pretty sure we taught you how to format those numbers already... Seriously...");
        }

        if (quantitySold >= QUANTITY_MAX_VALUE) {
            throw new QuantityTooLargeException();
        }

        if (quantitySold < 0) {
            throw new NumberLessThanZeroException();
        }

        if (unitPrice >= UNIT_PRICE_MAX_VALUE) {
            throw new PriceTooLargeException();
        }

        if (unitPrice < 0) {
            throw new NumberLessThanZeroException();
        }

        //@@author xenthm
        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Author name " + authorName + " exceeds maximum length");
            throw new AuthorNameTooLongException();
        }

        if (mangaName.length() > MAX_MANGA_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Manga name " + mangaName + " exceeds maximum length");
            throw new MangaNameTooLongException();
        }

        //@@author sarahchow03
        Sale salesData = new Sale(quantitySold, unitPrice);
        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        if (!authorList.hasAuthor(incomingAuthor)) {
            throw new AuthorDoesNotExistException(authorName);
        }

        Author existingAuthor = authorList.getAuthor(incomingAuthor);

        if (!existingAuthor.hasManga(incomingManga)) {
            throw new MangaDoesNotExistException(mangaName);
        }

        existingAuthor.getManga(incomingManga.getMangaName()).addSalesData(salesData);
        System.out.printf("Sales data added for %s %s\n", incomingManga.getMangaName(), salesData);
        saveFile(authorList);
    }
}
