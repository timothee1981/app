package royalstacks.app.model.pos;


import royalstacks.app.model.BusinessAccount;

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
    private Terminal terminal;

    // CONSTRUCTORS
    public Pos(BusinessAccount businessAccount, int identificationNumber, Terminal terminal) {
        this.businessAccount = businessAccount;
        this.identificationNumber = identificationNumber;
        this.terminal = terminal;
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

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }


    // METHODS
    @Override
    public String toString() {
        return "Pos{" +
                "id=" + id +
                ", businessAccount=" + businessAccount +
                ", identificationNumber=" + identificationNumber +
                ", posTerminal=" + terminal +
                '}';
    }
}
