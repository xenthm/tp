package author;

import manga.Manga;

import java.util.ArrayList;
import java.util.Objects;

public class Author {
    private String authorName;
    private ArrayList<Manga> mangaList;

    public Author(String authorName) {
        this.authorName = authorName;
        this.mangaList = new ArrayList<>();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public ArrayList<Manga> getMangaList() {
        return mangaList;
    }

    public boolean hasManga(Manga manga) {
        return mangaList.contains(manga);
    }

    public void addManga(Manga manga) {
        mangaList.add(manga);
    }

    public void deleteManga(Manga manga) {
        mangaList.remove(manga);
    }

    @Override
    public boolean equals(Object o) {
        // Check if they are the same object in memory
        if (this == o) {
            return true;
        }

        // Check for the correct type
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // Cast the incoming object as an Author
        Author otherAuthor = (Author) o;

        // Two authors are considered the same if they have the same name
        return authorName.equals(otherAuthor.getAuthorName());
    }

    // Ensures that only Authors with the same name
    @Override
    public int hashCode() {
        return Objects.hash(authorName);
    }
}
