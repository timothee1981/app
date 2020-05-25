package royalstacks.app.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    private final static int DEFAULT_USER_ID = 0;

    @Id
    @GeneratedValue
    protected int userid;
    protected String name;
    protected String username;
    protected String password;

    // CONTRUCTORS
    public User(int userid, String name, String username, String inputpassword) {
        this.userid = userid;
        this.name = name;
        this.username = username;
        this.password = Password.hashPassword(inputpassword);
    }

    // userid komt vanuit database
    public User(String name, String username, String inputpassword) {
        this(DEFAULT_USER_ID, name, username, inputpassword);
    }

    public User() { }

    // METHODS
    public boolean isUsernameFormatValid(){
        // username mag kleine letters, grote letters, getallen, en - of _ bevatten en moet tussen 3 en 20 characters lang zijn.
        return this.username.matches("^[a-zA-Z0-9_-]{3,15}$");
    }

    public boolean isUsernameUnique(){
        // TODO: body isUsernameUnique
        return true;
    }

    public static boolean isPasswordFormatValid(String password){
        // Moet 1 kleine letter, 1 grote letter, 1 nummer, 1 speciaal karakter en minstens 8 karakters lang zijn
        return password.matches("(?=(.*[0-9]))(?=.*[\\!@#$%^&*()\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}");
    }

    public boolean isNameValid(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password_plaintext) {

        String hashedPassword = Password.hashPassword(password_plaintext);

        this.password = hashedPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
