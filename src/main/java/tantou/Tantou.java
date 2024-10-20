package tantou;

import author.AuthorList;
import commands.Command;
import exceptions.TantouException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

public class Tantou {
    private Ui ui;
    private Parser parser;
    private boolean isExit;
    private AuthorList authorList;

    public Tantou() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.isExit = false;
        this.authorList = new AuthorList();
    }

    public void setAuthorList(AuthorList authorList) {
        this.authorList = authorList;
    }

    public void run() {
        AuthorList existingList = Storage.getInstance().readAuthorListFromDataFile();
        if (existingList != null) {
            setAuthorList(existingList);
        }

        ui.greetUser();

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
