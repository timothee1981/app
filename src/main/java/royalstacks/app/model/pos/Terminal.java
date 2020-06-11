package royalstacks.app.model.pos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Terminal {

    @Id
    @GeneratedValue
    protected int id;
    @OneToOne
    private Pos pos;

    public Terminal(Pos pos) {
        this.pos = pos;
    }

    public Terminal() {
    }
}
