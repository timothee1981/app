package royalstacks.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PosTerminal {

    @Id
    @GeneratedValue
    protected int id;
    @OneToOne
    private Pos pos;

    public PosTerminal(Pos pos) {
        this.pos = pos;
    }

    public PosTerminal() {
    }
}
