package manga;

import author.Author;
import storage.ExcludeInSerialization;
import sales.Sale;

import java.util.Objects;

public class Manga {
    private String mangaName;
    @ExcludeInSerialization
    private Author author;
    private String deadline;
    private Sale salesData;

    public Manga(String mangaName, Author author) {
        this.mangaName = mangaName;
        this.author = author;
        this.deadline = "None";
        this.salesData = new Sale();
    }

    //@@author xenthm
    public Manga(String mangaName, Author author, String deadline) {
        this.mangaName = mangaName;
        this.author = author;
        this.deadline = deadline;
        this.salesData = new Sale();
    }

    public Manga(String mangaName, Author author, String deadline, Sale salesData) {
        this.mangaName = mangaName;
        this.author = author;
        this.deadline = deadline;
        this.salesData = salesData;
    }

    //@@author
    public String getMangaName() {
        return mangaName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDeadline() {
        return deadline;
    }

    public void addDeadline(String deadline) {
        this.deadline = deadline;
    }

    //@@author sarahchow03

    public Sale getSalesData() {
        return salesData;
    }

    public void addSalesData(Sale salesData) {
        this.salesData = salesData;
    }

    //@@author averageandyyy
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Manga manga = (Manga) o;

        return mangaName.equals(manga.getMangaName()) && author.equals(manga.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mangaName, author);
    }
}
