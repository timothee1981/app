package royalstacks.app.model;

import javax.persistence.Entity;

@Entity
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

    public BusinessAccount() { }

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



    public boolean isCompanyNameFormatValid(){

        return this.companyName.matches("^[\\w@ ]*[^\\W_ ][\\w- \\@\\ \\&\\ \\+]*$");
    }

    public boolean isKvkNameFormatValid(){
        return this.kvkNumber.matches("^[0-9]{8}$");

    }

    public boolean isVatFormatValid(String vat){
        return true;
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
