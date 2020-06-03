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
        // maak van accountId's die in de transaction staat Accounts
        Account fromAccount = accountService.getAccountById(t.getFromAccountId());
        Account toAccount = accountService.getAccountById(t.getToAccountId());

        // check of geld niet verstuurd wordt naar zichzelf
        if(fromAccount.equals(toAccount)){
            return false;
        }

        // check of er genoeg geld op de rekening staat
        if(fromAccount.getBalance() < t.getAmount()){
            return false;
        }

        // muteer balances & sla op
        fromAccount.subtractAmount(t.getAmount());
        accountService.saveAccount(fromAccount);

        toAccount.addAmount(t.getAmount());
        accountService.saveAccount(toAccount);

        return true;
    }
}
