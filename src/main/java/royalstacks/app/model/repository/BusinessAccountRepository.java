package royalstacks.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.BusinessAccount;
import java.util.List;

@Repository
public interface BusinessAccountRepository extends CrudRepository<BusinessAccount, Integer> {

    @Query("SELECT ba.sector, AVG(ba.balance) AS totalBalance FROM BusinessAccount ba GROUP BY ba.sector ORDER BY totalBalance DESC")
    List<Object[]> findSectorAndAverageBalance();

}
