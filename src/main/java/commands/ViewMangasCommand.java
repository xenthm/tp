package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorDoesNotExistException;
import exceptions.AuthorListEmptyException;
import exceptions.AuthorNameTooLongException;
import exceptions.MangaListEmptyException;
import exceptions.MangaNameTooLongException;
import exceptions.NoAuthorProvidedException;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;

import static constants.Command.VIEW_COMMAND;
import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static constants.Options.MAX_MANGA_NAME_LENGTH;
import static manga.MangaList.mangaColumnsToPrint;

//@@author xenthm
/**
 * Represents the <code>view</code> command with the <code>-a</code> option; also known as the <code>view manga</code>
 * command. Handles its execution.
 */
public class ViewMangasCommand extends Command {
    private String authorName;
    private boolean includeDeadline;
    private boolean includeSales;

    public ViewMangasCommand(String authorName, boolean includeDeadline, boolean includeSales) {
        super(VIEW_COMMAND);
        this.authorName = authorName;
        this.includeDeadline = includeDeadline;
        this.includeSales = includeSales;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        assert ui != null : "Ui must not be null";
        assert authorList != null : "authorList must not be null";
        assert authorName != null : "An author name must be provided";

        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            logger.warning("Author name " + authorName + " exceeds maximum length");
            throw new AuthorNameTooLongException();
        }

        if (authorList.isEmpty()) {
            logger.info("authorList is empty");
            throw new AuthorListEmptyException();
        }

        if (authorName.isEmpty()) {
            logger.warning("Author argument is empty");
            throw new NoAuthorProvidedException();
        }
        Author author = authorList.getAuthor(authorName);

        if (author == null) {
            logger.warning("Author " + authorName + " does not exist in authorList");
            System.out.println("Author " + authorName + " does not exist!");
            throw new AuthorDoesNotExistException(authorName);
        }

        if (author.getMangaList().isEmpty()) {
            logger.info(authorName + " has no associated mangas");
            throw new MangaListEmptyException(authorName);
        }

        for (Manga manga : author.getMangaList()) {
            if (manga.getMangaName().length() > MAX_MANGA_NAME_LENGTH) {
                logger.warning("Manga name " + manga.getMangaName() + " exceeds maximum length");
                throw new MangaNameTooLongException();
            }
        }

        System.out.println("Mangas authored by " + authorName + ", Total: " + author.getMangaList().size());
        Ui.printList(author.getMangaList(), mangaColumnsToPrint(includeDeadline, includeSales));
    }
}
