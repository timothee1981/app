package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
