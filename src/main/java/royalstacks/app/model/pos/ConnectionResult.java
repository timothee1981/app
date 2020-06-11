package royalstacks.app.model.pos;

import royalstacks.app.model.BusinessAccount;

public class ConnectionResult {


    private boolean succeeded;
    private long id;

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
