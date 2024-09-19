package tantou;

import author.Author;
import commands.Command;
import parser.Parser;
import ui.Ui;

import java.util.ArrayList;

public class Tantou {
    private Ui ui;
    private Parser parser;
    private boolean isExit;
    private ArrayList<Author> authors;

    public Tantou() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.isExit = false;
        authors = new ArrayList<>();
    }

    public void run() {
        ui.greetUser();

        while (!isExit) {
            String userInput = ui.getUserInput();
            Command userCommand = parser.getUserCommand(userInput);
            userCommand.execute(ui);
            isExit = userCommand.isExit();
        }
    }
}
