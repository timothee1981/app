package royalstacks.app.backingBean;

import royalstacks.app.model.Account;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;

import java.time.LocalDateTime;
import java.util.Date;

public class TransactionBackingBean {

    private int transactionId;
    private String fromAccountNumber;
    private String toAccountNumber;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String description;
    private LocalDateTime date;

    AccountService as = new AccountService();

    public TransactionBackingBean(String fromAccountNumber, String toAccountNumber, double amount, String description) {
         this.fromAccountNumber = fromAccountNumber;
         this.toAccountNumber = toAccountNumber;
         this.amount = amount;
         this.description = description;
         this.date = LocalDateTime.now();
    }

    public Transaction Transaction(){
        return new Transaction(date, amount, fromAccount, toAccount, description);
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
