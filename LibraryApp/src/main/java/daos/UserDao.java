package daos;

import business.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a Data Access Object (DAO) for managing User data in the database.
 * Author: rob
 */

public class UserDao extends Dao implements UserDaoInterface{
    /**
     * Constructs a UserDao object with a specified database name.
     *
     * @param dbName The name of the database
     */
    public UserDao(String dbName){
        super(dbName);
    }


    /**
     * Retrieves all users from the 'members' table.
     *
     * @return A list of User objects representing all users in the database
     */
    public List<User> findAllUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM members";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String lastName = rs.getString("Last_Name");
                String firstName = rs.getString("First_Name");
                String email = rs.getString("Email");
                String address1 = rs.getString("Address1");
                String address2 = rs.getString("Address2");
                String eircode = rs.getString("Eircode");
                String phoneNumber = rs.getString("Phone_Number");
                String registrationDate = rs.getString("Registration_Date");

                User u = new User(firstName, lastName, username, password, email, address1, address2, eircode, phoneNumber, registrationDate);
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the findAllUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) freeConnection(con);
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the findAllUsers() method: " + e.getMessage());
            }
        }
        return users;
    }
    /**
     * Retrieves a user by username and password.
     *
     * @param uname The username of the user
     * @param pword The password of the user
     * @return A User object representing the user if found, otherwise null
     */
    public User findUserByUsernamePassword(String uname, String pword) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            con = this.getConnection();
            String query = "SELECT * FROM members WHERE Username = ? AND Password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);

            rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("MemberID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String lastName = rs.getString("Last_Name");
                String firstName = rs.getString("First_Name");
                String email = rs.getString("Email");
                String address1 = rs.getString("Address1");
                String address2 = rs.getString("Address2");
                String eircode = rs.getString("Eircode");
                String phoneNumber = rs.getString("Phone_Number");
                String registrationDate = rs.getString("Registration_Date");

                u = new User(firstName, lastName, username, password, email, address1, address2, eircode, phoneNumber, registrationDate);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the findUserByUsernamePassword() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) freeConnection(con);
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the findUserByUsernamePassword() method: " + e.getMessage());
            }
        }
        return u;     // u may be null
    }


    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user
     * @return A User object representing the user if found, otherwise null
     */
    public User findUserById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            con = this.getConnection();
            String query = "SELECT * FROM members WHERE MemberID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("MemberID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String lastName = rs.getString("Last_Name");
                String firstName = rs.getString("First_Name");
                String email = rs.getString("Email");
                String address1 = rs.getString("Address1");
                String address2 = rs.getString("Address2");
                String eircode = rs.getString("Eircode");
                String phoneNumber = rs.getString("Phone_Number");
                String registrationDate = rs.getString("Registration_Date");


                u = new User(firstName, lastName, username, password, email, address1, address2, eircode, phoneNumber, registrationDate);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the findUserById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) freeConnection(con);
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the findUserById() method: " + e.getMessage());
            }
        }
        return u;
    }


    /**
     * Adds a new user to the 'members' table.
     *
     * @param uname The username of the new user
     * @param pword The password of the new user
     * @param fName The first name of the new user
     * @param lName The last name of the new user
     * @return The ID of the newly added user, or -1 if the addition fails
     */
    @Override
    public int addUser(String uname, String pword, String fName, String lName, String email, String address1, String address2, String eircode, String phoneNumber, String registrationDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int newId = -1;

        try {
            con = this.getConnection();
            String query = "INSERT INTO members(first_name, last_name, username, password, email, address1, address2, eircode, phone_number, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, uname);
            ps.setString(4, pword);
            ps.setString(5, email);
            ps.setString(6, address1);
            ps.setString(7, address2);
            ps.setString(8, eircode);
            ps.setString(9, phoneNumber);
            ps.setString(10, registrationDate);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                newId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the addUser method:");
            System.err.println("\t" + e.getMessage());
            newId = -1;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("An error occurred when closing down the addUser method:\n" + e.getMessage());
            }
        }
        return newId;
    }


    /**
     * Changes the password of a user.
     *
     * @param username The username of the user
     * @param oldPass  The old password of the user
     * @param newPass  The new password to set
     * @return The number of affected rows (1 if successful, 0 if unsuccessful)
     */

    public int changePassword(String username, String oldPass, String newPass) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try {
            con = this.getConnection();
            String query = "UPDATE members SET Password = ? WHERE Username = ? AND Password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, newPass);
            ps.setString(2, username);
            ps.setString(3, oldPass);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred in the changePassword() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the changePassword() method: " + e.getMessage());
            }
        }
        return rowsAffected;
    }


    /**
     * Finds all users whose username contains a specific string.
     *
     * @param username The partial username to search for
     * @return A list of User objects matching the search criteria
     */
    public List<User> findAllUsersContainingUsername(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM members WHERE Username LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + username + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("MemberID");
                String uname = rs.getString("Username");
                String password = rs.getString("Password");
                String lastName = rs.getString("Last_Name");
                String firstName = rs.getString("First_Name");
                String email = rs.getString("Email");
                String address1 = rs.getString("Address1");
                String address2 = rs.getString("Address2");
                String eircode = rs.getString("Eircode");
                String phoneNumber = rs.getString("Phone_Number");
                String registrationDate = rs.getString("Registration_Date");


                User u = new User( firstName, lastName, uname, password, email, address1, address2, eircode, phoneNumber, registrationDate);
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the findAllUsersContainingUsername() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) freeConnection(con);
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the findAllUsersContainingUsername() method: " + e.getMessage());
            }
        }
        return users;
    }


    public int updateUserProfile(User updatedUser) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try {
            con = this.getConnection();
            String query = "UPDATE users SET First_Name = ?, Last_Name = ?, Email = ?, Address1 = ?, Address2 = ?, Eircode = ?, Phone_Number = ? WHERE Username = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, updatedUser.getFirstName());
            ps.setString(2, updatedUser.getLastName());
            ps.setString(3, updatedUser.getEmail());
            ps.setString(4, updatedUser.getAddress1());
            ps.setString(5, updatedUser.getAddress2());
            ps.setString(6, updatedUser.getEircode());
            ps.setString(7, updatedUser.getPhoneNumber());
            ps.setString(8, updatedUser.getUsername());

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred in the updateUserProfile() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the updateUserProfile() method: " + e.getMessage());
            }
        }
        return rowsAffected;
    }

}
