package daos;
import java.sql.*;
import java.util.Scanner;

public  class BookDaoInterface {

    // database information
static final String DB_URL  = "jdbc:mysql://localhost:3306/";
static final String USER = "root";
static final String PASS = "";

public static void main(String[] args) {
    try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
        System.out.println("Connected to the database");

        // Displaying the main menu
        while (true) {
            System.out.println("\nLibrary System Menu");
            System.out.println("1. Search for a Book");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");

            // Read user choice
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    searchForBook(connection);
                    break;

                case 2:
                    borrowBook(connection);
                    break;
                case 3:
                    returnBook(connection);
                    break;
                case 4:
                    System.out.println("Exiting?");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice Please try again");

            }
        }
    }catch (SQLException e) {
            e.printStackTrace();
        }
}


private static int getUserChoice() {
    System.out.println("Enter your choice:");
    Scanner sc = new Scanner(System.in);
    return sc.nextInt();
}
}

