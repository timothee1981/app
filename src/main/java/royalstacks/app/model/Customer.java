package royalstacks.app.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Suzanne & Wesley
 */

@Entity
public class Customer extends User {

    private String email;
    private String postalCode;
    private String houseNumber;
    private String suffix;
    private String city;
    private String phoneNumber;
    private String BSN;
    private boolean isBusinessAccountHolder;

    @ManyToOne
    private Employee accountManager;
    @ManyToMany(mappedBy = "accountHolders")
    // TODO add mapping after implementing config file hibernate
    private Set<Account> account;

    // CONSTRUCTORS
    // all args
    public Customer(int userid, String username, String password, String firstName, String lastName, String email, String postalCode, String houseNumber, String suffix, String city, String phoneNumber, String BSN, Employee accountManager, boolean isBusinessAccountHolder) {
        super(userid, username, password, firstName, lastName);
        this.email = email;
        this.houseNumber = houseNumber;
        this.suffix = suffix;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.BSN = BSN;
        this.accountManager = accountManager;
        this.isBusinessAccountHolder = isBusinessAccountHolder;
    }

    // om customer op te slaan in DB
    public Customer(String username, String password, String firstName, String lastName, String email, String postalCode, String houseNumber, String suffix, String city, String phoneNumber, String BSN, Employee accountManager, boolean isBusinessAccountHolder) {
        super(username, password, firstName, lastName);
        this.email = email;
        this.houseNumber = houseNumber;
        this.suffix = suffix;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.BSN = BSN;
        this.accountManager = accountManager;
        this.isBusinessAccountHolder = isBusinessAccountHolder;
    }

    // wordt gebruikt samen met de backing bean
    public Customer() { }


    // GETTERS EN SETTERS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String address) {
        this.houseNumber = address;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBSN() {
        return BSN;
    }

    public void setBSN(String BSN) {
        this.BSN = BSN;
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
    public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            boolean eq = super.equals(o);
            if (eq && o instanceof Customer) {
                        return isBusinessAccountHolder == customer.isBusinessAccountHolder &&
                        Objects.equals(email, customer.email) &&
                        Objects.equals(houseNumber, customer.houseNumber) &&
                        Objects.equals(suffix, customer.suffix) &&
                        Objects.equals(city, customer.city) &&
                        Objects.equals(postalCode, customer.postalCode) &&
                        Objects.equals(phoneNumber, customer.phoneNumber) &&
                        Objects.equals(BSN, customer.BSN) &&
                        Objects.equals(accountManager, customer.accountManager);
            } else{
                return false;
            }
        }


    @Override
    public int hashCode() {
        return Objects.hash(email, postalCode, houseNumber, suffix, city, phoneNumber, BSN, isBusinessAccountHolder, accountManager, account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", suffix='" + suffix + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", BSN='" + BSN + '\'' +
                ", isBusinessAccountHolder=" + isBusinessAccountHolder +
                ", accountManager=" + accountManager +
                ", account=" + account +
                '}';
    }
}
