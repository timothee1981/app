package royalstacks.app.model;

public class BusinessAccount extends Account {

    private String companyName;
    private String kvkNumber;
    private String vatNumber;
    private String sector;

    // CONSTRUCTORS
    public BusinessAccount(String accountNumber, double balance, String companyName, String kvkNumber, String vatNumber, String sector) {
        super(accountNumber, balance);
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.vatNumber = vatNumber;
        this.sector = sector;
    }

    public BusinessAccount(String companyName, String kvkNumber, String vatNumber, String sector) {
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.vatNumber = vatNumber;
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "BusinessAccount{" +
                "companyName='" + companyName + '\'' +
                ", kvkNumber='" + kvkNumber + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", sector='" + sector + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
