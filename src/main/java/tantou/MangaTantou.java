package tantou;

import author.AuthorList;
import commands.Command;
import commands.GreetCommand;
import exceptions.TantouException;
import parser.Parser;
import ui.Ui;

import static storage.StorageHelper.readFile;

public class MangaTantou {
    private Ui ui;
    private Parser parser;
    private boolean isExit;
    private AuthorList authorList;

    //@@author averageandyyy
    public MangaTantou() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.isExit = false;
        this.authorList = new AuthorList();
    }

    //@@author xenthm
    public void setAuthorList(AuthorList authorList) {
        this.authorList = authorList;
    }

    //@@author
    public void greetUser() {
        Command greetCommand = new GreetCommand();
        try {
            greetCommand.execute(ui, authorList);
        } catch (TantouException e) {
            System.out.printf("Something went wrong!: %s%n", e.getMessage());
        }
    }

    /**
     * Restores <code>authorList</code> from data file in the <code>Storage</code> singleton if available. If not, it
     * remains as a newly initialized one.
     */
    private void restoreDataIfAvailable() {
        AuthorList existingList = readFile();
        if (existingList != null) {
            setAuthorList(existingList);
        }
    }

    public void run() {
        restoreDataIfAvailable();

        //@@author averageandyyy
        greetUser();

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command userCommand = parser.getUserCommand(userInput);
                userCommand.execute(ui, authorList);
                isExit = userCommand.isExit();
            } catch (TantouException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
