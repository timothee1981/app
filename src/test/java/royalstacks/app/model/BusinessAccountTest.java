package royalstacks.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessAccountTest {

    @Test
    void isCompanyNameFormatValid() {
        BusinessAccount account = new BusinessAccount();

        // true: a-z
        account.setCompanyName("google");
        assertTrue(account.isCompanyNameFormatValid());

        // true: A-Z
        account.setCompanyName("GOOGLE");
        assertTrue(account.isCompanyNameFormatValid());

        // false: <1 char
        account.setCompanyName("");
        assertFalse(account.isCompanyNameFormatValid());

        // true: a-z string char
        account.setCompanyName("a");
        assertTrue(account.isCompanyNameFormatValid());

        // true: A-Z string char
        account.setCompanyName("A");
        assertTrue(account.isCompanyNameFormatValid());

        // true: number char
        account.setCompanyName("1");
        assertTrue(account.isCompanyNameFormatValid());

        // false: not allowed special char
        account.setCompanyName("*");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("<");
        assertFalse(account.isCompanyNameFormatValid());

        // false: a-z and not allowed special char
        account.setCompanyName("a*");
        assertFalse(account.isCompanyNameFormatValid());

        // false: only allowed special chars(s)
        account.setCompanyName("_");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("@");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("____");
        assertFalse(account.isCompanyNameFormatValid());

        // false: a-z and not allowed special char in between
        account.setCompanyName("google*co ");
        assertFalse(account.isCompanyNameFormatValid());

        // false: only special char and spaces
        account.setCompanyName(" _");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName("_ ");
        assertFalse(account.isCompanyNameFormatValid());
        account.setCompanyName(" _ ");
        assertFalse(account.isCompanyNameFormatValid());

        // false: only spaces
        account.setCompanyName("   ");
        assertFalse(account.isCompanyNameFormatValid());

        // true: a-z and spaces
        account.setCompanyName("the one");
        assertTrue(account.isCompanyNameFormatValid());

        // true: a-z and allowed special char in between
        account.setCompanyName("g_g");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google_go");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google-go");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("Google-go");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google@co ");
        assertTrue(account.isCompanyNameFormatValid());

        // true: a-z and allowed special char at end
        account.setCompanyName("g_");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("g-");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("g@");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("g&");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("g+");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google co-");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("google co @");
        assertTrue(account.isCompanyNameFormatValid());


        // true: a-z and allowed special char at beginning
        account.setCompanyName("_g");
        assertTrue(account.isCompanyNameFormatValid());
        account.setCompanyName("@google");
        assertTrue(account.isCompanyNameFormatValid());



    }

    @Test
    void isKvkNameFormatValid() {

        BusinessAccount account = new BusinessAccount();
        //true: = 8 numbers
        account.setKvkNumber("12345678");
        assertTrue(account.isKvkNameFormatValid());

        // false: < 8 numbers
        account.setKvkNumber("1234567");
        assertFalse(account.isKvkNameFormatValid());

        // false: > 8 numbers
        account.setKvkNumber("123456789");
        assertFalse(account.isKvkNameFormatValid());

        // false: 8 a-z chars
        account.setKvkNumber("abcdefgh");
        assertFalse(account.isKvkNameFormatValid());

        // false: 8 spaces
        account.setKvkNumber("        ");
        assertFalse(account.isKvkNameFormatValid());

        // false: 8 special chars
        account.setKvkNumber("********");
        assertFalse(account.isKvkNameFormatValid());

        // false: empty string
        account.setKvkNumber("");
        assertFalse(account.isKvkNameFormatValid());

        // false: 8 chars of which 1 space
        account.setKvkNumber("1234 678");
        assertFalse(account.isKvkNameFormatValid());

        // false: >8 chars of which 1 space
        account.setKvkNumber("1234 5678");
        assertFalse(account.isKvkNameFormatValid());

        // false: 9 chars of which 1 special char
        account.setKvkNumber("12345678.");
        assertFalse(account.isKvkNameFormatValid());

    }
}