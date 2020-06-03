package royalstacks.app.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.CustomerAndTotalBalance;

import javax.persistence.OrderBy;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT u FROM Customer u WHERE u.BSN = ?1")
    Optional<Customer> findCustomerByBSN(String BSN);

    @Query("SELECT c.firstName, c.lastName, SUM(ba.balance) AS totalBalance FROM Customer c JOIN c.account ba GROUP BY c.userid ORDER BY totalBalance DESC")
    List<Object[]> findCustomersAndBusinessAccountBalance(Pageable pageable);
}
