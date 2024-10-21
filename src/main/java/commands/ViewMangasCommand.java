package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import static constants.Command.VIEW_COMMAND;

//@@author xenthm
/**
 * Represents the <code>view</code> command with the <code>-a</code> option. Handles its execution.
 */
public class ViewMangasCommand extends Command {
    private String userInput;

    public ViewMangasCommand(String userInput) {
        super(VIEW_COMMAND);
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        assert ui != null : "Ui must not be null";
        assert authorList != null : "authorList must not be null";

        if (authorList.size() == 0) {
            System.out.println("You have no authors under you! Maybe you are the one slacking...");
            logger.info("authorList is empty");
            return;
        }
        String authorName = parser.getAuthorNameFromInput(userInput);
        if (authorName.isEmpty()) {
            logger.warning("Author argument is empty");
            throw new TantouException("No author provided!");
        }
        Author author = authorList.getAuthor(authorName);
        if (author == null) {
            logger.warning("Author does not exist in authorList");
            System.out.println("Author does not exist!");
            return;
        }
        if (author.getMangaList().isEmpty()) {
            logger.info(authorName + " has no associated mangas");
            System.out.println(authorName + " has no mangas... You know what has to be done.");
            return;
        }
        System.out.println("Mangas authored by " + authorName + ", Total: " + author.getMangaList().size());
        author.printMangaList();
    }
}
