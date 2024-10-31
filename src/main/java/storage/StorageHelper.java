package storage;

import author.AuthorList;

//@@author xenthm
/**
 * This utility class simplifies calls to save the <code>authorList</code> to the data file.
 */
public class StorageHelper {
    public static void saveFile(AuthorList authorList) {
        Storage.getInstance().saveAuthorListToDataFile(authorList);
    }

    public static AuthorList readFile() {
        return Storage.getInstance().readAuthorListFromDataFile();
    }
}
