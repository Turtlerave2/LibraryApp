package daos;

import Exceptions.DaoException;
import business.Book;

import java.util.List;

public interface BookDaoInterface {
    /**Destiny
     * Retrieves a list of all books from the database.
     *
     * @return A list of book objects containing information about all the books.
     * @throws DaoException if an error occurs in the database operation.
     */

    List<Book> findAllBooks() throws DaoException;

    /** Destiny
     * Searches for a book by title in the database.
     *
     * @param title The title of the book to search for.
     * @return A list of book objects matching the search criteria.
     * @throws DaoException if an error occurs in the database operation.
     */
List<Book> searchBookByTitle(String title) throws DaoException;
    /** Destiny
     * Borrows a book by updating the total copies available in the database.
     *
     * @param bookID The ID of the book to be borrowed.
     * @throws DaoException if an error occurs in the database operation.
     */
void borrowBook(int bookID) throws DaoException;
    /** Destiny
     * Returns a borrowed book by updating the total copies available in the database.
     *
     * @param bookID The ID of the book to be returned.
     * @throws DaoException if an error occurs in the database operation.
     */
void returnBook(int bookID) throws DaoException;

}