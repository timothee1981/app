package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
