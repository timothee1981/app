package royalstacks.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer extends User {

    private String address;
    private String city;
    private String postalCode;
    private int socialSecurityNumber;
    private boolean isBusinessAccountHolder;
    @ManyToOne
    private Employee accountManager;
    @ManyToMany
    // TODO add mapping after implementing config file hibernate
    private Set<Account> account;

    // CONSTRUCTORS
    public Customer(int userid, String name, String username, String password, String address, String city, String postalCode, int socialSecurityNumber, Employee accountManager, boolean isBusinessAccountHolder) {
        super(userid, name, username, password);
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.socialSecurityNumber = socialSecurityNumber;
        this.accountManager = accountManager;
        this.isBusinessAccountHolder = isBusinessAccountHolder;
    }

    public Customer(String name, String username, String password, String address, String city, String postalCode, int socialSecurityNumber, Employee accountManager, boolean isBusinessAccountHolder) {
        super(name, username, password);
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.socialSecurityNumber = socialSecurityNumber;
        this.accountManager = accountManager;
        this.isBusinessAccountHolder = isBusinessAccountHolder;
    }

    public Customer() { }

    // METHODS
    public static boolean isSocialSecurityNumberValid(int socialSecurityNumber){
        return isSocialSecurityNumberUnique(socialSecurityNumber) &&
                isSocialSecurityNumberFormatValid(socialSecurityNumber);
    }

    private static boolean isSocialSecurityNumberUnique(int socialSecurityNumber){
        // TODO: body isSocialSecurityNumberUnique
        return true;
    }

    private static boolean isSocialSecurityNumberFormatValid(int socialSecurityNumber){
        // TODO: body isSocialSecurityNumberFormatValid
        return true;
    }

    public static boolean isAddressValid(String address){
        // TODO: body isAddressValid
        return true;
    }

    public static boolean isPostalCodeValid(String postalCode){
        // TODO: body isPostalCodeValid
        return true;
    }

    public static boolean isCityValid(String city){
        // TODO: body isCityValid
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", accountManager=" + accountManager +
                ", isBusinessAccountHolder=" + isBusinessAccountHolder +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
