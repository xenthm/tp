package author;

import java.util.ArrayList;

public class AuthorList {
    private ArrayList<Author> authorList;

    public AuthorList() {
        this.authorList = new ArrayList<>();
    }

    public boolean hasAuthor(Author author) {
        return authorList.contains(author);
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
        for (Author value : authorList) {
            if (value.equals(author)) {
                return value;
            }
        }

        return null;
    }

    public void addAuthor(Author author) {
        authorList.add(author);
    }

    public int size() {
        return authorList.size();
    }

    public void print() {
        System.out.println("Here are the sla-I mean authors under you! Total: " + authorList.size());
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println((i + 1) + ". " + authorList.get(i).getAuthorName());
        }
    }
}
