package daos;

import Exceptions.DaoException;
import business.Loans;

import java.util.List;

public interface LoanDaoInterface {
    List<Loans> viewActiveLoans(int memberID) throws DaoException;

    List<Loans> getLoansForMember(int memberId) throws DaoException;

    double getCurrentOverdueFees(int memberID);

    boolean isCreditCardValid(String cardNumber);

    boolean isValidExpiryDate(String expiryDate);

    boolean payLateFeeValidate(int memberId,String cardNumber,String expiryDate);

}
