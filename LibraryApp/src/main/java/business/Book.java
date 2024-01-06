package business;

public class Book {

    private int bookID;
    private String title;
    private int authorID;
    private int ISBN;
    private int publicationYear;
    private int genreID;
    private int totalCopies;
    private String description;

    public Book(int bookID, String title, int authorID, int ISBN, int publicationYear, int genreID, int totalCopies, String description) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.genreID = genreID;
        this.totalCopies = totalCopies;
        this.description = description;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authorID=" + authorID +
                ", ISBN=" + ISBN +
                ", publicationYear=" + publicationYear +
                ", genreID=" + genreID +
                ", totalCopies=" + totalCopies +
                ", description='" + description + '\'' +
                '}';
    }
}

