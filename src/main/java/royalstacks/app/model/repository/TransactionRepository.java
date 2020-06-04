package royalstacks.app.model.repository;

import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(int fromAccountId, int toAccountId);
}
