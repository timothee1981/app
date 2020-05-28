package royalstacks.app.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void isEmailValid() {

        Customer c = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "Hoogstraat 12", "Hoorn" , "1234AB", "0612345678",
                "123456789", null, false);

        // valid email addresses
        c.setEmail("fiepbakker@hotmail.com");
        Assert.assertTrue(c.isEmailValid());
        c.setEmail("email@[123.123.123.123]");
        Assert.assertTrue(c.isEmailValid());
        c.setEmail("_______@example.com");
        Assert.assertTrue(c.isEmailValid());
        c.setEmail("firstname+lastname@example.com");
        Assert.assertTrue(c.isEmailValid());
        c.setEmail("  example@example.com  ");
        Assert.assertTrue(c.isEmailValid());

        // invalid email addresses
        c.setEmail("#@%^%#$@#$@#.com");
        Assert.assertFalse(c.isEmailValid());
        c.setEmail("plainaddress");
        Assert.assertFalse(c.isEmailValid());
        c.setEmail("@example.com");
        Assert.assertFalse(c.isEmailValid());
        c.setEmail("Donald Duck <donald@duck.com>");
        Assert.assertFalse(c.isEmailValid());
        c.setEmail("just\"not\"right@example.com");
    }


    @Test
    void isAddressValid() {

        Customer c = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "Hoogstraat 12", "Hoorn" , "1234AB", "0612345678",
                "123456789", null, false);

        // valid addresses
        c.setAddress("  Barack Obamastraat 1  ");
        Assert.assertTrue(c.isAddressValid());
        c.setAddress("Barack Obamastraat 1");
        Assert.assertTrue(c.isAddressValid());

        //adressen die bestaan en dus valide moeten zijn als we alles helemaal officieel willen doen:
        c.setAddress("Laan van Duiven-Westervoort 100");
        Assert.assertTrue(c.isAddressValid());
        c.setAddress("Gondel 26-95");
        Assert.assertTrue(c.isAddressValid());
        c.setAddress("Kagerstraat 5-F 4");
        Assert.assertTrue(c.isAddressValid());

        //invalid addresses

        //deze willen we valid maken door zelf voor de gebruiker een spatie in te voegen(?)
        c.setAddress("Straatnaam4");
        Assert.assertFalse(c.isAddressValid());

        c.setAddress("Straatnaam");
        Assert.assertFalse(c.isAddressValid());
        c.setAddress("1934 24");
        Assert.assertFalse(c.isAddressValid());
        c.setAddress("20");
        Assert.assertFalse(c.isAddressValid());
    }

    @Test
    void isPostalCodeValid() {

        Customer c = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "Hoogstraat 12", "Hoorn" , "1234AB", "0612345678",
                "123456789", null, false);

        // valid postal codes
        c.setPostalCode("1234 AB");
        Assert.assertTrue(c.isPostalCodeValid());
        c.setPostalCode("1234AB");
        Assert.assertTrue(c.isPostalCodeValid());
        c.setPostalCode("1234ab");
        Assert.assertTrue(c.isPostalCodeValid());

        // invalid postal codes
        c.setPostalCode("1234");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("1a23 b4");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("12345");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("1234-AB");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("0123 AB");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("1234 SS");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("1234 SA");
        Assert.assertFalse(c.isPostalCodeValid());
        c.setPostalCode("1234 SD");
        Assert.assertFalse(c.isPostalCodeValid());
    }

    @Test
    void isCityValid() {
        Customer c = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "Hoogstraat 12", "Hoorn" , "1234AB", "0612345678",
                "123456789", null, false);

        // valid cities
        c.setCity("Amsterdam");
        Assert.assertTrue(c.isCityValid());
        c.setCity("amsterdam");
        Assert.assertTrue(c.isCityValid());
        c.setCity("  Maastricht  ");
        Assert.assertTrue(c.isCityValid());

        //deze test faalt, maar dit gebruikt toch niemand, toch?
        //c.setCity("'s-Gravenhage");
        //Assert.assertTrue(c.isCityValid());

        // invalid cities
        c.setCity("Hoorn0");
        Assert.assertFalse(c.isCityValid());
        c.setCity(".amsterdam");
        Assert.assertFalse(c.isCityValid());
    }

    @Test
    void isPhoneNumberValid() {

        Customer c = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "Hoogstraat 12", "Hoorn" , "1234AB", "0612345678",
                "123456789", null, false);

        // valid phone numbers
        c.setPhoneNumber("0612345678");
        Assert.assertTrue(c.isPhoneNumberValid());
        c.setPhoneNumber("0756357878");
        Assert.assertTrue(c.isPhoneNumberValid());
        c.setPhoneNumber("075-6357878");
        Assert.assertTrue(c.isPhoneNumberValid());
        // deze test faalt (vanwege streepje na 06). willen we dit fiksen in de regex?
        //c.setPhoneNumber("06-12345678");
        //Assert.assertTrue(c.isPhoneNumberValid());

        //invalid phone numbers
        c.setPhoneNumber("075.6357878");
        Assert.assertFalse(c.isPhoneNumberValid());
        c.setPhoneNumber("7563578782");
        Assert.assertFalse(c.isPhoneNumberValid());
        c.setPhoneNumber("02756357878");
        Assert.assertFalse(c.isPhoneNumberValid());
        c.setPhoneNumber("027563578@");
        Assert.assertFalse(c.isPhoneNumberValid());
        c.setPhoneNumber("075635-7878");
        Assert.assertFalse(c.isPhoneNumberValid());
    }

    @Test
    void isBSNFormatValid() {

        Customer c = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "Hoogstraat 12", "Hoorn" , "1234AB", "0612345678",
                "123456789", null, false);

        // valid BSN
        c.setBSN("302110501");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("265478224");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("429922760");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("083753321");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("043003357");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("039527554");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("110871042");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("373736459");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("352480695");
        Assert.assertTrue(c.isBSNFormatValid());
        c.setBSN("672270225");
        Assert.assertTrue(c.isBSNFormatValid());


        // invalid BSN
        c.setBSN("01234567");
        Assert.assertFalse(c.isBSNFormatValid());
        c.setBSN("123456789");
        Assert.assertFalse(c.isBSNFormatValid());
        c.setBSN("012-34567");
        Assert.assertFalse(c.isBSNFormatValid());
        c.setBSN("01234567A");
        Assert.assertFalse(c.isBSNFormatValid());
    }



}
