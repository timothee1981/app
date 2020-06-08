package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.model.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TransactionService {

    private static final Logger LOGGER = Logger.getLogger(TransactionService.class.getName());

    private AccountService accountService;
    private TransactionRepository transactionRepository;

    private Transaction transaction;
    private Account fromAccount;
    private Account toAccount;

    @Autowired
    public TransactionService(AccountService as, TransactionRepository tr){
        this.accountService = as;
        this.transactionRepository = tr;
    }

    public final boolean executeTransaction(Transaction t){
        setAttributes(t);

        if(isTransactionValid()) {
            updateBalances();
            saveTransaction();

            LOGGER.log(Level.INFO, "**** Transaction has been executed");
            return true;
        } else {
            LOGGER.log(Level.SEVERE,"**** Transaction FAILED");
            return false;
        }
    }

    private void setAttributes(Transaction t){
        this.transaction = t;
        this.fromAccount = accountService.getAccountById(transaction.getFromAccountId());
        this.toAccount = accountService.getAccountById(transaction.getToAccountId());
    }

    private boolean isTransactionValid(){
        return !this.fromAccount.equals(this.toAccount) || this.fromAccount.getBalance().compareTo(this.transaction.getAmount()) >= 0;
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
