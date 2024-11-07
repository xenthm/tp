import tantou.Tantou;

public class Main {
    public static void main(String[] args) {
        boolean loggingEnabled = true;
        if (args.length != 0 && args[0].equals("nolog")) {
            loggingEnabled = false;
        }
        Tantou tantou = new Tantou(loggingEnabled);
        tantou.run();
    }
}
