package royalstacks.app.backingBean;

import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.PrivateAccount;

public class OpenAccountBackingBean {
    public static final int START_BALANCE = 0;
    public static final String EMPTY_STRING = "";
    private String companyName;
    private String kvkNumber;
    private String vatNumber;
    private String sector;

    public OpenAccountBackingBean() {
        this(EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING);
    }



    public OpenAccountBackingBean(String companyName, String kvkNumber, String vatNumber, String sector) {
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.vatNumber = vatNumber;
        this.sector = sector;
    }


    public BusinessAccount businessAccount(){
        String accountNumber = generateAccountNumber();
        BusinessAccount businessAccount = new BusinessAccount(accountNumber, START_BALANCE, companyName,kvkNumber,vatNumber,sector);
        return businessAccount;

    }

    public PrivateAccount privateAccount(){
        String accountNumber = generateAccountNumber();
        PrivateAccount privateAccount = new PrivateAccount(accountNumber,START_BALANCE);
        return privateAccount;
    }

    public static OpenAccountBackingBean createOpenAccountBean(BusinessAccount businessAccount){
        OpenAccountBackingBean bb = new OpenAccountBackingBean(businessAccount.getCompanyName(),businessAccount.getKvkNumber(),
                businessAccount.getVatNumber(),businessAccount.getSector());
        return bb;
    }

    public String generateAccountNumber(){
        // TODO: body generateAccountNumber
        return String.valueOf((int)(Math.random() * 100000));
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
}
