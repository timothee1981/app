package royalstacks.app.model.repository;

import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
