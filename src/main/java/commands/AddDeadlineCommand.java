package commands;

import author.Author;
import exceptions.AuthorDoesNotExistException;
import exceptions.MangaDoesNotExistException;
import exceptions.NoAuthorProvidedException;
import exceptions.NoDeadlineProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;
import author.AuthorList;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.MANGA_INDEX;
import static constants.Command.DEADLINE_INDEX;
import static storage.StorageHelper.saveFile;

import java.util.logging.Level;

import static constants.Command.SCHEDULE_COMMAND;

public class AddDeadlineCommand extends Command {
    private String[] userInput;

    public AddDeadlineCommand(String[] userInput) {
        super(SCHEDULE_COMMAND);
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        // Empty user input should have been caught at the Parser level
        assert userInput.length != 0 : "No user input provided";
        // There should be a manga, author, and deadline provided
        assert userInput.length == 3 : "Invalid user input provided";

        String authorName = userInput[AUTHOR_INDEX];
        String mangaName = userInput[MANGA_INDEX];
        String deadline = userInput[DEADLINE_INDEX];

        if (authorName == null || authorName.isEmpty()) {
            throw new NoAuthorProvidedException();
        }

        if (mangaName == null || mangaName.isEmpty()) {
            throw new NoMangaProvidedException();
        }

        if (deadline == null || deadline.isEmpty()) {
            throw new NoDeadlineProvidedException();
        }

        logger.info("Valid author, manga and deadline provided");

        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        // If author doesn't exist, throw an error
        if (!authorList.hasAuthor(authorName)) {
            logger.log(Level.INFO, "Author not found!");
            throw new AuthorDoesNotExistException(authorName);
        }
        assert authorList.hasAuthor(incomingAuthor) : "Author is missing";
        Author existingAuthor = authorList.getAuthor(incomingAuthor);

        // If manga doesn't exist, throw an error
        if (!existingAuthor.hasManga(incomingManga)) {
            logger.log(Level.INFO, "Manga not found!");
            throw new MangaDoesNotExistException(mangaName);
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

        saveFile(authorList);
    }
}
