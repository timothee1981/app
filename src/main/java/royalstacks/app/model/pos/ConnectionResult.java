package royalstacks.app.model.pos;

import royalstacks.app.model.BusinessAccount;

public class ConnectionResult {

    final static long ID_WHEN_SUCCEEDED_IS_FALSE = 0;
    final static boolean SUCCEEDED_IS_FALSE = false;
    final static boolean SUCCEEDED_IS_TRUE = true;

    private boolean succeeded;
    private long id;

    //CONSTRUCTORS
    public ConnectionResult(boolean succeeded, long id) {
        this.succeeded = succeeded;
        this.id = id;
    }

    public ConnectionResult() {
    }

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
        return 87655678;
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
