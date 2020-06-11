package royalstacks.app.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pos {

    @Id
    @GeneratedValue
    protected int id;
    @OneToOne
    private BusinessAccount businessAccount;
    private int identificationNumber;
    @OneToOne
    private PosTerminal posTerminal;

    // CONSTRUCTORS
    public Pos(BusinessAccount businessAccount, int identificationNumber, PosTerminal posTerminal) {
        this.businessAccount = businessAccount;
        this.identificationNumber = identificationNumber;
        this.posTerminal = posTerminal;
    }

    public Pos() {
    }


    // GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BusinessAccount getBusinessAccount() {
        return businessAccount;
    }

    public void setBusinessAccount(BusinessAccount businessAccount) {
        this.businessAccount = businessAccount;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public PosTerminal getPosTerminal() {
        return posTerminal;
    }

    public void setPosTerminal(PosTerminal posTerminal) {
        this.posTerminal = posTerminal;
    }


    // METHODS
    @Override
    public String toString() {
        return "Pos{" +
                "id=" + id +
                ", businessAccount=" + businessAccount +
                ", identificationNumber=" + identificationNumber +
                ", posTerminal=" + posTerminal +
                '}';
    }
}
