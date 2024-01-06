package daos;

import Exceptions.DaoException;
import business.Loans;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanDaoInterfaceTest {

    public LoanDao loana = new LoanDao("librarytest");

    /**
     * Test the activeloansmethod when member id is passed who has no active loans
     * verifies that the method returns empty
     */
    @Test
    void viewActiveLoansNoSuchMember() {
    int memberId = 5;
        try {
            List<Loans> activeLoans = loana.viewActiveLoans(memberId);
            System.out.println(activeLoans.size());
            Assertions.assertEquals(0,activeLoans.size());

        } catch (DaoException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test the getLoansForMember when member id is passed who has active loans
     * verifies that the method returns the same as in the database
     */
    @Test
    void getLoansForMember() {
        int memberId = 1;
        try {
            List<Loans> activeLoans = loana.getLoansForMember(memberId);
            System.out.println(activeLoans.size());
            Assertions.assertEquals(2,activeLoans.size());

        } catch (DaoException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }

    }

    /**
     * Test the getCurrentOverdueFees when member id is passed who has no overdue fees
     * verifies that the method returns a number less than or equal to 0
     */
    @Test
    void getCurrentOverdueFees() {
        int memberId = 1;
        try {
            double overdueFees = loana.getCurrentOverdueFees(memberId);

             Assertions.assertTrue(overdueFees >= 0);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test the isCreditCardValid with a correct card number being passed
     * verifies that the method returns a true to indicate it is correct
     */
    @Test
    void isCreditCardValid() {
        LoanDao loan = new LoanDao("librarytest");

        String creditCard= "4532015112830366";
        assertTrue(loan.isCreditCardValid(creditCard));
    }
    /**
     * Test the isCreditCardInValid with a incorrect card number being passed
     * verifies that the method returns a false to indicate it is incorrect
     */
    @Test
    void isCreditCardInValid() {
        LoanDao loan = new LoanDao("librarytest");

        String creditCard= "1234-5678-9012-3456";
        String creditCard2= "4111111111111112";
        assertFalse(loan.isCreditCardValid(creditCard));
        assertFalse(loan.isCreditCardValid(creditCard2));
    }

    /**
     * Test the isValidExpiryDate with a correct expiry date being passed
     * verifies that the method returns a true to indicate it is correct
     */
    @Test
    void isValidExpiryDate() {
        LoanDao loan = new LoanDao("librarytest");
        String expiry = "03/2027";

        assertTrue(loan.isValidExpiryDate(expiry));
    }
    /**
     * Test the NotValidExpiryDate with an incorrect expiry date being passed
     * verifies that the method returns a false to indicate it is incorrect
     */
    @Test
    void NotValidExpiryDate() {
        LoanDao loan = new LoanDao("librarytest");
        String expiry = "03/2020";
        //don't know how to check for month, cause will fail if enter say 21 for the month
        assertFalse(loan.isValidExpiryDate(expiry));

    }

    /**
     * Test the payLateFeeValidate with a correct card details being passed
     * verifies that the method returns a true to indicate it the 'payment' has gone through
     */
    @Test
    void payLateFeeValidate() {
        LoanDao loan = new LoanDao("librarytest");

        String creditNumber="4532015112830366";
        String expiry="11/2024";
        boolean result = loan.payLateFeeValidate(1,creditNumber,expiry);
        assertTrue(result);
    }
}