package daos;

import Exceptions.DaoException;
import business.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BookDaoTest {



            BookDao book1 = new BookDao("libraryapp");



    /** Destiny
     * Test the functionality of finding all books in the database.
     */
        @Test
        void testFindAllBooks() {
            try {
                List<Book> books = book1.findAllBooks();
                assertNotNull(books);
                assertFalse(books.isEmpty());

                for (Book book : books) {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getAuthorID());

                }
            } catch (DaoException e) {
                fail("Exception thrown" + e.getMessage());
            }
        }
    /** Destiny
     * Test the functionality of searching books by title in the database.
     */
        @Test
        void testSearchBookTitle() {
            String titleToSearch = "harrypotter";

            try {
                List<Book> books = book1.searchBookTitle(titleToSearch);
                assertNotNull(books);

                for (Book book : books) {
                    assertTrue(book.getTitle().toLowerCase().contains(titleToSearch.toLowerCase()));
                }
            } catch (DaoException e) {
                fail("Exception thrown:" + e.getMessage());
            }
        }
    /** Destiny
     * Test the functionality of borrowing and returning books in the database.
     */
        @Test
        void testBorrowAndReturnBook() {
            int bookIDToBorrow = 1;

            try {
                book1.borrowBook(bookIDToBorrow);

                Book borrowedBook = book1.findAllBooks().stream().filter(book -> book.getBookID() == bookIDToBorrow)
                        .findFirst().orElse(null);
                assertNotNull(borrowedBook);
                assertEquals(1199998, borrowedBook.getTotalCopies());

                book1.returnBook(bookIDToBorrow);

                Book returnBook = book1.findAllBooks().stream()
                        .filter(book -> book.getBookID() == bookIDToBorrow)
                        .findFirst()
                        .orElse(null);
                assertNotNull(returnBook);
                assertEquals(1199999, returnBook.getTotalCopies());
            } catch (DaoException e) {
                fail("Exception throw:" + e.getMessage());
            }
        }
    }
