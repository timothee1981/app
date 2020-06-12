package royalstacks.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessAccountRepository extends CrudRepository<BusinessAccount, Integer> {

    @Query("SELECT ba.sector, AVG(ba.balance) AS totalBalance FROM BusinessAccount ba GROUP BY ba.sector ORDER BY totalBalance DESC")
    List<Object[]> findSectorAndAverageBalance();

    @Query("SELECT ba FROM BusinessAccount ba WHERE ba.accountId = ?1")
    Optional<BusinessAccount> findByAccountId(int accountId);

    @Query("SELECT c.firstName, c.lastName, ba.accountId, ba.balance FROM Customer c JOIN c.account ba WHERE TYPE(ba) = BusinessAccount")
    List<Object[]> findCompaniesAndBusinessAccounts();

}
