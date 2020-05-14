package royalstacks.app.model;

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
