package royalstacks.app.model.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import royalstacks.app.model.repository.PosRepository;
import royalstacks.app.service.PosService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class ConnectionResult {

    final static long ID_WHEN_SUCCEEDED_IS_FALSE = 0;
    final static boolean SUCCEEDED_IS_FALSE = false;
    final static boolean SUCCEEDED_IS_TRUE = true;

    @Id
    @GeneratedValue
    private long id;
    private boolean succeeded;

    //CONSTRUCTORS
    public ConnectionResult(boolean succeeded, long id) {
        this.succeeded = succeeded;
        this.id = id;
    }

    public ConnectionResult() {
    }



    //GETTERS AND SETTERS

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
