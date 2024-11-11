package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorNameTooLongException;
import exceptions.MangaNameTooLongException;
import exceptions.NoAuthorProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.CATALOG_COMMAND;
import static constants.Command.MANGA_INDEX;
import static storage.StorageHelper.saveFile;

//@@author averageandyyy
/**
 * Represents a command to add a new manga to an author's collection.
 *
 * <p>The {@code AddMangaCommand} class extends the {@link Command} class and defines
 * the behavior for adding a manga associated with a specific author. It validates the
 * provided author and manga names, checks for duplicates, and updates the author list
 * accordingly.
 */
public class AddMangaCommand extends Command {
    private String authorName;
    private String mangaName;

    public AddMangaCommand(String[] argsAuthorManga) {
        super(CATALOG_COMMAND);
        this.authorName = argsAuthorManga[AUTHOR_INDEX];
        this.mangaName = argsAuthorManga[MANGA_INDEX];
    }

    /**
     * Executes the command to add the manga to the specified author's collection in the author list.
     *
     * <p>This method validates the provided author and manga names, checks their lengths
     * against predefined maximum limits, and ensures that the author exists in the list.
     * If the author does not exist, a new author is created. The manga is then added to
     * the author's collection. If a manga with the same name already exists for the author,
     * an exception is thrown.
     *
     * @param ui The user interface through which the command interacts with the user.
     * @param authorList The list of authors to which the new manga will be added.
     * @throws TantouException if an error occurs during command execution, such as
     *                         if the author or manga name is missing, exceeds maximum length,
     *                         or if the manga already exists.
     * @throws NoAuthorProvidedException if the provided author name is null or empty.
     * @throws NoMangaProvidedException if the provided manga name is null or empty.
     * @throws AuthorNameTooLongException if the provided author name exceeds the
     *                                     maximum length defined by {@code MAX_AUTHOR_NAME_LENGTH}.
     * @throws MangaNameTooLongException if the provided manga name exceeds the
     *                                    maximum length defined by {@code MAX_MANGA_NAME_LENGTH}.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        //@@author xenthm
        CommandValidator.ensureValidAuthorName(authorName);
        CommandValidator.ensureValidMangaName(mangaName);

        //@@author averageandyyy
        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        // If author exists add manga to the existing author
        if (authorList.hasAuthor(incomingAuthor)) {
            // Obtain the same Author object in authorList
            Author existingAuthor = authorList.getAuthor(incomingAuthor);

            CommandValidator.ensureNoDuplicateManga(mangaName, existingAuthor);

            existingAuthor.addManga(incomingManga);
            ui.printAddMangaSuccessMessage(incomingManga);

            // Assert that the manga was successfully added
            assert authorList.getAuthor(incomingAuthor).hasManga(incomingManga) : "Failed to add manga";
            saveFile(authorList);
            return;
        }

        // Otherwise create new Author and add Manga to it
        authorList.add(incomingAuthor);
        incomingAuthor.addManga(incomingManga);
        assert authorList.getAuthor(incomingAuthor).hasManga(incomingManga) : "Failed to add author and manga";
        ui.printAddMangaSuccessMessage(incomingManga);
        saveFile(authorList);
    }
}
