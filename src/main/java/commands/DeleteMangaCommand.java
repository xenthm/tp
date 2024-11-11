package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.DELETE_COMMAND;
import static constants.Command.MANGA_INDEX;
import static storage.StorageHelper.saveFile;

//@@author sarahchow03
/**
 * Represents a command to delete a manga from an author's mangaList.
 * It checks for the existence of the author and manga in the list before deletion.
 * If the author or manga is not found, an exception is thrown.
 *
 * This class extends the Command class and overrides the execute method to handle the delete operation.
 */
public class DeleteMangaCommand extends Command {
    private String authorName;
    private String mangaName;


    /**
     * Constructs a DeleteMangaCommand with the given user input.
     *
     * @param argsAuthorManga The user's input list which consists of the author and manga.
     */
    public DeleteMangaCommand(String[] argsAuthorManga) {
        super(DELETE_COMMAND);
        this.authorName = argsAuthorManga[AUTHOR_INDEX];
        this.mangaName = argsAuthorManga[MANGA_INDEX];
    }

    /**
     * Executes the delete command by conducting several checks on the user input and author list.
     * If the checks are successful, the specified manga is deleted from the author's list.
     *
     * @param ui          The Ui object that handles user interface interactions.
     * @param authorList  The list of authors containing the list of existing authors in the catalog.
     * @throws TantouException If the author or manga does not exist, or if the input is invalid.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        CommandValidator.ensureValidAuthorName(authorName);
        CommandValidator.ensureValidMangaName(mangaName);

        Author attachedAuthor = new Author(authorName);
        Manga deletingManga = new Manga(mangaName, attachedAuthor);
        CommandValidator.ensureAuthorExists(attachedAuthor.getAuthorName(), authorList);

        Author existingAuthor = authorList.getAuthor(attachedAuthor);
        CommandValidator.ensureMangaExists(deletingManga.getMangaName(), existingAuthor);

        existingAuthor.deleteManga(deletingManga);
        ui.printDeleteMangaSuccessMessage(deletingManga);
        saveFile(authorList);
    }
}


