package royalstacks.app.model.fakeDataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.AccountRepository;

import java.util.List;

@Service
public class transactionGenerator {

    @Autowired
    private AccountRepository accountRepository;

    public transactionGenerator() {
    }

    public List<Transaction> generateTransactions(int amount){
        Iterable<Account> allAccounts= accountRepository.findAll();
        return null;
    }



    /*    this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.description = description;
        this.date = date;*/

}
