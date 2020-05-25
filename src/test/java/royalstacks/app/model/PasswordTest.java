package royalstacks.app.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void checkCorrectPassword() {
        String test_passwd = "abcdefghijklmnopqrstuvwxyz";

        String computed_hash = Password.hashPassword(test_passwd);

        Assert.assertTrue(Password.checkPassword(test_passwd, computed_hash));
    }

    @Test
    void checkFalsePassword() {
        String test_passwd = "abcdefghijklmnopqrstuvwxyz";
        String test_hash = "$2a$06$.rCVZVOThsIa97pEXOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC";

        String computed_hash = Password.hashPassword(test_passwd);

        Assert.assertFalse(Password.checkPassword(test_passwd, test_hash));

    }

}