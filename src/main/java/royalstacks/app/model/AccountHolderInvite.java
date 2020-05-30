package royalstacks.app.model;

public class AccountHolderInvite {
    private Customer invitee;
    private Account account;
    private String verificationCode;

    //CONSTRUCTORS
    //all args

    public AccountHolderInvite(Customer invitee, Account account, String verificationCode) {
        this.invitee = invitee;
        this.account = account;
        this.verificationCode = verificationCode;
    }

    //default
    public AccountHolderInvite() {}

    //GETTERS AND SETTERS

    public Customer getInvitee() {
        return invitee;
    }

    public void setInvitee(Customer invitee) {
        this.invitee = invitee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
