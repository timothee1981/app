package royalstacks.app.model.pos;

import org.springframework.beans.factory.annotation.Autowired;
import royalstacks.app.model.repository.AccountRepository;
import royalstacks.app.model.repository.ConnectionRequestRepository;

import java.util.Optional;

public class ConnectionResult {

    final static long ID_WHEN_SUCCEEDED_IS_FALSE = 0;
    final static boolean SUCCEEDED_IS_FALSE = false;
    final static boolean SUCCEEDED_IS_TRUE = true;

    private boolean succeeded;
    private long id;

    @Autowired
    private ConnectionRequestRepository connectionRequestRepository;


    //CONSTRUCTORS
    public ConnectionResult(boolean succeeded, long id) {
        this.succeeded = succeeded;
        this.id = id;
    }

    public ConnectionResult() {
    }
/*
    public void failedConnection(){
        setSucceeded(SUCCEEDED_IS_FALSE);
        setId(ID_WHEN_SUCCEEDED_IS_FALSE);
    }


    public void succeededConnection() {
        setSucceeded(SUCCEEDED_IS_TRUE);
        setId(generate8DigitId());
    }

    private long generate8DigitId() {
        //todo: implement
        final long INITIAL_ID = 00000000;
        long lastId;

        if(retrieveLastId() == 0){
            lastId = INITIAL_ID;
        }
        else {
            lastId = retrieveLastId();
        }
        long newId = lastId+1;;
        return  newId;
    }

    private long retrieveLastId() {
        Optional<Long> lastId = connectionRequestRepository.getLastId();
        if (lastId.isPresent()) {
            return lastId.get();
        } else {
            return 0;
        }
    }*/


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
