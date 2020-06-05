package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.AccountHolderInvite;

@Repository
public interface AccountHolderInviteRepository extends CrudRepository<AccountHolderInvite, Integer> {
}
