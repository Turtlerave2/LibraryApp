package daos;

import business.User;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao("libraryapp");
    }

    @AfterEach
    public void tearDown() {
        userDao = null;
    }

    @Test
    public void testFindAllUsers() {
    }

    @Test
    public void testFindUserByUsernamePassword() {
    }

    @Test
    public void testFindUserByUsernamePassword() {
    }

    @Test
    public void testFindUserById() {
    }

    @Test
    public void testAddUser() {
    }

    @Test
    public void testChangePassword() {
    }
    
    @Test
    public void testFindAllUsersContainingUsername() {

    }

    @Test
    public void testFindAllUsers() {
        List<User> users = userDao.findAllUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testFindUserByUsernamePassword() {
        User user = userDao.findUserByUsernamePassword("CoolGuy", "password1");
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testFindUserById() {
        User user = userDao.findUserById(1);
        assertNotNull(user);
        assertEquals("CoolGuy", user.getUsername());
    }

    @Test
    public void testAddUser() {
        int newId = userDao.addUser("TestUser", "testpass", "Test", "User");
        assertTrue(newId > 0);
    }

    @Test
    public void testChangePassword() {
        int rowsAffected = userDao.changePassword("CoolGuy", "password1", "newpassword");
        assertTrue(rowsAffected > 0);
    }

    @Test
    public void testFindAllUsersContainingUsername() {
        List<User> users = userDao.findAllUsersContainingUsername("CoolGuy");
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }
}
