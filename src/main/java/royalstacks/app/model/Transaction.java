package royalstacks.app.model;


import java.util.Date;

public class Transaction {
    private int transactionId;
    private Date timestamp;
    private double amount;
    private Account fromAccount;
    private Account toAccount;
    private String description;

    // CONSTRUCTORS
    // all args
    public Transaction(int transactionId, Date timestamp, double amount, Account fromAccount, Account toAccount, String description) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.description = description;
    }

    // om op te slaan in database
    public Transaction(Date timestamp, double amount, Account fromAccount, Account toAccount, String description) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.description = description;
    }

    //default
    public Transaction(){}

    // METHODS


    // GETTERS EN SETTERS

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
}
