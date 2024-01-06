package daos;

import Exceptions.DaoException;
import business.Book;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookDaoInterfaceTest {
private BookDaoInterface bookDao;

void setUp() {
    bookDao = mock(BookDaoInterface.class);
}

@Test
    Void testFindAllBooks(){
    try {
        when(bookDao.findAllBooks()).thenReturn(Arrays.asList(new Book("Book1"), new Book("Book2")));

        List<Book> books = bookDao.findAllBooks();
        assertNotNull(books);
        assertEquals(2, books.size());
    } catch (DaoException e) {
        fail("Exception should not be thrown" + e.getMessage());
    }
}

@Test
    void testSearchBookByTitle() {
    String titleToSearch = "Sample Title";
    when(bookDao.searchBookByTitle(titleToSearch).thenReturn(Arrays.asList(new Book(titleToSearch)));
}


}
