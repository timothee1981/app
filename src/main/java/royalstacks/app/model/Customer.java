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
    public boolean isSocialSecurityNumberUnique(){
        // TODO: body isSocialSecurityNumberUnique
        return true;
    }

    public boolean isSocialSecurityNumberFormatValid(){
        return this.socialSecurityNumber.matches("\\d{9}");
    }

    public boolean isAddressValid(){
        // trim eventuele spaties van begin en eind
        this.address = this.address.trim();
        return this.address.matches("^([1-9][e][\\s])*([a-zA-Z]+(([\\.][\\s])|([\\s]))?)+[1-9][0-9]*(([-][1-9][0-9]*)|([\\s]?[a-zA-Z]+))?$");
    }

    public boolean isPostalCodeValid() {
        // verwijder eventuele spaties en maak van alle letters hoofdletters
        this.postalCode = this.postalCode.replace(" ", "");
        this.postalCode = this.postalCode.toUpperCase();

        // check of postcode bestaat uit 4 getallen en 2 letters
        return this.postalCode.matches("\\d{4}[A-Z]{2}");
    }

    public boolean isCityValid(){
        // trim eventuele spaties van begin en eind
        this.city = city.trim();

        // moet beginnen met een hoofdletter of ' en minimaal 2 letters lang zijn
        return this.city.matches("[A-Z']?[a-zA-Z _']+");
    }


    // GETTERS EN SETTERS
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
