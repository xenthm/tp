package manga;

import org.junit.jupiter.api.Test;
import ui.PrintColumn;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author xenthm
public class MangaListTest {
    @Test
    public void mangaColumnsToPrint_includeNothing_containsNoExtraColumns() {
        ArrayList<PrintColumn<Manga>> columns = MangaList.mangaColumnsToPrint(false, false);
        assertEquals(2, columns.size());
        assertEquals("no.", columns.get(0).getHeader());
        assertEquals("Manga Name", columns.get(1).getHeader().trim());
    }

    @Test
    public void mangaColumnsToPrint_includeDeadline_containsDeadlineColumn() {
        ArrayList<PrintColumn<Manga>> columns = MangaList.mangaColumnsToPrint(true, false);
        assertEquals(3, columns.size());
        assertEquals("no.", columns.get(0).getHeader());
        assertEquals("Manga Name", columns.get(1).getHeader().trim());
        assertEquals("Deadline", columns.get(2).getHeader().trim());
    }

    @Test
    public void mangaColumnsToPrint_includeSales_containsSalesColumns() {
        ArrayList<PrintColumn<Manga>> columns = MangaList.mangaColumnsToPrint(false, true);
        assertEquals(5, columns.size());
        assertEquals("no.", columns.get(0).getHeader());
        assertEquals("Manga Name", columns.get(1).getHeader().trim());
        assertEquals("Unit Price", columns.get(2).getHeader().trim());
        assertEquals("Units Sold", columns.get(3).getHeader().trim());
        assertEquals("Revenue", columns.get(4).getHeader().trim());
    }

    @Test
    public void mangaColumnsToPrint_includeAll_containsAllColumns() {
        ArrayList<PrintColumn<Manga>> columns = MangaList.mangaColumnsToPrint(true, true);
        assertEquals(6, columns.size());
        assertEquals("no.", columns.get(0).getHeader());
        assertEquals("Manga Name", columns.get(1).getHeader().trim());
        assertEquals("Deadline", columns.get(2).getHeader().trim());
        assertEquals("Unit Price", columns.get(3).getHeader().trim());
        assertEquals("Units Sold", columns.get(4).getHeader().trim());
        assertEquals("Revenue", columns.get(5).getHeader().trim());
    }
}
