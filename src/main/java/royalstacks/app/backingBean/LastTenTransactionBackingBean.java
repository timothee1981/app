package royalstacks.app.backingBean;

public class LastTenTransactionBackingBean {
    private String dateTime;
    private String customerName;
    private String bankAccountNumber;
    private String description;
    private String amount;

    public LastTenTransactionBackingBean(String dateTime, String customerName, String bankAccountNumber, String description, String amount) {
        this.dateTime = dateTime;
        this.customerName = customerName;
        this.bankAccountNumber = bankAccountNumber;
        this.description = description;
        this.amount = amount;
    }




    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}