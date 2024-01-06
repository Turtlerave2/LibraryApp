package daos;

import business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao implements UserDaoInterface{
    public UserDao(String dbName){
        super(dbName);
    }

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
                int userId = rs.getInt("MemberID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String lastName = rs.getString("Last_Name");
                String firstName = rs.getString("First_Name");
                User u = new User(userId, firstName, lastName, username, password);
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
        return users;     // may be empty
    }

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
                u = new User(userId, firstName, lastName, username, password);
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

    public User findUserById(int id) {
        // Find a user by their ID
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
                u = new User(userId, firstName, lastName, username, password);
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

    public int addUser(String uname, String pword, String fName, String lName) {
        Connection con = null;
        PreparedStatement ps = null;
        int newId = -1;
        ResultSet generatedKeys = null;

        try {
            con = this.getConnection();
            String query = "INSERT INTO members(Username, Password, First_Name, Last_Name) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, uname);
            ps.setString(2, pword);
            ps.setString(3, fName);
            ps.setString(4, lName);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                newId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred during the addUser method: " + e.getMessage());
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

    public List<User> findAllUsersContainingUsername(String username) {
        // Find all users whose username contains a certain string
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
                User u = new User(userId, firstName, lastName, uname, password);
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
}
