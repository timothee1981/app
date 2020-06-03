package royalstacks.app.model;

import javax.persistence.Entity;

@Entity
public class PrivateAccount extends Account  {

    // CONSTRUCTORS
    public PrivateAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public PrivateAccount() {
    }

    @Override
    public String toString() {
        return "PrivateAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
