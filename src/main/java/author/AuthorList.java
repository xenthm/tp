package author;

import java.util.ArrayList;

public class AuthorList {
    private ArrayList<Author> authorList;

    public AuthorList() {
        this.authorList = new ArrayList<>();
    }

    public boolean hasAuthor(Author author) {
        assert author != null : "author must not be null";
        return authorList.contains(author);
    }

    public boolean hasAuthor(String authorName) {
        return hasAuthor(new Author(authorName));
    }

    /**
     * Searches for existing author in {@code authorList} based on incoming author's
     * name
     * and returns {@code Author} instance in {@code authorList}
     *
     * @param author The incoming author to search for based on name
     * @return {@code Author} instance in {@code authorList}, {@code null} if no
     *         such author
     *         can be found
     */
    public Author getAuthor(Author author) {
        assert author != null : "author must not be null";
        for (Author a : authorList) {
            if (a.equals(author)) {
                return a;
            }
        }

        return null;
    }

    public Author getAuthor(String authorName) {
        for (Author a : authorList) {
            if (a.getAuthorName().equals(authorName)) {
                return a;
            }
        }
        return null;
    }

    public void addAuthor(Author author) {
        assert author != null : "author must not be null";
        authorList.add(author);
    }

    public void deleteAuthor(Author author) {
        assert author != null : "author must not be null";
        authorList.remove(author);
    }

    public ArrayList<Author> getAuthorList() {
        return authorList;
    }

    public int size() {
        return authorList.size();
    }

    public void print() {
        assert size() >= 0 : "authorList.size() must be non-negative";

        System.out.println("Here are the sla-I mean authors under you! Total: " + authorList.size());
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println((i + 1) + ". " + authorList.get(i).getAuthorName());
        }
    }
}
