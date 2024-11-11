package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorDoesNotExistException;
import exceptions.AuthorExistsException;
import exceptions.AuthorListEmptyException;
import exceptions.AuthorNameTooLongException;
import exceptions.DeadlineTooLongException;
import exceptions.MangaDoesNotExistException;
import exceptions.MangaExistsException;
import exceptions.MangaListEmptyException;
import exceptions.MangaNameTooLongException;
import exceptions.NoAuthorProvidedException;
import exceptions.NoDeadlineProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.NoPriceProvidedException;
import exceptions.NoQuantityProvidedException;
import exceptions.NumberLessThanZeroException;
import exceptions.PriceTooLargeException;
import exceptions.PriceWrongFormatException;
import exceptions.QuantityTooLargeException;
import exceptions.QuantityWrongFormatException;
import manga.Manga;
import manga.MangaList;

import static commands.Command.COMMAND_LOGGER;
import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static constants.Options.MAX_DEADLINE_LENGTH;
import static constants.Options.MAX_MANGA_NAME_LENGTH;
import static constants.Options.MAX_QUANTITY_VALUE;
import static constants.Options.MAX_UNIT_PRICE_VALUE;

//@@author xenthm
/**
 * This utility class contains methods to check the various inputs to the defined <code>Command</code>s in
 * <code>MangaTantou</code>, throwing relevant exceptions if needed.
 */
public class CommandValidator {
    /**
     * Checks if the author name is <code>null</code> or is empty, then if it is longer than
     * <code>MAX_AUTHOR_NAME_LENGTH</code>.
     *
     * @param authorName The author name <code>String</code> to be checked.
     * @throws NoAuthorProvidedException  If the author name is <code>null</code> or empty.
     * @throws AuthorNameTooLongException If the author name is longer than <code>MAX_AUTHOR_NAME_LENGTH</code>.
     */
    public static void ensureValidAuthorName(String authorName)
            throws NoAuthorProvidedException, AuthorNameTooLongException {
        ensureAuthorIsProvided(authorName);
        ensureAuthorNameWithinLength(authorName);
    }

    /**
     * Checks if the manga name is <code>null</code> or is empty, then if it is longer than
     * <code>MAX_MANGA_NAME_LENGTH</code>.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     * @throws NoMangaProvidedException  If the manga name is <code>null</code> or empty.
     * @throws MangaNameTooLongException If the manga name is longer than <code>MAX_MANGA_NAME_LENGTH</code>.
     */
    public static void ensureValidMangaName(String mangaName)
            throws NoMangaProvidedException, MangaNameTooLongException {
        ensureMangaIsProvided(mangaName);
        ensureMangaNameWithinLength(mangaName);
    }

    /**
     * Checks if the deadline <code>String</code> is <code>null</code> or is empty, then if it is longer than
     * <code>MAX_DEADLINE_LENGTH</code>.
     *
     * @param deadlineString The deadline <code>String</code> to be checked.
     * @throws NoDeadlineProvidedException If the deadline <code>String</code> is <code>null</code> or empty.
     * @throws DeadlineTooLongException    If the deadline <code>String</code> is longer than
     *                                     <code>MAX_DEADLINE_LENGTH</code>
     */
    public static void ensureValidDeadline(String deadlineString)
            throws NoDeadlineProvidedException, DeadlineTooLongException {
        ensureDeadlineIsProvided(deadlineString);
        ensureDeadlineWithinLength(deadlineString);
    }

    /**
     * Checks if the provided sales data is valid.
     *
     * @param quantitySoldString The quantity sold <code>String</code> to be checked.
     * @param unitPriceString    The unit price <code>String</code> to be checked.
     * @throws NoQuantityProvidedException  If the quantity sold <code>String</code> is <code>null</code> or empty.
     * @throws QuantityWrongFormatException If the quantity sold <code>String</code> is not an <code>Integer</code>
     * @throws QuantityTooLargeException    If the quantity sold exceeds <code>MAX_QUANTITY_VALUE</code>
     * @throws NoPriceProvidedException     If the unit price <code>String</code> is <code>null</code> or empty.
     * @throws PriceWrongFormatException    If the unit price <code>String</code> is not a <code>Double</code>
     * @throws PriceTooLargeException       If the quantity sold exceeds <code>MAX_UNIT_PRICE_VALUE</code>
     * @throws NumberLessThanZeroException  If either the quantity sold or unit price is negative
     */
    public static void ensureValidSalesData(String quantitySoldString, String unitPriceString)
            throws NoQuantityProvidedException, NoPriceProvidedException, QuantityWrongFormatException,
            PriceWrongFormatException, QuantityTooLargeException, PriceTooLargeException, NumberLessThanZeroException {
        ensureValidQuantitySold(quantitySoldString);
        ensureValidUnitPrice(unitPriceString);
    }

    /**
     * Checks if the provided quantity sold <code>String</code> is valid.
     *
     * @param quantitySoldString The quantity sold <code>String</code> to be checked.
     * @throws NoQuantityProvidedException  If the quantity sold <code>String</code> is <code>null</code> or empty.
     * @throws QuantityWrongFormatException If the quantity sold <code>String</code> is not an <code>Integer</code>
     * @throws QuantityTooLargeException    If the quantity sold exceeds <code>MAX_QUANTITY_VALUE</code>
     * @throws NumberLessThanZeroException  If either the quantity sold or unit price is negative
     */
    public static void ensureValidQuantitySold(String quantitySoldString)
            throws NoQuantityProvidedException, QuantityTooLargeException, QuantityWrongFormatException,
            NumberLessThanZeroException {
        ensureQuantitySoldIsProvided(quantitySoldString);
        ensureValidQuantitySoldNumber(quantitySoldString);
    }

    /**
     * Checks if the provided unit price <code>String</code> is valid.
     *
     * @param unitPriceString The unit price <code>String</code> to be checked.
     * @throws NoPriceProvidedException     If the unit price <code>String</code> is <code>null</code> or empty.
     * @throws PriceWrongFormatException    If the unit price <code>String</code> is not a <code>Double</code>
     * @throws PriceTooLargeException       If the quantity sold exceeds <code>MAX_UNIT_PRICE_VALUE</code>
     * @throws NumberLessThanZeroException  If either the quantity sold or unit price is negative
     */
    public static void ensureValidUnitPrice(String unitPriceString)
            throws NoPriceProvidedException, PriceWrongFormatException, PriceTooLargeException,
            NumberLessThanZeroException {
        ensureUnitPriceIsProvided(unitPriceString);
        ensureValidUnitPriceNumber(unitPriceString);
    }

    private static void ensureAuthorIsProvided(String authorName) throws NoAuthorProvidedException {
        if (authorName == null || authorName.isEmpty()) {
            if (authorName == null) {
                COMMAND_LOGGER.warning("Provided author name is null");
            } else {
                COMMAND_LOGGER.warning("Provided author name is empty");
            }
            throw new NoAuthorProvidedException();
        }
    }

    private static void ensureMangaIsProvided(String mangaName) throws NoMangaProvidedException {
        if (mangaName == null || mangaName.isEmpty()) {
            if (mangaName == null) {
                COMMAND_LOGGER.warning("Provided manga name is null");
            } else {
                COMMAND_LOGGER.warning("Provided manga name is empty");
            }
            throw new NoMangaProvidedException();
        }
    }

    private static void ensureDeadlineIsProvided(String deadlineString) throws NoDeadlineProvidedException {
        if (deadlineString == null || deadlineString.isEmpty()) {
            if (deadlineString == null) {
                COMMAND_LOGGER.warning("Provided deadline is null");
            } else {
                COMMAND_LOGGER.warning("Provided deadline is empty");
            }
            throw new NoDeadlineProvidedException();
        }
    }

    private static void ensureQuantitySoldIsProvided(String quantityString) throws NoQuantityProvidedException {
        if (quantityString == null || quantityString.isEmpty()) {
            if (quantityString == null) {
                COMMAND_LOGGER.warning("Provided quantity is null");
            } else {
                COMMAND_LOGGER.warning("Provided quantity is empty");
            }
            throw new NoQuantityProvidedException();
        }
    }

    private static void ensureUnitPriceIsProvided(String priceString) throws NoPriceProvidedException {
        if (priceString == null || priceString.isEmpty()) {
            if (priceString == null) {
                COMMAND_LOGGER.warning("Provided price is null");
            } else {
                COMMAND_LOGGER.warning("Provided price is empty");
            }
            throw new NoPriceProvidedException();
        }
    }

    private static void ensureAuthorNameWithinLength(String authorName) throws AuthorNameTooLongException {
        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Provided author name \"" + authorName + "\" exceeds maximum length at "
                    + authorName.length());
            throw new AuthorNameTooLongException();
        }
    }

    private static void ensureMangaNameWithinLength(String mangaName) throws MangaNameTooLongException {
        if (mangaName.length() > MAX_MANGA_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Provided manga name \"" + mangaName + "\" exceeds maximum length at "
                    + mangaName.length());
            throw new MangaNameTooLongException();
        }
    }

    private static void ensureDeadlineWithinLength(String deadline) throws DeadlineTooLongException {
        if (deadline.length() > MAX_DEADLINE_LENGTH) {
            COMMAND_LOGGER.warning("Provided deadline \"" + deadline + "\" exceeds maximum length at "
                    + deadline.length());
            throw new DeadlineTooLongException();
        }
    }

    private static void ensureValidUnitPriceNumber(String unitPriceString)
            throws PriceWrongFormatException, PriceTooLargeException, NumberLessThanZeroException {
        Double unitPrice = null;
        try {
            unitPrice = Double.parseDouble(unitPriceString);
        } catch (NumberFormatException e) {
            throw new PriceWrongFormatException();
        }
        if (unitPrice >= MAX_UNIT_PRICE_VALUE) {
            throw new PriceTooLargeException();
        }
        if (unitPrice < 0) {
            throw new NumberLessThanZeroException();
        }
    }

    private static void ensureValidQuantitySoldNumber(String quantitySoldString)
            throws QuantityWrongFormatException, QuantityTooLargeException, NumberLessThanZeroException {
        Integer quantitySold = null;
        try {
            quantitySold = Integer.parseInt(quantitySoldString);
        } catch (NumberFormatException e) {
            throw new QuantityWrongFormatException();
        }
        if (quantitySold >= MAX_QUANTITY_VALUE) {
            throw new QuantityTooLargeException();
        }
        if (quantitySold < 0) {
            throw new NumberLessThanZeroException();
        }

    }

    /**
     * Throws <code>AuthorExistsException</code> if author already exists in the <code>authorList</code>.
     *
     * @param authorName The author name <code>String</code> to be checked.
     * @param authorList The <code>AuthorList</code> to search.
     */
    public static void ensureNoDuplicateAuthor(String authorName, AuthorList authorList) throws AuthorExistsException {
        if (authorList.hasAuthor(authorName)) {
            COMMAND_LOGGER.warning("Author \"" + authorName + "\" already exists in the author list");
            throw new AuthorExistsException();
        }
    }

    /**
     * Throws <code>MangaExistsException</code> if manga already exists in the <code>mangaList</code> of the author.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     * @param author    The <code>Author</code> whose <code>mangaList</code> to search.
     */
    public static void ensureNoDuplicateManga(String mangaName, Author author) throws MangaExistsException {
        if (author.hasManga(mangaName)) {
            COMMAND_LOGGER.warning("Manga \"" + mangaName + "\" already exists in the manga list of \""
                    + author.getAuthorName() + "\"");
            throw new MangaExistsException();
        }
    }

    /**
     * The overloaded method is for deserialization. It throws <code>MangaExistsException</code> if manga already exists
     * in the <code>mangaList</code> while deserializing the author.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     * @param author    The <code>Author</code> currently being checked.
     * @param mangaList The <code>MangaList</code> which has not yet been added to the author
     */
    public static void ensureNoDuplicateManga(String mangaName, Author author, MangaList mangaList)
            throws MangaExistsException {
        for (Manga manga : mangaList) {
            if (manga.getMangaName().equals(mangaName)) {
                COMMAND_LOGGER.warning("Manga \"" + mangaName + "\" already exists in the manga list of \""
                        + author.getAuthorName() + "\"");
                throw new MangaExistsException();
            }
        }
    }

    /**
     * Throws <code>AuthorDoesNotExistException</code> if author cannot be found in the <code>authorList</code>.
     *
     * @param authorName The author name <code>String</code> to be checked.
     * @param authorList The <code>AuthorList</code> to search.
     */
    public static void ensureAuthorExists(String authorName, AuthorList authorList)
            throws AuthorDoesNotExistException {
        if (!authorList.hasAuthor(authorName)) {
            COMMAND_LOGGER.warning("Author \"" + authorName + "\" does not exist in the author list");
            throw new AuthorDoesNotExistException(authorName);
        }
    }

    /**
     * Throws <code>MangaDoesNotExistException</code> if manga cannot be found in the <code>mangaList</code> of the
     * author.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     * @param author    The <code>Author</code> whose <code>mangaList</code> to search.
     */
    public static void ensureMangaExists(String mangaName, Author author) throws MangaDoesNotExistException {
        if (!author.hasManga(mangaName)) {
            COMMAND_LOGGER.warning("Manga \"" + mangaName + "\" does not exist in the manga list of \""
                    + author.getAuthorName() + "\"");
            throw new MangaDoesNotExistException(mangaName);
        }
    }

    /**
     * Throws <code>AuthorListEmptyException</code> if the <code>authorList</code> is empty.
     *
     * @param authorList The <code>AuthorList</code> to be checked.
     */
    public static void ensureAuthorListNotEmpty(AuthorList authorList) throws AuthorListEmptyException {
        if (authorList.isEmpty()) {
            COMMAND_LOGGER.info("The author list is empty");
            throw new AuthorListEmptyException();
        }
    }

    /**
     * Throws <code>MangaListEmptyException</code> if the <code>mangaList</code> of the author is empty.
     *
     * @param author The <code>Author</code> whose <code>mangaList</code> is to be checked.
     */
    public static void ensureMangaListNotEmpty(Author author) throws MangaListEmptyException {
        if (author.getMangaList().isEmpty()) {
            COMMAND_LOGGER.info("The author list is empty");
            throw new MangaListEmptyException(author.getAuthorName());
        }
    }
}
