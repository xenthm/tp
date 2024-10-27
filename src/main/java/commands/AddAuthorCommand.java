package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static storage.StorageHelper.saveFile;
import static constants.Command.CATALOG_COMMAND;

//@@author averageandyyy
public class AddAuthorCommand extends Command {
    private String authorName;

    public AddAuthorCommand(String authorName) {
        super(CATALOG_COMMAND);
        this.authorName = authorName;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        // Empty user input should have been caught at the Parser level
        assert authorName != null : "No user input provided";

        if (authorName.isEmpty()) {
            logger.warning("No author provided!");
            throw new TantouException("No author provided!");
        }

        Author incomingAuthor = new Author(authorName);

        if (!authorList.hasAuthor(incomingAuthor)) {
            authorList.add(incomingAuthor);
            System.out.printf("Successfully added author: %s\n", incomingAuthor.getAuthorName());

            // Assert that the addition was successfully executed
            assert authorList.hasAuthor(incomingAuthor) : "Author is missing";
            assert authorList.getAuthor(incomingAuthor).getAuthorName()
                    .equals(incomingAuthor.getAuthorName()) : "Author was not added";

            saveFile(authorList);
            return;
        }

        // The existing author must have the same name for the duplicate to be recognized
        assert authorList.getAuthor(incomingAuthor).getAuthorName()
                .equals(incomingAuthor.getAuthorName()) : "Different author recognized as equal!";

        logger.info("Author already exists");
        throw new TantouException("Author exists!");
    }
}
