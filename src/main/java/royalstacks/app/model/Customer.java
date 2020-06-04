package royalstacks.app.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    // METHODS

    public boolean isEmailValid(){
        this.email = this.email.trim();
        // volgt RFC 5322 Official Standard
        return this.email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
                        "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                        "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                        "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                        "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
    }

    public boolean isHouseNumber(){
        this.houseNumber = this.houseNumber.trim();
        return this.houseNumber.matches("^[0-9]{1,6}+([-]\\d{1,5})?$");
    }

    public boolean isPostalCodeValid() {
        this.postalCode = this.postalCode.replace(" ", "");
        this.postalCode = this.postalCode.toUpperCase();

        // Postcode bestaat uit 4 getallen en 2 letters. Begint nooit met een 0 en bevat nooit SS, SA of SD
        return this.postalCode.matches("^[1-9][0-9]{3} ?(?!SA|SD|SS)[A-Z]{2}$");
    }

    public boolean isCityValid(){
        this.city = city.trim();
        return this.city.matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
    }

    public boolean isPhoneNumberValid(){
        // verwijder eventuele - en spaties
        this.phoneNumber = this.phoneNumber.replace(" ", "");
        return  // vast nummer zonder +31
                this.phoneNumber.matches("^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$")
                // of mobiel nummber zonder +31
                || this.phoneNumber.matches("^(((\\+31|0|0031)6)[1-9][0-9]{7})$");
    }

    public boolean isBSNFormatValid(){
        // check  of het uit 9 getallen bestaat
        if(!this.BSN.matches("^[0-9]{9}$")){
            return false;

        } else {
            // voer 11 proef vor BSN uit
            String firstNumbers = this.BSN.substring(0,8);
            int lastNumber = Integer.parseInt(this.BSN.substring(8)) * -1;

            int sum = 0;
            for (int i = 0; i < firstNumbers.length(); i++) {
                sum += firstNumbers.charAt(i) * (this.BSN.length() - i);
            }
            sum += lastNumber;

            return sum%11 == 0;
        }
    }

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
