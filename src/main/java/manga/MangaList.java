package manga;

import java.util.ArrayList;

//@@author xenthm
/**
 * Represents a list of <code>Manga</code>. Extends {@code ArrayList<Manga>}
 */
public class MangaList extends ArrayList<Manga> {
    /**
     * Prints the entire <code>MangaList</code> with the associated <code>deadline</code> after formatting.
     */
    public void print() {
        assert size() >= 0 : "MangaList.size() must be non-negative";

        for (int i = 0; i < size(); i++) {
            System.out.println((i + 1) + ". " + get(i).getMangaName() + " | Deadline: " + get(i).getDeadline());
        }
    }
}
