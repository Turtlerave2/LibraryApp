package daos;

import business.Loans;

import java.util.List;

public interface LoanDaoInterface {
    List<Loans> viewActiveLoans(int memberID);

    List<Loans> getLoansForMember(int memberId);


}
