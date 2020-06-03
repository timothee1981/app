package royalstacks.app.backingBean;

import org.springframework.beans.factory.annotation.Autowired;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;

import java.util.Date;
import java.util.Optional;

public class TransactionBackingBean {

    @Autowired
    private AccountService accountService;

    private int transactionId;
    private String fromAccountNumber;
    private String toAccountNumber;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String description;
    private Date date;

    public TransactionBackingBean(String fromAccountNumber, String toAccountNumber, double amount, String description) {
         this.fromAccountNumber = fromAccountNumber;
         this.toAccountNumber = toAccountNumber;
         this.amount = amount;
         this.description = description;
         this.date = new Date();
    }

    public Optional<Transaction> Transaction(){
        Optional<Account> fromAccountOptional = accountService.getAccountByAccountNumber(fromAccountNumber);
        Optional<Account> toAccountOptional = accountService.getAccountByAccountNumber(toAccountNumber);

        Account fromAccount;
        Account toAccount;

        if (fromAccountOptional.isPresent()) {
            fromAccount = fromAccountOptional.get();
        } else {
            return Optional.empty();
        }

        if (toAccountOptional.isPresent()) {
            toAccount = toAccountOptional.get();
        } else {
            return Optional.empty();
        }

        return Optional.of(new Transaction(fromAccount, toAccount, amount, description, date));
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionBackingBean{" +
                "transactionId=" + transactionId +
                ", fromAccountNumber=" + fromAccountNumber +
                ", toAccountNumber=" + toAccountNumber +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
