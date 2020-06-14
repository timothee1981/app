package royalstacks.app.model.pos;

import java.math.BigDecimal;

public class PaymentData {

    private String identificationNumber;
    private String account;
    private String pin;

    public PaymentData() {

    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "identificationNumber=" + identificationNumber +
                ", account='" + account + '\'' +
                ", pin=" + pin +
                '}';
    }
}
