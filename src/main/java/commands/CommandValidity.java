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

import static commands.Command.COMMAND_LOGGER;
import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static constants.Options.MAX_DEADLINE_LENGTH;
import static constants.Options.MAX_MANGA_NAME_LENGTH;

//@@author xenthm
/**
 * This utility class contains methods to check the various inputs to the defined <code>Command</code>s in
 * <code>MangaTantou</code>, throwing relevant exceptions if needed.
 */
public class CommandValidity {
    /**
     * Checks if the author name is <code>null</code> or is empty, then if it is longer than
     * <code>MAX_AUTHOR_NAME_LENGTH</code>.
     *
     * @param authorName The author name <code>String</code> to be checked.
     * @throws NoAuthorProvidedException  If the author name is <code>null</code> or empty.
     * @throws AuthorNameTooLongException If the author name is longer than <code>MAX_AUTHOR_NAME_LENGTH</code>.
     */
    public static void checkAuthorName(String authorName) throws NoAuthorProvidedException, AuthorNameTooLongException {
        checkAuthorIsProvided(authorName);
        checkAuthorNameLength(authorName);
    }

    /**
     * Checks if the manga name is <code>null</code> or is empty, then if it is longer than
     * <code>MAX_MANGA_NAME_LENGTH</code>.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     * @throws NoMangaProvidedException  If the manga name is <code>null</code> or empty.
     * @throws MangaNameTooLongException If the manga name is longer than <code>MAX_MANGA_NAME_LENGTH</code>.
     */
    public static void checkMangaName(String mangaName) throws NoMangaProvidedException, MangaNameTooLongException {
        checkMangaIsProvided(mangaName);
        checkMangaNameLength(mangaName);
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
    public static void checkDeadline(String deadlineString)
            throws NoDeadlineProvidedException, DeadlineTooLongException {
        checkDeadlineIsProvided(deadlineString);
        checkDeadlineLength(deadlineString);
    }

    private static void checkAuthorIsProvided(String authorName) throws NoAuthorProvidedException {
        if (authorName == null || authorName.isEmpty()) {
            if (authorName == null) {
                COMMAND_LOGGER.warning("Provided author name is null");
            } else {
                COMMAND_LOGGER.warning("Provided author name is empty");
            }
            throw new NoAuthorProvidedException();
        }
    }

    private static void checkMangaIsProvided(String mangaName) throws NoMangaProvidedException {
        if (mangaName == null || mangaName.isEmpty()) {
            if (mangaName == null) {
                COMMAND_LOGGER.warning("Provided manga name is null");
            } else {
                COMMAND_LOGGER.warning("Provided manga name is empty");
            }
            throw new NoMangaProvidedException();
        }
    }

    private static void checkDeadlineIsProvided(String deadlineString) throws NoDeadlineProvidedException {
        if (deadlineString == null || deadlineString.isEmpty()) {
            if (deadlineString == null) {
                COMMAND_LOGGER.warning("Provided deadline is null");
            } else {
                COMMAND_LOGGER.warning("Provided deadline is empty");
            }
            throw new NoDeadlineProvidedException();
        }
    }

    /**
     * Throws <code>NoQuantityProvidedException</code> if quantity is <code>null</code> or is empty.
     *
     * @param quantityString The quantity <code>String</code> to be checked.
     */
    public static void checkQuantityIsProvided(String quantityString) throws NoQuantityProvidedException {
        if (quantityString == null || quantityString.isEmpty()) {
            if (quantityString == null) {
                COMMAND_LOGGER.warning("Provided quantity is null");
            } else {
                COMMAND_LOGGER.warning("Provided quantity is empty");
            }
            throw new NoQuantityProvidedException();
        }
    }

    /**
     * Throws <code>NoPriceProvidedException</code> if price is <code>null</code> or is empty.
     *
     * @param priceString The price <code>String</code> to be checked.
     */
    public static void checkPriceIsProvided(String priceString) throws NoPriceProvidedException {
        if (priceString == null || priceString.isEmpty()) {
            if (priceString == null) {
                COMMAND_LOGGER.warning("Provided price is null");
            } else {
                COMMAND_LOGGER.warning("Provided price is empty");
            }
            throw new NoPriceProvidedException();
        }
    }

    private static void checkAuthorNameLength(String authorName) throws AuthorNameTooLongException {
        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Provided author name \"" + authorName + "\" exceeds maximum length at "
                    + authorName.length());
            throw new AuthorNameTooLongException();
        }
    }

    private static void checkMangaNameLength(String mangaName) throws MangaNameTooLongException {
        if (mangaName.length() > MAX_MANGA_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Provided manga name \"" + mangaName + "\" exceeds maximum length at "
                    + mangaName.length());
            throw new MangaNameTooLongException();
        }
    }

    private static void checkDeadlineLength(String deadline) throws DeadlineTooLongException {
        if (deadline.length() > MAX_DEADLINE_LENGTH) {
            COMMAND_LOGGER.warning("Provided deadline \"" + deadline + "\" exceeds maximum length at "
                    + deadline.length());
            throw new DeadlineTooLongException();
        }
    }

    /**
     * Throws <code>AuthorExistsException</code> if author already exists in the <code>authorList</code>.
     *
     * @param authorName The author name <code>String</code> to be checked.
     * @param authorList The <code>AuthorList</code> to search.
     */
    public static void checkForDuplicateAuthor(String authorName, AuthorList authorList) throws AuthorExistsException {
        if (authorList.hasAuthor(authorName)) {
            COMMAND_LOGGER.warning("\"" + authorName + "\" already exists in the author list");
            throw new AuthorExistsException();
        }
    }

    /**
     * Throws <code>MangaExistsException</code> if manga already exists in the <code>mangaList</code> of the author.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     * @param author    The <code>Author</code> whose <code>mangaList</code> to search.
     */
    public static void checkForDuplicateManga(String mangaName, Author author) throws MangaExistsException {
        if (author.hasManga(mangaName)) {
            COMMAND_LOGGER.warning("\"" + mangaName + "\" already exists in the manga list of \""
                    + author.getAuthorName() + "\"");
            throw new MangaExistsException();
        }
    }

    /**
     * Throws <code>AuthorDoesNotExistException</code> if author cannot be found in the <code>authorList</code>.
     *
     * @param authorName The author name <code>String</code> to be checked.
     * @param authorList The <code>AuthorList</code> to search.
     */
    public static void checkIfAuthorDoesNotExist(String authorName, AuthorList authorList)
            throws AuthorDoesNotExistException {
        if (!authorList.hasAuthor(authorName)) {
            COMMAND_LOGGER.warning("\"" + authorName + "\" does not exist in the author list");
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
    public static void checkIfMangaDoesNotExist(String mangaName, Author author) throws MangaDoesNotExistException {
        if (!author.hasManga(mangaName)) {
            COMMAND_LOGGER.warning("\"" + mangaName + "\" does not exist in the manga list of \""
                    + author.getAuthorName() + "\"");
            throw new MangaDoesNotExistException(mangaName);
        }
    }

    /**
     * Throws <code>AuthorListEmptyException</code> if the <code>authorList</code> is empty.
     *
     * @param authorList The <code>AuthorList</code> to be checked.
     */
    public static void checkIfAuthorListNotEmpty(AuthorList authorList) throws AuthorListEmptyException {
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
    public static void checkIfMangaListNotEmpty(Author author) throws MangaListEmptyException {
        if (author.getMangaList().isEmpty()) {
            COMMAND_LOGGER.info("The author list is empty");
            throw new MangaListEmptyException(author.getAuthorName());
        }
    }
}
