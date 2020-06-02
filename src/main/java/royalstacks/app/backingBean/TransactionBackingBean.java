package royalstacks.app.backingBean;

import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;

import java.util.Date;

public class TransactionBackingBean {

    private int transactionId;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String description;
    private Date date;

    AccountService as = new AccountService();

    public TransactionBackingBean(String fromAccount, String toAccount, double amount, String description) {
         this.fromAccount = as.getAccountByAccountNumber(fromAccount).get();
         this.toAccount = as.getAccountByAccountNumber(toAccount).get();
         this.amount = amount;
         this.description = description;
         this.date = new Date();
    }

    public Transaction Transaction(){
        return new Transaction(fromAccount, toAccount, amount, description, date);
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
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
