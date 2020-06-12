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
    private String businessAccountNumber;
    private int identificationNumber;
    @OneToOne
    private Terminal terminal;

    // CONSTRUCTORS
    public Pos(String businessAccountNumber, int identificationNumber, Terminal terminal) {
        this.businessAccountNumber = businessAccountNumber;
        this.identificationNumber = identificationNumber;
        this.terminal = terminal;
    }

    public Pos() {
    }


    // GETTERS & SETTERS


    public int getId() {
        return id;
    }


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
                ", businessAccountNumber=" + businessAccountNumber +
                ", identificationNumber=" + identificationNumber +
                ", posTerminal=" + terminal +
                '}';
    }
}
