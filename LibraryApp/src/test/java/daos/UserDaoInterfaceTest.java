package daos;

import business.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class to validate UserDao functionalities.
 * Tests cover different methods of UserDao to ensure proper functionality.
 * Note: "POP" stands for "Populated".
 */

class UserDaoInterfaceTest {

    /**
     * Nested class containing test cases for UserDao methods.
     */
    @Nested
    class UserDaoTest {
        private UserDao userDao;
        /**
         * Setting up UserDao instance before each test method execution.
         */
        @BeforeEach
        public void setUp() {
            userDao = new UserDao("libraryapp");
        }
        /**
         * Cleaning up resources after each test method execution.
         */
        @AfterEach
        public void tearDown() {
            userDao = null;
        }



        /**
         * Test case for findAllUsers method.
         */
        @Test
        public void testFindAllUsers() {
            // Empty test method for JUnit execution validation
        }
        /**
         * Test case for findUserByUsernamePassword method.
         */
        @Test
        public void testFindUserByUsernamePassword() {
            // Empty test method for JUnit execution validation
        }
        /**
         * Test case for findUserById method.
         */

        @Test
        public void testFindUserById() {
            // Empty test method for JUnit execution validation
        }
        /**
         * Test case for addUser method.
         */
        @Test
        public void testAddUser() {
            // Empty test method for JUnit execution validation
        }
        /**
         * Test case for changePassword method.
         */
        @Test
        public void testChangePassword() {
            // Empty test method for JUnit execution validation
        }
        /**
         * Test case for findAllUsersContainingUsername method.
         */
        @Test
        public void testFindAllUsersContainingUsername() {
            // Empty test method for JUnit execution validation
        }

        // Test cases with populated outcomes (POP)

        /**
         * Test case with a populated outcome for findAllUsers method.
         * Validates that the returned list of users is not null and contains elements.
         */
        @Test
        public void testFindAllUsersPOP() {
            List<User> users = userDao.findAllUsers();
            assertNotNull(users);
            assertTrue(users.size() > 0);
        }
        /**
         * Test case with a populated outcome for findUserByUsernamePassword method.
         * Checks user details after finding by username and password.
         */
        @Test
        public void testFindUserByUsernamePasswordPOP() {
            User user = userDao.findUserByUsernamePassword("CoolGuy", "password1");
            assertNotNull(user);
            assertEquals("John", user.getFirstName());
            assertEquals("Doe", user.getLastName());
        }
        /**
         * Test case with a populated outcome for findUserById method.
         * Verifies user details after finding by ID.
         */
        @Test
        public void testFindUserByIdPOP() {
            User user = userDao.findUserById(1);
            assertNotNull(user);
            assertEquals("CoolGuy", user.getUsername());
        }
        /**
         * Test case with a populated outcome for addUser method.
         * Ensures successful addition of a new user.
         */
        @Test
        public void testAddUserPOP() {
            int newId = userDao.addUser("TestUser", "testpass", "Test", "User");
            assertTrue(newId > 0);
        }
        /**
         * Test case with a populated outcome for changePassword method.
         * Verifies successful password change.
         */
        @Test
        public void testChangePasswordPOP() {
            int rowsAffected = userDao.changePassword("CoolGuy", "password1", "newpassword");
            assertTrue(rowsAffected > 0);
        }
        /**
         * Test case with a populated outcome for findAllUsersContainingUsername method.
         * Checks users containing a specific string in their username.
         */
        @Test
        public void testFindAllUsersContainingUsernamePOP() {
            List<User> users = userDao.findAllUsersContainingUsername("CoolGuy");
            assertNotNull(users);
            assertTrue(users.size() > 0);
        }
    }

}