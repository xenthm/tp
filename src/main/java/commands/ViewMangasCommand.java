package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;

import static constants.Command.VIEW_COMMAND;
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

        CommandValidator.ensureValidAuthorName(authorName);
        CommandValidator.ensureAuthorListNotEmpty(authorList);
        CommandValidator.ensureAuthorExists(authorName, authorList);
        Author author = authorList.getAuthor(authorName);

        CommandValidator.ensureMangaListNotEmpty(author);
        for (Manga manga : author.getMangaList()) {
            CommandValidator.ensureValidMangaName(manga.getMangaName());
        }
        ui.printPreMangaListMessage(author);
        Ui.printList(author.getMangaList(), mangaColumnsToPrint(includeDeadline, includeSales));
    }
}
