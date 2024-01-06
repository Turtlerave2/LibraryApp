package daos;

import Exceptions.DaoException;
import business.Loans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDao extends Dao{
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
     * @throws DaoException if error occurs while getting the active loans or connecting to
     * the database.
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
     * @throws DaoException if error occurs while getting the loans or connecting to database.
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
}
