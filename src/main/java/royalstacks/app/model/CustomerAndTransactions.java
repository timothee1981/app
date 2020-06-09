package royalstacks.app.model;

import java.math.BigDecimal;

public class CustomerAndTransactions implements Comparable<CustomerAndTransactions> {

    private String firstName;
    private String lastName;
    private int numberOfTransactions;
    private BigDecimal balance;

    //CONSTRUCTOR
    public CustomerAndTransactions(String firstName, String lastName, int numberOfTransactions, BigDecimal balance) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public int compareTo(CustomerAndTransactions o) {
        return (o.getNumberOfTransactions() - this.getNumberOfTransactions());
    }
}
