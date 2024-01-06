package daos;

import Exceptions.DaoException;
import business.Loans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class LoanDao extends Dao implements LoanDaoInterface{
    private int memberID;
    private int bookid;

    /**
     * Constructs a new BookDao with the specified database name.
     *
     * @param databaseName The name of the database to be used by the BookDao.
     */
    public LoanDao(String databaseName) {
        super(databaseName);
    }

    /**
     * gets the list of all loans for member
     *
     * the method gets a list of loans from the database for the member
     * if the return date is null then it is active
     *
     * @param memberID the id of the member to get the active loans for them
     * @return a list of the active loans for the member
     */
    public List<Loans> viewActiveLoans(int memberID) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loans> activeLoans = new ArrayList<>();

        try {
            con = getConnection();

            String query = "SELECT * FROM loans WHERE MemberID = ? AND ReturnDate IS NULL AND DueDate > CURDATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, memberID);

            rs = ps.executeQuery();

            while (rs.next()) {
                int loanID = rs.getInt("LoanID");
                int bookID = rs.getInt("BookID");
                Date loanDate = rs.getDate("LoanDate");
                Date dueDate = rs.getDate("DueDate");
                double lateFee = rs.getDouble("LateFee");

                Loans loan = new Loans(loanID, memberID, bookID, loanDate, dueDate, null, lateFee);
                activeLoans.add(loan);
            }
        } catch (SQLException e) {
            throw new DaoException("viewActiveLoans() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }

        return activeLoans;
    }
    //leo
    /**
     * gets list of loans for member
     *
     * the method gets a list of the loans from the database that are
     * linked to the members id
     *
     * @param memberId the id of the member
     * @return a list of loans linked to the member
     */
    public List<Loans> getLoansForMember(int memberId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = getConnection();
        List<Loans> loans = new ArrayList<>();

        String sql = "SELECT LoanID, MemberID, BookID, LoanDate, DueDate, ReturnDate, LateFee " +
                "FROM loans WHERE MemberID = ?" ;
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, memberId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int loanId = resultSet.getInt("LoanID");
                    int bookId = resultSet.getInt("BookID");
                    Date loanDate = resultSet.getDate("LoanDate");
                    Date dueDate = resultSet.getDate("DueDate");
                    Date returnDate = resultSet.getDate("ReturnDate");
                    double lateFee = resultSet.getDouble("LateFee");


                    Loans loan = new Loans(loanId, memberId, bookId, loanDate, dueDate, returnDate, lateFee);
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving loans: " + e.getMessage());
        }

        return loans;

    }

    /**
     *This method retrieves the total amount of fees due for a specific member
     *
     * @param memberID the id of the member to search for, the overdue fees
     * @return the total of overdue fees for the member
     */
    public double getCurrentOverdueFees(int memberID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = getConnection();
        double overdueFees = 0.0;
        String sql = "SELECT SUM(LateFee) AS TotalLateFees "+
                " FROM loans WHERE MemberID = ? AND DueDate < NOW()";
        try(PreparedStatement pss = con.prepareStatement(sql)){
            pss.setInt(1,memberID);

                try (ResultSet rss = pss.executeQuery()){
                        if(rss.next()){
                            overdueFees = rss.getDouble("TotalLateFees");
                        }
                    }
                } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return overdueFees;
    }

    /**
     * validates the credit card number using Luhn algorithm
     *
     * this checks credit length and removes any spaces or anything user might put in
     *
     *
     * @param cardNumber the credit card number to validate
     * @return if credit card number is valid return true otherwise return false
     */
    public  boolean isCreditCardValid(String cardNumber) {

        cardNumber = cardNumber.replaceAll("[^0-9]", "");

        int length = cardNumber.length();
        if (length < 13 || length > 19) {
            return false;
        }
        int sum = 0;
        boolean doubleDigit = false;

        //Luhn algorithm
        for (int i = length - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (sum % 10 == 0);
    }
    //leo
    /**
     * validates the format and checks if date is correct
     *
     * this method checks the format of the date, and it
     * expires in the future cant be less than the current date
     *
     * @param expiryDate
     * @return
     */
    public boolean isValidExpiryDate(String expiryDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            Date currentDate = new Date();
            Date parsedExpiryDate = dateFormat.parse(expiryDate);

            return currentDate.before(parsedExpiryDate);
        } catch (ParseException e) {
            return false;
        }
    }

     //leo
    /**
     * checks and processes the payment for a late fee for a member
     *
     * this method validates credit card info calling the 2 other methods to check.
     * once valid then processes payment for late fee. if both are true proceed onwards
     * otherwise return false.
     *
     *
     * @param memberId id of the member making payment for late fee
     * @param cardNumber the members credit card number
     * @param expiryDate the expiry date of the credit card
     * @return true if the payment was successful and credit card info is correct
     * @throws RuntimeException if error occurs with payment or database connection
     */
    public boolean payLateFeeValidate(int memberId,String cardNumber,String expiryDate) {

        if (!isCreditCardValid(cardNumber)) {
            return false;
        }
        if(!isValidExpiryDate(expiryDate)){
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String sql = "UPDATE loans Set LateFee = 0.00 where memberId =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, memberId);

            int rows = ps.executeUpdate();

            return rows>0;

        } catch (DaoException e) {
            throw new RuntimeException("Error paying late fee: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error occurred");
            }


        }
    }


  }

