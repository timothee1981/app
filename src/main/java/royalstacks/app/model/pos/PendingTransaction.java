package royalstacks.app.model.pos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class PendingTransaction {

    @Id
    @GeneratedValue
    protected int id;
    private BigDecimal amount;
    @OneToOne
    private Pos pos;
    private int clientAccountNumber;
    private int pincode;

    public PendingTransaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public int getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(int clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
