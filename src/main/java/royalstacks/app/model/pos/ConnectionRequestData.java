package royalstacks.app.model.pos;

public class ConnectionRequestData {

    private String account;

    private int code;

    public ConnectionRequestData(String account, int code) {
        this.account = account;
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

