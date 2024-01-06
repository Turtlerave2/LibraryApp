
public interface BookDaoInterface {
    List<Book> findAllUsers();
    Book findallbooks(String uname, String pword);
    Book searchbook(int id);


}