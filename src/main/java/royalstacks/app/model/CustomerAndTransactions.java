package royalstacks.app.model;

public class CustomerAndTransactions implements Comparable<CustomerAndTransactions> {

    private String firstName;
    private String lastName;
    private int numberOfTransactions;
    private double balance;

    //CONSTRUCTOR
    public CustomerAndTransactions(String firstName, String lastName, int numberOfTransactions, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfTransactions = numberOfTransactions;
        this.balance = balance;
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public int compareTo(CustomerAndTransactions o) {
        return (o.getNumberOfTransactions() - this.getNumberOfTransactions());
    }
}
