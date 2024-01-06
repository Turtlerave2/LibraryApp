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

    //leo

    /**
     * Constructs a new BookDao with the specified database name.
     *
     * @param databaseName The name of the database to be used by the BookDao.
     */
    public BookDao(String databaseName) {
        super(databaseName);
    }

    /**
     * Retrieves a list of all books from the database
     *
     * @return A list of book objects containing info about all the books
     * @throws DaoException if an error occurs in the database operation
     */
    //leo
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
}