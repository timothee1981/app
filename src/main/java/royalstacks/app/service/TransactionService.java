package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository transactionRepository;

    public void saveTransaction(Transaction t){
        transactionRepository.save(t);
        System.out.println("**** Saved: " + t);
    }

    public boolean executeTransaction(Transaction t){
        // maak van accountId's die in de transaction staat Accounts
        Account fromAccount = accountService.getAccountById(t.getFromAccountId());
        Account toAccount = accountService.getAccountById(t.getToAccountId());

        if(fromAccount.equals(toAccount)){
            return false;
        }

        if(fromAccount.getBalance() < t.getAmount()){
            return false;
        }

        fromAccount.subtractAmount(t.getAmount());
        accountService.saveAccount(fromAccount);

        toAccount.addAmount(t.getAmount());
        accountService.saveAccount(toAccount);

        saveTransaction(t);

        return true;
    }


    //MOET GETEST WORDEN
    public List<Transaction> getTenLastTransaction(int accountId){

        List<Transaction> transactions = transactionRepository.getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(accountId,accountId);
        List<Transaction> tenLastTransactions = new ArrayList<>();
        for(int index =0; index < 10 && index < transactions.size(); index++){
            tenLastTransactions.add(transactions.get(index));
        }

       return  transactions;

    }
}
