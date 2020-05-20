package royalstacks.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer extends User {

    private String address;
    private String city;
    private String postalCode;
    private String socialSecurityNumber;
    private boolean isBusinessAccountHolder;
    @ManyToOne
    private Employee accountManager;
    @ManyToMany
    // TODO add mapping after implementing config file hibernate
    private Set<Account> account;

    // CONSTRUCTORS
    public Customer(int userid, String name, String username, String password, String address, String city, String postalCode, String socialSecurityNumber, Employee accountManager, boolean isBusinessAccountHolder) {
        super(userid, name, username, password);
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.socialSecurityNumber = socialSecurityNumber;
        this.accountManager = accountManager;
        this.isBusinessAccountHolder = isBusinessAccountHolder;
    }

    public Customer(String name, String username, String password, String address, String city, String postalCode, String socialSecurityNumber, Employee accountManager, boolean isBusinessAccountHolder) {
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
    public static boolean isSocialSecurityNumberValid(String socialSecurityNumber){
        return isSocialSecurityNumberUnique(socialSecurityNumber) &&
                isSocialSecurityNumberFormatValid(socialSecurityNumber);
    }

    private static boolean isSocialSecurityNumberUnique(String socialSecurityNumber){
        // TODO: body isSocialSecurityNumberUnique
        return true;
    }

    private static boolean isSocialSecurityNumberFormatValid(String socialSecurityNumber){
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public boolean isBusinessAccountHolder() {
        return isBusinessAccountHolder;
    }

    public void setBusinessAccountHolder(boolean businessAccountHolder) {
        isBusinessAccountHolder = businessAccountHolder;
    }

    public Employee getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Employee accountManager) {
        this.accountManager = accountManager;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
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
