package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;

@Service
public class TransactionService {

    @Autowired
    AccountService accountService;

    public boolean executeTransaction(Transaction t){
        Account fromAccount = accountService.getAccountById(t.getFromAccountId());
        Account toAccount = accountService.getAccountById(t.getToAccountId());

        if(fromAccount.getBalance() < t.getAmount()){
            return false;
        }

        if(fromAccount.equals(toAccount)){
            return false;
        }

        fromAccount.subtractAmount(t.getAmount());
        toAccount.addAmount(t.getAmount());
        accountService.saveAccount(fromAccount);
        accountService.saveAccount(toAccount);

        return true;
    }
}
