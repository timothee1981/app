package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.BusinessAccount;

public interface BusinessAccountRepository extends CrudRepository<BusinessAccount,Integer> {
}
