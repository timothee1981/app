package royalstacks.app.model;

import royalstacks.app.service.UserService;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    protected int userid;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;

    // CONSTRUCTORS
    public User(int userid, String username, String password, String firstName, String lastName) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // userid komt vanuit database
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() { }

    // METHODS
    public boolean isUsernameFormatValid(){
        // username mag kleine letters, grote letters, getallen, en - of _ bevatten en moet tussen 3 en 20 characters lang zijn.
        return this.username.matches("^[a-zA-Z0-9_-]{3,15}$");
    }

    public boolean isUsernameUnique(){
        UserService userService = new UserService();

        // TODO geeft hier NPE, niet als userService in Controller gebruikt wordt
        try{
            return userService.findByUsername(this.username).isEmpty();
        } catch(NullPointerException e) {
            System.out.println(e);
            return true;
        }

    }

    public boolean isPasswordValid(){
        // Moet 1 kleine letter, 1 grote letter, 1 nummer, 1 speciaal karakter en minstens 8 karakters lang zijn
        return this.password.matches("(?=(.*[0-9]))(?=.*[\\!@#$%^&*()\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}");
    }

    public boolean isFirstNameValid(){
        // TODO: is name valid. Voornaam Achternaam?
        return true;
    }

    public boolean isLastNameValid(){
        // TODO: is name valid. Voornaam Achternaam?
        return true;
    }

    // GETTERS SETTERS
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
