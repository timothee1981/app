package royalstacks.app.model;

public class CustomerAndTotalBalance {

    private String firstName;
    private String lastName;
    private double totalBalance;

    //CONSTRUCTOR
    public CustomerAndTotalBalance(String firstName, String lastName, double totalBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalBalance = totalBalance;
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

}
