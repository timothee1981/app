package royalstacks.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import royalstacks.app.model.Account;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,Integer> {

    Optional<Account> findByAccountId(int accountId);

    @Query("SELECT a.accountNumber FROM Account a WHERE a.accountId = (select max(a.accountId) From a)")
    Optional<String> getLastAccountNumber();



}
