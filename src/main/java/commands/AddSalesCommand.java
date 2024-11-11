package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import manga.Manga;
import sales.Sale;
import ui.Ui;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.MANGA_INDEX;
import static constants.Command.PRICE_INDEX;
import static constants.Command.QUANTITY_INDEX;
import static constants.Command.SALES_COMMAND;
import static storage.StorageHelper.saveFile;

//@@author sarahchow03
/**
 * Represents the <code>Sale</code> command that handles adding sales data to a manga. This command validates the user
 * input, checks if the author and manga exist, and adds sales data (quantity sold and unit price) to the specified
 * manga.
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

        CommandValidator.ensureValidAuthorName(authorName);
        CommandValidator.ensureValidMangaName(mangaName);
        CommandValidator.ensureValidSalesData(quantityString, priceString);

        Integer quantitySold = Integer.parseInt(quantityString);
        Double unitPrice = Double.parseDouble(priceString);

        //@@author sarahchow03
        Sale salesData = new Sale(quantitySold, unitPrice);
        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        CommandValidator.ensureAuthorExists(incomingAuthor.getAuthorName(), authorList);
        Author existingAuthor = authorList.getAuthor(incomingAuthor);

        CommandValidator.ensureMangaExists(incomingManga.getMangaName(), existingAuthor);

        existingAuthor.getManga(incomingManga.getMangaName()).addSalesData(salesData);

        ui.printAddSalesDataSuccessMessage(existingAuthor.getManga(incomingManga.getMangaName()));
        saveFile(authorList);
    }
}
