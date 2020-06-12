package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.pos.PendingTransaction;

@Repository
public interface PendingTransactionRepository extends CrudRepository<PendingTransaction, Integer> {
}
