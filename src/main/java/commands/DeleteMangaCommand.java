package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import manga.Manga;
import storage.Storage;
import ui.Ui;

import java.util.logging.Level;

import static constants.Command.DELETE_COMMAND;

public class DeleteMangaCommand extends Command {
    // private static final Logger logger = Logger.getLogger(DeleteMangaCommand.class.getName());
    private String[] userInputList;

    public DeleteMangaCommand(String[] userInputList) {
        super(DELETE_COMMAND);
        this.userInputList = userInputList;
    }

    /**
     * Conducts several checks on the userInput and authorList before deleting the manga from the list.
     * If any of the checks fail, a TantouException is thrown.
     */
    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        String authorName = parser.getAuthorNameFromInput(userInputList);
        String mangaName = parser.getMangaNameFromInput(userInputList);

        if (authorName.isEmpty() || mangaName.isEmpty()) {
            logger.log(Level.SEVERE, "Author name or manga name is empty");
            throw new TantouException("No author or manga provided!");
        }
        assert (!authorName.isEmpty() && !mangaName.isEmpty()) : "Author or manga name is empty";
        logger.log(Level.INFO, "Deleting manga... " + mangaName + " from " + authorName);

        Author attachedAuthor = new Author(authorName);
        Manga deletingManga = new Manga(mangaName, attachedAuthor);

        if (authorList.hasAuthor(attachedAuthor)) {
            Author existingAuthor = authorList.getAuthor(attachedAuthor);
            if (existingAuthor.hasManga(deletingManga)) {
                existingAuthor.deleteManga(deletingManga);
                System.out.printf("Manga %s successfully deleted from author %s\n",
                        deletingManga.getMangaName(), existingAuthor.getAuthorName());
                logger.log(Level.INFO, "Successfully deleted manga: " + deletingManga.getMangaName());

                Storage.getInstance().saveAuthorListToDataFile(authorList);
                return;
            }
            assert !existingAuthor.hasManga(deletingManga): "No manga found";
            logger.log(Level.SEVERE, "Manga not found");

            throw new TantouException("Manga does not exist!");
        }
        assert !authorList.hasAuthor(attachedAuthor): "Author not found";
        logger.log(Level.SEVERE, "Author not found");

        throw new TantouException("Author does not exist!");
    }


}


