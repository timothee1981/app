package royalstacks.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public abstract class Account {

    protected final static double STARTING_BALANCE = 0.00;

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
    }

    // generates new account
    public Account() {
        this(generateAccountNumber(),STARTING_BALANCE);
    }


    // METHODS

    private static String generateAccountNumber(){
        // TODO: body generateAccountNumber
        return "";
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
