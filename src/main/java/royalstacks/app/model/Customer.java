package royalstacks.app.model;

import royalstacks.app.service.CustomerService;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer extends User {

    private String email;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String BSN;
    private boolean isBusinessAccountHolder;
    @ManyToOne
    private Employee accountManager;
    @ManyToMany
    // TODO add mapping after implementing config file hibernate
    private Set<Account> account;

    // CONSTRUCTORS
    // all args
    public Customer(int userid, String username, String password, String firstName, String lastName, String email, String address, String city, String postalCode, String phoneNumber, String BSN, Employee accountManager, boolean isBusinessAccountHolder) {
        super(userid, username, password, firstName, lastName);
        this.email = email;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.BSN = BSN;
        this.accountManager = accountManager;
        this.isBusinessAccountHolder = isBusinessAccountHolder;
    }

    // om customer op te slaan in DB
    public Customer(String username, String password, String firstName, String lastName, String email, String address, String city, String postalCode, String phoneNumber, String BSN, Employee accountManager, boolean isBusinessAccountHolder) {
        super(username, password, firstName, lastName);
        this.email = email;
        this.address = address;
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

    public boolean isAddressValid(){
        this.address = this.address.trim();
        return this.address.matches("^([1-9][e][\\s])*([a-zA-Z]+(([\\.][\\s])|([\\s]))?)+[1-9][0-9]*(([-][1-9][0-9]*)|([\\s]?[a-zA-Z]+))?$");
    }

    public boolean isPostalCodeValid() {
        this.postalCode = this.postalCode.replace(" ", "");
        this.postalCode = this.postalCode.toUpperCase();

        // Postcode bestaat uit 4 getallen en 2 letters. Begint nooit met een 0 en bevat nooit SS, SA of SD
        return this.postalCode.matches("^[1-9][0-9]{3} ?(?!SA|SD|SS)[A-Z]{2}$");
    }

    public boolean isCityValid(){
        this.city = city.trim();
        return this.city.matches("[A-Z']?[a-zA-Z _']+");
    }

    public boolean isPhoneNumberValid(){
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
            int sum = 0;
            for (int i = 0; i < firstNumbers.length(); i++) {
                sum += firstNumbers.charAt(i) * (this.BSN.length() - i);
            }

            sum += (Integer.parseInt(this.BSN.substring(8)) * -1);
            return sum%11 == 0;
        }
    }

    // TODO wordt in SignUpController geregeld. Deze verwijderen?
    public boolean isBSNUnique(){
        CustomerService cs = new CustomerService();
        return true;
    }

    // GETTERS EN SETTERS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String toString() {
        return "Customer{" +
                "emailAddress='" + email + '\'' +
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
