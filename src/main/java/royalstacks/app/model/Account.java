package royalstacks.app.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
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
    public boolean addAmount(double addedAmount){

        // add amounts
        double result = balance + addedAmount;

        // check for overflow-exception
        if(Double.isInfinite(result) || Double.isNaN(result)){
            return false;
        }

        // all is correct, set new amount to balance
        setBalance(result);
        return true;
    }

    public boolean subtractAmount(double subtractedAmount){
        if(subtractedAmount > balance){
            return false;
        }
        else{
            setBalance(balance - subtractedAmount);
            return true;
        }
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
