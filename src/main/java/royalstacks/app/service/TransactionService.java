package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository transactionRepository;



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

    public List<Transaction> getTenLastTransaction(int accountId){

        List<Transaction> transactions = transactionRepository.getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(accountId,accountId);
        List<Transaction> tenLastTransactions = new ArrayList<>();
        for(int index =0; index < 10; index++){
            tenLastTransactions.add(transactions.get(index));
        }

       return  transactions;

    }
}
