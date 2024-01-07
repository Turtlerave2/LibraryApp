package daos;

import Exceptions.DaoException;
import business.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {
    private BookDaoInterface bookDao;

    void setUp(){
        bookDao = (BookDaoInterface) new BookDao("name");
    }

    void tearDown() {
        bookDao = null;
    }
    @Test
    void testFindAllBooks(){
        try {
            List<Book> books = bookDao.findAllBooks();
            assertNotNull(books);
            assertFalse(books.isEmpty());

            for (Book book: books) {
                assertNotNull(book.getTitle());
                assertNotNull(book.getAuthorID());

            }
        } catch (DaoException e) {
            fail("Exception thrown" + e.getMessage());
        }
    }

    @Test
    void testSearchBookTitle() {
        String titleToSearch = "harrypotter";

        try {
            List<Book> books = bookDao.searchBookByTitle(titleToSearch);
            assertNotNull(books);

            for (Book book : books) {
                assertTrue(book.getTitle().toLowerCase().contains(titleToSearch.toLowerCase()));
            }
        } catch (DaoException e) {
            fail("Exception thrown:" + e.getMessage());
        }
    }

    @Test
    void testBorrowAndReturnBook() {
        int bookIDToBorrow = 1;

        try {
            bookDao.borrowBook(bookIDToBorrow);

            Book borrowedBook = bookDao.findAllBooks().stream().filter(book -> book.getBookID() == bookIDToBorrow)
                    .findFirst().orElse(null);
            assertNotNull(borrowedBook);
            assertEquals(1, borrowedBook.getTotalCopies());

            bookDao.returnBook(bookIDToBorrow);

            Book returnBook = bookDao.findAllBooks().stream()
                    .filter(book -> book.getBookID() == bookIDToBorrow)
                    .findFirst()
                    .orElse(null);
            assertNotNull(returnBook);
            assertEquals(2, returnBook.getTotalCopies());
        } catch (DaoException e) {
            fail ("Exception throw:" + e.getMessage());
        }
    }
}