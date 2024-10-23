package commands;

import author.Author;
import exceptions.TantouException;
import storage.Storage;
import ui.Ui;
import author.AuthorList;

import java.util.logging.Level;

import static constants.Command.DELETE_COMMAND;

public class DeleteDeadlineCommand extends Command {
    private String[] userInputList;

    public DeleteDeadlineCommand(String[] userInputList) {
        super(DELETE_COMMAND);
        this.userInputList = userInputList;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        // Empty user input should have been caught at the Parser level
        assert !(userInputList.length == 0) : "No user input provided";

        String authorName = parser.getAuthorNameFromInput(userInputList);
        String mangaName = parser.getMangaNameFromInput(userInputList);

        if (authorName.isEmpty() || mangaName.isEmpty()) {
            logger.warning("No author, or manga provided.");
            throw new TantouException("No author or manga provided!");
        }

        Author incomingAuthor = new Author(authorName);

        // If author exists, find the manga
        if (authorList.hasAuthor(authorName)) {
            // Obtain the same Author object in authorList
            Author existingAuthor = authorList.getAuthor(incomingAuthor);
            // If manga exists, add the deadline
            if (existingAuthor.getManga(mangaName) != null) {
                existingAuthor.getManga(mangaName).deleteDeadline();
                // Assert that the deletion successfully executed
                assert authorList.getAuthor(authorName).getManga(mangaName)
                        .getDeadline().equals("None") : "Deadline was not deleted";
                logger.log(Level.INFO, "Successfully deleted deadline from " + mangaName);
                System.out.printf("Deadline successfully deleted from manga %s\n",
                        mangaName);

                Storage.getInstance().saveAuthorListToDataFile(authorList);
                return;
            }

            // Exception thrown if the manga doesn't exist
            logger.log(Level.SEVERE, "Manga not found");
            throw new TantouException("Manga doesn't exist!");
        }

        // Exception thrown if the author doesn't exist
        logger.log(Level.SEVERE, "Author not found");
        throw new TantouException("Author doesn't exist!");
    }
}
