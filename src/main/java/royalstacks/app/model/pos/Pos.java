package royalstacks.app.model.pos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Pos {

    @Id
    @GeneratedValue
    protected int id;
    private String businessAccountNumber;
    private int identificationNumber;
    private BigDecimal pendingAmount;
    private String clientAccountNumber;

    // CONSTRUCTORS
    public Pos(String businessAccountNumber, int identificationNumber, String clientAccountNumber) {
        this.businessAccountNumber = businessAccountNumber;
        this.identificationNumber = identificationNumber;
        this.clientAccountNumber = clientAccountNumber;

    }

    public Pos() {
    }


    // GETTERS & SETTERS
    public String getBusinessAccountNumber() {
        return businessAccountNumber;
    }

    public void setBusinessAccountNumber(String businessAccountNumber) {
        this.businessAccountNumber = businessAccountNumber;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public BigDecimal getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(BigDecimal pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "id=" + id +
                ", businessAccountNumber='" + businessAccountNumber + '\'' +
                ", identificationNumber=" + identificationNumber +
                ", pendingAmount=" + pendingAmount +
                ", clientAccountNumber='" + clientAccountNumber + '\'' +
                '}';
    }
}
