package royalstacks.app.backingBean;

import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.PrivateAccount;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountDetailsBackingBean {

    private int accountId;
    private String account;
    private String formatDateTime;
    private String accountType;
    private String accountNumber;
    private double balance;
    private String companyName;
    private String kvkNumber;
    private String vatNumber;
    private String sector;
    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public AccountDetailsBackingBean(String accountNumber, double balance) {
        this(null,accountNumber,balance);
        this.formatDateTime =  now.format(formatter);

    }


    public AccountDetailsBackingBean(String accountType, String accountNumber, double balance) {
        this.formatDateTime =  now.format(formatter);
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;



    }



    public PrivateAccount privateAccount(){
        return new PrivateAccount(accountNumber,balance);

    }

    public BusinessAccount businessAccount(){
        return new BusinessAccount(accountNumber,balance,companyName,kvkNumber,vatNumber,sector);
    }

    public static AccountDetailsBackingBean createBeanPrivate(PrivateAccount account){
        AccountDetailsBackingBean bb =
                new AccountDetailsBackingBean(account.getAccountNumber(),account.getBalance());
        bb.setAccountType("Private Account");
        bb.setAccountId(account.getAccountId());

        return bb;

    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFormatDateTime() {
        return formatDateTime;
    }

    public void setFormatDateTime(String formatDateTime) {
        this.formatDateTime = formatDateTime;
    }

    public static AccountDetailsBackingBean createBeanBusiness(BusinessAccount account){
        AccountDetailsBackingBean bb =
                new AccountDetailsBackingBean(account.getAccountNumber(),account.getBalance());
        bb.setAccountType("Business Account");
        bb.setAccountId(account.getAccountId());
        return bb;

    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(String kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDetailsBackingBean{" +
                "dateTime="  +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
