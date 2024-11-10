package commands;

import author.Author;
import author.AuthorList;
import exceptions.*;

import static commands.Command.COMMAND_LOGGER;
import static constants.Options.*;

//@@author xenthm
/**
 * This utility class contains methods to check the various inputs to the defined <code>Command</code>s in
 * <code>MangaTantou</code>, throwing relevant exceptions if needed.
 */
public class CommandValidity {
    /**
     * Throws <code>NoAuthorProvidedException</code> if author is <code>null</code> or is empty.
     *
     * @param authorName The author name <code>String</code> to be checked.
     */
    public static void checkAuthorProvided(String authorName) throws NoAuthorProvidedException {
        if (authorName == null || authorName.isEmpty()) {
            if (authorName == null) {
                COMMAND_LOGGER.warning("Provided author name is null");
            } else {
                COMMAND_LOGGER.warning("Provided author name is empty");
            }
            throw new NoAuthorProvidedException();
        }
    }

    /**
     * Throws <code>NoMangaProvidedException</code> if manga is <code>null</code> or is empty.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     */
    public static void checkMangaProvided(String mangaName) throws NoMangaProvidedException {
        if (mangaName == null || mangaName.isEmpty()) {
            if (mangaName == null) {
                COMMAND_LOGGER.warning("Provided manga name is null");
            } else {
                COMMAND_LOGGER.warning("Provided manga name is empty");
            }
            throw new NoMangaProvidedException();
        }
    }

    /**
     * Throws <code>NoDeadlineProvidedException</code> if deadline is <code>null</code> or is empty.
     *
     * @param deadlineString The deadline <code>String</code> to be checked.
     */
    public static void checkDeadlineProvided(String deadlineString) throws NoDeadlineProvidedException {
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
    public static void checkQuantityProvided(String quantityString) throws NoQuantityProvidedException {
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
    public static void checkPriceProvided(String priceString) throws NoPriceProvidedException {
        if (priceString == null || priceString.isEmpty()) {
            if (priceString == null) {
                COMMAND_LOGGER.warning("Provided price is null");
            } else {
                COMMAND_LOGGER.warning("Provided price is empty");
            }
            throw new NoPriceProvidedException();
        }
    }

    /**
     * Throws <code>AuthorNameTooLongException</code> if author name is longer than MAX_AUTHOR_NAME_LENGTH.
     *
     * @param authorName The manga name <code>String</code> to be checked.
     */
    public static void checkAuthorNameLength(String authorName) throws AuthorNameTooLongException {
        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Provided author name \"" + authorName + "\" exceeds maximum length at "
                    + authorName.length());
            throw new AuthorNameTooLongException();
        }
    }

    /**
     * Throws <code>MangaNameTooLongException</code> if manga name is longer than MAX_MANGA_NAME_LENGTH.
     *
     * @param mangaName The manga name <code>String</code> to be checked.
     */
    public static void checkMangaNameLength(String mangaName) throws MangaNameTooLongException {
        if (mangaName.length() > MAX_MANGA_NAME_LENGTH) {
            COMMAND_LOGGER.warning("Provided manga name \"" + mangaName + "\" exceeds maximum length at "
                    + mangaName.length());
            throw new MangaNameTooLongException();
        }
    }

    /**
     * Throws <code>DeadlineTooLongException</code> if deadline is longer than MAX_DEADLINE_LENGTH.
     *
     * @param deadline The deadline <code>String</code> to be checked.
     */
    public static void checkDeadlineLength(String deadline) throws DeadlineTooLongException {
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
    public static void checkDuplicateAuthor(String authorName, AuthorList authorList) throws AuthorExistsException {
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
    public static void checkDuplicateManga(String mangaName, Author author) throws MangaExistsException {
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
    public static void checkAuthorDoesNotExist(String authorName, AuthorList authorList) throws AuthorDoesNotExistException {
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
    public static void checkAuthorDoesNotExist(String mangaName, Author author) throws MangaDoesNotExistException {
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
    public static void checkAuthorListNotEmpty(AuthorList authorList) throws AuthorListEmptyException {
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
    public static void checkMangaListNotEmpty(Author author) throws MangaListEmptyException {
        if (author.getMangaList().isEmpty()) {
            COMMAND_LOGGER.info("The author list is empty");
            throw new MangaListEmptyException(author.getAuthorName());
        }
    }
}
