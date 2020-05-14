package royalstacks.app.model;

public class PrivateAccount extends Account {

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
