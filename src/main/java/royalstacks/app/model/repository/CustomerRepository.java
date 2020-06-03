package royalstacks.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT u FROM Customer u WHERE u.BSN = ?1")
    Optional<Customer> findCustomerByBSN(String BSN);



}
