package commands;

import author.Author;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;
import author.AuthorList;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.MANGA_INDEX;
import static constants.Command.DEADLINE_INDEX;
import static storage.StorageHelper.saveFile;

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

        CommandValidator.ensureValidAuthorName(authorName);
        CommandValidator.ensureValidMangaName(mangaName);
        CommandValidator.ensureValidDeadline(deadline);
        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        // If author doesn't exist, throw an error
        CommandValidator.ensureAuthorExists(authorName, authorList);
        assert authorList.hasAuthor(incomingAuthor) : "Author is missing";
        Author existingAuthor = authorList.getAuthor(incomingAuthor);

        // If manga doesn't exist, throw an error
        CommandValidator.ensureMangaExists(mangaName, existingAuthor);
        assert authorList.getAuthor(authorName).hasManga(incomingManga) : "Manga is missing";

        // Change the deadline for the specified manga
        existingAuthor.getManga(mangaName).addDeadline(deadline);

        // Assert that the addition successfully executed
        assert authorList.getAuthor(authorName).getManga(mangaName)
                .getDeadline().equals(deadline) : "Deadline was not added";
        ui.printAddDeadlineSuccessMessage(incomingManga);
        saveFile(authorList);
    }
}
