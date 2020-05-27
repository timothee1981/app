package royalstacks.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessAccountTest {

    @Test
    void isCompanyNameFormatValid() {
        BusinessAccount account = new BusinessAccount();
        account.setCompanyName("google");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("11");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("1");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("/<>//");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("the one");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("   ");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("google_go");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google-go");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("Google-go");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("@google");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google&co+");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google co-");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google@co ");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google co @");
        assertTrue(account.isCompanyNameFormatValid());






    }

    @Test
    void isKvkNameFormatValid() {

        BusinessAccount account = new BusinessAccount();
        //true: kvk-format (8 numbers)
        account.setKvkNumber("12345678");
        assertTrue(account.isKvkNameFormatValid());

        // false: < 8 numbers
        account.setKvkNumber("1234567");
        assertFalse(account.isKvkNameFormatValid());

        // false: > 8 numbers
        account.setKvkNumber("123456789");
        assertFalse(account.isKvkNameFormatValid());

        // false: empty string
        account.setKvkNumber("");
        assertFalse(account.isKvkNameFormatValid());

        // false: 9 chars of which 1 space
        account.setKvkNumber("1234 5678");
        assertFalse(account.isKvkNameFormatValid());

        // false: 9 chars of which 1 special char
        account.setKvkNumber("12345678.");
        assertFalse(account.isKvkNameFormatValid());


    }
}