package royalstacks.app.model.pos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ConnectionRequest {

    @Id
    @GeneratedValue
    protected int id;
    private String businessAccountIban;
    private int connectionCode;

    public ConnectionRequest(String businessAccountIban, int connectionCode) {
        this.businessAccountIban = businessAccountIban;
        this.connectionCode = connectionCode;
    }

    public ConnectionRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessAccountIban() {
        return businessAccountIban;
    }

    public void setBusinessAccountIban(String businessAccountIban) {
        this.businessAccountIban = businessAccountIban;
    }

    public int getConnectionCode() {
        return connectionCode;
    }

    public void setConnectionCode(int validationCode) {
        this.connectionCode = validationCode;
    }

    @Override
    public String toString() {
        return "ConnectionRequest{" +
                "id=" + id +
                ", businessAccountIban='" + businessAccountIban + '\'' +
                ", connectionCode=" + connectionCode +
                '}';
    }
}
