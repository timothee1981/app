package royalstacks.app.backingBean;

import org.springframework.beans.factory.annotation.Autowired;
import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;
import java.util.Date;
import java.util.Optional;

public class TransactionBackingBean {

    @Autowired
    AccountService accountService;

    private int transactionId;
    private String fromAccountNumber;
    private String toAccountNumber;
    private Optional<Account> fromAccount;
    private Optional<Account> toAccount;
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

        Account fromAccount;
        Account toAccount;

        // check of beide accounts bestaan
        if (this.fromAccount.isPresent() && this.toAccount.isPresent()) {
            fromAccount = this.fromAccount.get();
            toAccount = this.toAccount.get();
        } else {
            return Optional.empty();
        }

        // check of Account niet naar zichzelf stuurt
        if (toAccount == fromAccount){
            return Optional.empty();
        }

        // check of amount valide is en fromAccount genoeg geld heeft
        if (amount <= 0 && !fromAccount.hasSufficientBalance(amount)){
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

    public Optional<Account>getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Optional<Account> fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Optional<Account> getToAccount() {
        return toAccount;
    }

    public void setToAccount(Optional<Account> toAccount) {
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
