package royalstacks.app.backingBean;

import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

public class CustomerBackingBean {

    private int userid;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String BSN;
    // hier motten we nog wat mee
    private boolean isBusinessAccountHolder;
    @ManyToOne
    private Employee accountManager;
    @ManyToMany
    // TODO add mapping after implementing config file hibernate
    private Set<Account> account;

    public CustomerBackingBean(String username, String password, String firstName, String lastName, String emailAddress, String address, String city, String postalCode, String phoneNumber,String BSN) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.BSN = BSN;
    }

    public Customer customer() {
        Customer customer = new Customer(username, password, firstName, lastName, emailAddress, address, city, postalCode, phoneNumber, BSN, null, false);
        return customer;
    }

    //TODO nog een constructor maken voor backing bean met lijst van accounts voor ophalen uit DB?

    // maakt een backing bean van een customer
    public static CustomerBackingBean createCustomerBackingBean(Customer customer) {
        CustomerBackingBean cbb =
                new CustomerBackingBean(customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(),
                        customer.getAddress(), customer.getCity(), customer.getPostalCode(), customer.getPhoneNumber(), customer.getBSN());
                return cbb;
    }

    @Override
    public String toString() {
        return "CustomerBackingBean{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", BSN='" + BSN + '\'' +
                ", isBusinessAccountHolder=" + isBusinessAccountHolder +
                ", accountManager=" + accountManager +
                ", account=" + account +
                '}';
    }
}


