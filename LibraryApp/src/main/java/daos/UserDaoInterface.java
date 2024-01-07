package daos;

import business.User;

import java.util.List;

public interface UserDaoInterface {

    List<User> findAllUsers();

    User findUserByUsernamePassword(String uname, String pword);

    User findUserById(int id);

    int addUser(String uname, String pword, String fName, String lName, String email, String address1, String address2, String eircode, String phoneNumber, String registrationDate);

    int changePassword(String username, String oldPass, String newPass);

    List<User> findAllUsersContainingUsername(String username);
}
