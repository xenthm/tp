package commands;

import author.Author;
import author.AuthorList;
import exceptions.TantouException;
import ui.Ui;

import static constants.Command.VIEW_COMMAND;

public class ViewMangasCommand extends Command {
    private String userInput;

    public ViewMangasCommand(String userInput) {
        super(VIEW_COMMAND);
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        if (authorList.size() == 0) {
            System.out.println("You have no authors under you! Maybe you are the one slacking...");
            return;
        }
        String authorName = parser.getAuthorNameFromInput(userInput);
        if (authorName.isEmpty()) {
            throw new TantouException("No author provided!");
        }
        Author author = authorList.getAuthor(authorName);
        if (author == null) {
            System.out.println("Author does not exist!");
            return;
        }
        if (author.getMangaList().isEmpty()) {
            System.out.println(authorName + " has no mangas... You know what has to be done.");
            return;
        }
        System.out.println("Mangas authored by " + authorName + ", Total: " + author.getMangaList().size());
        author.printMangaList();
    }
}
