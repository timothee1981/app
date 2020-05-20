package royalstacks.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class User {

    @Id
    @GeneratedValue
    protected int userid;
    protected String name;
    protected String username;
    protected String password;

    // CONTRUCTORS
    public User(int userid, String name, String username, String password) {
        this.userid = userid;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // userid komt vanuit database
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User() { }

    // METHODS
    public static boolean isUsernameValid(String username){
        return isUsernameFormatValid(username)
                && isUsernameUnique(username);
    }

    private static boolean isUsernameFormatValid(String username){
        // TODO: body isUsernameFormatValid
        return true;
    }

    private static boolean isUsernameUnique(String username){
        // TODO: body isUsernameUnique
        return true;
    }

    public static boolean isPasswordValid(String password){
        // TODO: body isPasswordValid
        return true;
    }

    public static boolean isNameValid(String name){
        // TODO: body isNameValid
        return true;
    }

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

    public void setPassword(String password) {
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
