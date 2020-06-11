package royalstacks.app.backingBean;

import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.model.Sector;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountDetailsBackingBean {

    private int accountId;
    private String account;
    private String accountType;
    private String accountNumber;
    private BigDecimal balance;

    public AccountDetailsBackingBean(String accountNumber, BigDecimal balance) {
        this(null,accountNumber,balance);


    }


    public AccountDetailsBackingBean(String accountType, String accountNumber, BigDecimal balance) {

        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
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


    public static AccountDetailsBackingBean createBeanBusiness(BusinessAccount account){
        AccountDetailsBackingBean bb =
                new AccountDetailsBackingBean(account.getAccountNumber(),account.getBalance());
        bb.setAccountType("Business Account");
        bb.setAccountId(account.getAccountId());
        return bb;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDetailsBackingBean{" +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
