package commands;

import author.Author;
import exceptions.TantouException;
import manga.Manga;
import storage.Storage;
import ui.Ui;
import author.AuthorList;

import java.util.logging.Level;

import static constants.Command.ADD_COMMAND;

public class AddDeadlineCommand extends Command {
    private String userInput;

    public AddDeadlineCommand(String userInput) {
        super(ADD_COMMAND);
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        // Empty user input should have been caught at the Parser level
        assert !(userInput.isEmpty()) : "No user input provided";

        String authorName = parser.getAuthorNameFromInput(userInput);
        String mangaName = parser.getMangaNameFromInput(userInput);
        String deadline = parser.getDeadlineDateFromInput(userInput);

        if (deadline.isEmpty() || mangaName.isEmpty() || authorName.isEmpty()) {
            logger.warning("No deadline, author, or manga provided.");
            throw new TantouException("No deadline date, author, or manga provided!");
        }

        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        // If author doesn't exist, create them
        if (!authorList.hasAuthor(authorName)) {
            logger.log(Level.INFO, "Author not found!");
            throw new TantouException("Author not found!");
        }
        assert authorList.hasAuthor(incomingAuthor) : "Author is missing";
        Author existingAuthor = authorList.getAuthor(incomingAuthor);

        // If manga doesn't exist, create it and add the deadline
        if (!existingAuthor.hasManga(incomingManga)) {
            logger.log(Level.INFO, "Manga not found!");
            throw new TantouException("Manga not found!");
        }
        assert authorList.getAuthor(authorName).hasManga(incomingManga) : "Manga is missing";

        // Change the deadline for the specified manga
        existingAuthor.getManga(mangaName).addDeadline(deadline);

        // Assert that the addition successfully executed
        assert authorList.getAuthor(authorName).getManga(mangaName)
                .getDeadline().equals(deadline) : "Deadline was not added";
        logger.log(Level.INFO, "Deadline added to manga " + mangaName);
        System.out.printf("Deadline %s added successfully to manga %s\n",
                deadline, incomingManga.getMangaName());

        Storage.getInstance().saveAuthorListToDataFile(authorList);
    }
}
