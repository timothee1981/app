package royalstacks.app.model;

import org.springframework.security.crypto.bcrypt.BCrypt;
import royalstacks.app.service.UserService;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    private final static int DEFAULT_USER_ID = 0;
    private static int passwordWorkload = 12;

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
        this.firstName = firstName;
        this.lastName = lastName;
        setPassword(password);
    }

    // userid komt vanuit database
    public User(String username, String password, String firstName, String lastName) {
        this(DEFAULT_USER_ID, username, password, firstName, lastName);
    }

    public User() { }

    // METHODS
    public boolean isUsernameFormatValid(){
        // usernameField mag kleine letters, grote letters, getallen, en - of _ bevatten en moet tussen 3 en 20 characters lang zijn.
        return this.username.matches("^[a-zA-Z0-9_-]{3,15}$");
    }

    public static boolean isPasswordValid(String inputPassword){
        // Moet 1 kleine letter, 1 grote letter, 1 nummer, 1 speciaal karakter en minstens 10 karakters lang zijn
        return inputPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#$%&'()*+,\\-./:;<=>?@^_`{|}~\\[\\]])[A-Za-z\\d!\"#$%&'()*+,\\-./:;<=>?@^_`{|}~\\[\\]]{10,100}$");
    }

    public boolean isFirstNameValid(){
        this.firstName = this.firstName.trim();
        return this.firstName.matches("^[^\\s].*[a-zA-Z-'\\s?][^.]{1,100}");
    }

    public boolean isLastNameValid(){
        this.lastName = this.lastName.trim();
        return this.lastName.matches("^[^\\s].*[a-zA-Z-'\\s?][^.]{1,100}");
    }



    /**
     * This method can be used to generate a string representing an account passwordField
     * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
     * hash string of length=60
     * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
     * A workload of 12 is a very reasonable safe default as of 2013.
     * This automatically handles secure 128-bit salt generation and storage within the hash.
     * @param password_plaintext The account's plaintext passwordField as provided during account creation,
     *			     or when changing an account's passwordField.
     * @return String - a string of length 60 that is the bcrypt hashed passwordField in crypt(3) format.
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(passwordWorkload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g. during a login
     * request) with that of a stored hash from a database. The passwordField hash from the database
     * must be passed as the second variable.
     * @param password_plaintext The account's plaintext passwordField, as provided during a login request
     * @param stored_hash The account's stored passwordField hash, retrieved from the authorization database
     * @return boolean - true if the passwordField matches the passwordField of the stored hash, false otherwise
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
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

        String hashedPassword = User.hashPassword(password_plaintext);

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userid == user.userid &&
                Objects.equals(username, user.username) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", usernameField='" + username + '\'' +
                ", passwordField='" + password + '\'' +
                ", firstNameField='" + firstName + '\'' +
                ", lastNameField='" + lastName + '\'' +
                '}';
    }
}
