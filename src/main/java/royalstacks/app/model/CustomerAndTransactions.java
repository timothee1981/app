package royalstacks.app.model;

public class CustomerAndTransactions implements Comparable<CustomerAndTransactions> {

    private String firstName;
    private String lastName;
    private int numberOfTransactions;
    private double balance = 0.0;

    //CONSTRUCTOR
    public CustomerAndTransactions(String firstName, String lastName, int numberOfTransactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfTransactions = numberOfTransactions;
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
