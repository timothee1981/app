package royalstacks.app.model;

import royalstacks.app.service.UserService;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    private final static int DEFAULT_USER_ID = 0;

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
        this.password = Password.hashPassword(password);
    }

    // userid komt vanuit database
    public User(String username, String password, String firstName, String lastName) {
        this(DEFAULT_USER_ID, username, password, firstName, lastName);
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

    public static boolean isPasswordValid(String inputPassword){
        // Moet 1 kleine letter, 1 grote letter, 1 nummer, 1 speciaal karakter en minstens 8 karakters lang zijn
        return inputPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$");
    }

    public boolean isFirstNameValid(){
        // Alleen letters, - en tussen 1 en 100 karakters lang
        return this.firstName.matches("[a-zA-Z-'\\s?]{1,100}");
    }

    public boolean isLastNameValid(){
        // Alleen letters, - en tussen 1 en 100 karakters lang
        return this.lastName.matches("[a-zA-Z-'\\s?]{1,100}");
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

    public void setPassword(String password_plaintext) {

        String hashedPassword = Password.hashPassword(password_plaintext);

        this.password = hashedPassword;
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
