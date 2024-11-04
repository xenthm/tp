import tantou.Tantou;

import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        Tantou tantou = new Tantou();
        if (args.length != 0 && args[0].equals("nolog")) {
            LogManager.getLogManager().reset();
        }
        tantou.run();
    }
}
