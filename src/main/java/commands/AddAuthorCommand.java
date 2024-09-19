package commands;

import org.apache.commons.cli.ParseException;
import ui.Ui;

import static constants.Regex.SPACE_REGEX;

public class AddAuthorCommand extends AddCommand {
    public AddAuthorCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Ui ui) {
        try {
            String authorName = parser.getAuthorName(userInput.split(SPACE_REGEX));
            System.out.println(authorName);
        } catch (ParseException e) {
            System.out.println("no author provided!");
        }
    }
}
