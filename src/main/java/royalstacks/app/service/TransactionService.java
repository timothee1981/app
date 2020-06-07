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
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction transaction;
    private Account fromAccount;
    private Account toAccount;


    public boolean executeTransaction(Transaction t){
        this.transaction = t;
        this.fromAccount = accountService.getAccountById(transaction.getFromAccountId());
        this.toAccount = accountService.getAccountById(transaction.getToAccountId());

        if(isTransactionValid()) {
            updateBalances();
            saveTransaction();
            return true;
        } else {
            return false;
        }
    }

    private boolean isTransactionValid(){
        return this.fromAccount.equals(this.toAccount) || this.fromAccount.getBalance() < this.transaction.getAmount() ;
    }

    private void updateBalances(){
        this.fromAccount.subtractAmount(this.transaction.getAmount());
        accountService.saveAccount(this.fromAccount);
        this.toAccount.addAmount(this.transaction.getAmount());
        accountService.saveAccount(this.toAccount);
    }

    private void saveTransaction(){
        transactionRepository.save(this.transaction);
    }


    //MOET GETEST WORDEN
    public List<Transaction> getTenLastTransaction(int accountId){

        List<Transaction> transactions = transactionRepository.getTransactionsByFromAccountIdOrToAccountIdOrderByDateDesc(accountId,accountId);
        List<Transaction> tenLastTransactions = new ArrayList<>();
        for(int index =0; index < 10 && index < transactions.size(); index++){
            tenLastTransactions.add(transactions.get(index));
        }

       return  tenLastTransactions;

    }
}
