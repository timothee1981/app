package royalstacks.app.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    protected final static double STARTING_BALANCE = 1337.00;

    @Id
    @GeneratedValue
    protected int accountId;
    protected String accountNumber;
    protected double balance;
    @ManyToMany
    protected Set<Customer> accountHolders;

    // CONSTRUCTORS

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        accountHolders = new HashSet<>();
    }

    // generates new account
    public Account() {
        this("",STARTING_BALANCE);
    }

    //METHODS
    public boolean hasSufficientBalance(double sentAmount){
    return this.balance >= sentAmount;
    }

    public void subtractAmount(double subtractAmount){
        this.setBalance(this.getBalance() - subtractAmount);
    }

    public void addAmount(double addAmount){
        this.setBalance(this.getBalance() + addAmount);
    }

    public void addAccountHolder(Customer accountHolderToAdd){
        // als accountholder nog niet bestaat, voeg toe
        if(! (accountHolders.contains(accountHolderToAdd))){
            accountHolders.add(accountHolderToAdd);
        }
    }

    //Getters and Setters
    public static double getStartingBalance() {
        return STARTING_BALANCE;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<Customer> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(Set<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
