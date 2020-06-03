package royalstacks.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.Customer;

import java.util.Map;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT u FROM Customer u WHERE u.BSN = ?1")
    Optional<Customer> findCustomerByBSN(String BSN);

    @Query("SELECT u.userid,u.firstName,u.lastName,SUM(a.balance) AS totalbalance FROM User u JOIN Account a JOIN BusinessAccount ba GROUP BY u.userid ORDER BY totalbalance DESC")
    Map<Customer, Double> findTop10BusinessAccounts();
}
