package ui;

import java.util.Scanner;

import static constants.Message.EXIT_MESSAGE;
import static constants.Message.GREETING_MESSAGE;

public class Ui {
    private Scanner in = new Scanner(System.in);

    public void greetUser() {
        System.out.println(GREETING_MESSAGE);
    }

    public void sayGoodbye() {
        System.out.println(EXIT_MESSAGE);
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
