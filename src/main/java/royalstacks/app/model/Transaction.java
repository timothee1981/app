package royalstacks.app.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Transactions")
public class Transaction {
    @Id
    private String transactionId;
    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime date;
    private double amount;
    private Account fromAccount;
    private Account toAccount;
    private String description;

    // CONSTRUCTORS
    // all args
    public Transaction(String transactionId, LocalDateTime date, double amount, Account fromAccount, Account toAccount, String description) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.description = description;
    }

    // om op te slaan in database
    public Transaction(LocalDateTime date, double amount, Account fromAccount, Account toAccount, String description) {
        this.date = date;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.description = description;
    }

    //default
    public Transaction(){}

    // METHODS


    // GETTERS EN SETTERS

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}







