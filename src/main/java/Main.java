import tantou.MangaTantou;

import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        MangaTantou mangaTantou = new MangaTantou();
        if (args.length != 0 && args[0].equals("nolog")) {
            LogManager.getLogManager().reset();
        }
        mangaTantou.run();
    }
}
