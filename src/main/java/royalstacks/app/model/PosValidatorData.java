package royalstacks.app.model;

public class PosValidatorData {

    private String account;

    private int code;

    public PosValidatorData(String account, int code) {
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

