package daos;

import Exceptions.DaoException;
import business.Book;
import daos.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDao extends Dao {


    /** Destiny
     * Constructs a new BookDao with the specified database name.
     *
     * @param databaseName The name of the database to be used by the BookDao.
     */
    public BookDao(String databaseName) {
        super(databaseName);
    }

    /** Destiny
     * Retrieves a list of all books from the database
     *
     * @return A list of book objects containing info about all the books
     * @throws DaoException if an error occurs in the database operation
     */

    public List<Book> findAllBooks() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();

        try {
            con = getConnection();

            String query = "Select * from book";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int bookID = rs.getInt("Bookid");
                String title = rs.getString("Title").trim();
                int authorID = rs.getInt("AuthorID");
                int ISBN = rs.getInt("ISBN");
                int publicationYear = rs.getInt("PublicationYear");
                int genreID = rs.getInt("GenreID");
                int totalCopies = rs.getInt("TotalCopies");
                String description = rs.getString("Description").trim();

                Book book = new Book(bookID, title, authorID, ISBN, publicationYear, genreID, totalCopies, description);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllBooks() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return books;
    }
    /** Destiny
     * Searches for a book by title in the database.
     *
     * @param title The title of the book to search for.
     * @return A list of book objects matching the search criteria.
     * @throws DaoException if an error occurs in the database operation.
     */
        public List<Book> searchBookTitle(String title) throws DaoException {
            Connection con = null;
            PreparedStatement ps = null;

            ResultSet rs = null;

            List<Book> books = new ArrayList<>();

            try {
                con = getConnection();

                String query = "SELECT * FROM book WHERE Title LIKE ?";
                ps = con.prepareStatement(query);
                ps.setString(1, "%" + title + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    int bookID = rs.getInt("Bookid");
                    String bookTitle = rs.getString("Title").trim();
                    int authorID = rs.getInt("AuthorID");
                    int ISBN = rs.getInt("ISBN");
                    int publicationYear = rs.getInt("PublicationYear");
                    int genreID = rs.getInt("GenreID");
                    int totalCopies = rs.getInt("TotalCopies");
                    String description = rs.getString("Description").trim();

                    Book book = new Book(bookID, bookTitle, authorID, ISBN, publicationYear, genreID, totalCopies, description);
                    books.add(book);

                }
            } catch (SQLException e) {
                throw new DaoException("searchBookByTitle" + e.getMessage());
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        freeConnection(con);
                    }

                } catch (SQLException e) {
                    throw new DaoException(e.getMessage());
                }
            }
            return books;
    }
    /** Destiny
     * Borrows a book by updating the total copies available in the database.
     *
     * @param bookID The ID of the book to be borrowed.
     * @throws DaoException if an error occurs in the database operation.
     */
    public void borrowBook(int bookID) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "UPDATE book SET TotalCopies = TotalCopies -1 WHERE Bookid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("borrowBook()" + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw  new DaoException(e.getMessage());

            }
        }
    }
    /** Destiny
     * Returns a borrowed book by updating the total copies available in the database.
     *
     * @param bookID The ID of the book to be returned.
     * @throws DaoException if an error occurs in the database operation.
     */
    public void returnBook(int bookID) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE book SET TotalCopies = TotalCopies +1 WHERE Bookid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("returnBook() " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
    }
}