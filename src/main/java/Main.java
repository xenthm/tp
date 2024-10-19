import author.AuthorList;
import storage.Storage;
import tantou.Tantou;

public class Main {
    public static void main(String[] args) {
        Tantou tantou = new Tantou();
        AuthorList existingList = Storage.getInstance().readDataFile();
        if (existingList != null) {
            tantou.setAuthorList(existingList);
        }
        tantou.run();
    }
}
