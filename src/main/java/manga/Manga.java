package manga;

import author.Author;

import java.util.Objects;

public class Manga {
    private String mangaName;
    private Author author;

    public Manga(String mangaName, Author author) {
        this.mangaName = mangaName;
        this.author = author;
    }

    public String getMangaName() {
        return mangaName;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Manga manga = (Manga) o;

        //
        return mangaName.equals(manga.getMangaName()) && author.equals(manga.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mangaName, author);
    }
}
