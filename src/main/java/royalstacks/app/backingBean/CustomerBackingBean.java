package royalstacks.app.backingBean;

import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

public class CustomerBackingBean {

    private int userid;
    private String name;
    private String password;
    private String username;
    private String address;
    private String city;
    private String postalCode;
    private String socialSecurityNumber;
    // hier motten we nog wat mee
    private boolean isBusinessAccountHolder;
    @ManyToOne
    private Employee accountManager;
    @ManyToMany
    // TODO add mapping after implementing config file hibernate
    private Set<Account> account;

    public CustomerBackingBean(String name, String password, String username, String address, String city, String postalCode, String socialSecurityNumber) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Customer customer() {
        Customer customer = new Customer(name, username, password, address, city, postalCode, socialSecurityNumber, null, false);
        return customer;
    }

    //TODO nog een constructor maken voor backing bean met lijst van accounts voor ophalen uit DB?

    // maakt een backing bean van een customer
    public static CustomerBackingBean createCustomerBackingBean(Customer customer) {
        CustomerBackingBean cbb =
                new CustomerBackingBean(customer.getName(), customer.getPassword(), customer.getUsername(),
                        customer.getAddress(), customer.getCity(), customer.getPostalCode(), customer.getSocialSecurityNumber());
                return cbb;
    }


}


