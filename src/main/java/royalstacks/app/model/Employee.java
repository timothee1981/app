package royalstacks.app.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Employee extends User {

    private String position;

     // CONSTRUCTORS
    public Employee(int userid, String name, String username, String password, String position) {
        super(userid, name, username, password);
        this.position = position;
    }

    public Employee(String name, String username, String password, String position) {
        super(name, username, password);
        this.position = position;
    }

    public Employee() { }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
