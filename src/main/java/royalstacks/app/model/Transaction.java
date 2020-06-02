package royalstacks.app.model;


import java.util.Date;

public class Transaction {
    private int transactionId;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String description;
    private Date date;


    // CONSTRUCTORS
    // all args
    public Transaction(int transactionId, Account fromAccount, Account toAccount, double amount, String description, Date date) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // zonder id
    public Transaction(Account fromAccount, Account toAccount, double amount, String description, Date date) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // default
    public Transaction() {
    }

    // GETTERS & SETTERS
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", timestamp=" + date +
                '}';
    }
}







