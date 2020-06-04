package royalstacks.app.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import royalstacks.app.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Test
    void isEmailValid() {

        Assert.assertTrue(customerService.isEmailValid("fiepbakker@hotmail.com"));
        Assert.assertTrue(customerService.isEmailValid("email@[123.123.123.123]"));
        Assert.assertTrue(customerService.isEmailValid("_______@example.com"));
        Assert.assertTrue(customerService.isEmailValid("firstname+lastname@example.com"));
        Assert.assertTrue(customerService.isEmailValid("  example@example.com  "));
        Assert.assertTrue(customerService.isEmailValid("fiepbakker@hotmail.com"));
        Assert.assertTrue(customerService.isEmailValid("fiepbakker@hotmail.com"));

        Assert.assertFalse(customerService.isEmailValid("#@%^%#$@#$@#.com"));
        Assert.assertFalse(customerService.isEmailValid("plainaddress"));
        Assert.assertFalse(customerService.isEmailValid("@example.com"));
        Assert.assertFalse(customerService.isEmailValid("#Donald Duck <donald@duck.com>"));
        Assert.assertFalse(customerService.isEmailValid("just\"not\"right@example.com"));
    }


    @Test
    void isHouseNumberValid() {

        Assert.assertTrue(customerService.isHouseNumberValid("   1  "));
        Assert.assertTrue(customerService.isHouseNumberValid(" 1"));
        Assert.assertTrue(customerService.isHouseNumberValid(" 100"));
        Assert.assertTrue(customerService.isHouseNumberValid("26-95"));
        Assert.assertTrue(customerService.isHouseNumberValid("   4"));
        Assert.assertTrue(customerService.isHouseNumberValid(" 100"));


        Assert.assertFalse(customerService.isHouseNumberValid(""));
        Assert.assertFalse(customerService.isHouseNumberValid("1934 24"));
        Assert.assertFalse(customerService.isHouseNumberValid("1933434"));
    }

    @Test
    void isPostalCodeValid() {

        // valid postal codes

        Assert.assertTrue(customerService.isPostalCodeValid("1234 AB"));
        Assert.assertTrue(customerService.isPostalCodeValid("1234AB"));
        Assert.assertTrue(customerService.isPostalCodeValid("1234ab"));

        // invalid postal codes
        Assert.assertFalse(customerService.isPostalCodeValid("1234"));
        Assert.assertFalse(customerService.isPostalCodeValid("1a23 b4"));
        Assert.assertFalse(customerService.isPostalCodeValid("12345"));
        Assert.assertFalse(customerService.isPostalCodeValid("1234-AB"));
        Assert.assertFalse(customerService.isPostalCodeValid("0123 AB"));
        Assert.assertFalse(customerService.isPostalCodeValid("1234 SS"));
        Assert.assertFalse(customerService.isPostalCodeValid("1234 SA"));
        Assert.assertFalse(customerService.isPostalCodeValid("1234 SD"));
    }

    @Test
    void isCityValid() {

        // valid cities
        Assert.assertTrue(customerService.isCityValid("Amsterdam"));
        Assert.assertTrue(customerService.isCityValid("amsterdam"));
        Assert.assertTrue(customerService.isCityValid("  Maastricht  "));

        // invalid cities
        Assert.assertFalse(customerService.isCityValid("Hoorn0"));
        Assert.assertFalse(customerService.isCityValid(".amsterdam"));

    }

    @Test
    void isPhoneNumberValid() {


        Assert.assertTrue(customerService.isPhoneNumberValid("0612345678"));
        Assert.assertTrue(customerService.isPhoneNumberValid("0756357878"));
        Assert.assertTrue(customerService.isPhoneNumberValid("075-6357878"));

        Assert.assertFalse(customerService.isPhoneNumberValid("075.6357878"));
        Assert.assertFalse(customerService.isPhoneNumberValid("7563578782"));
        Assert.assertFalse(customerService.isPhoneNumberValid("02756357878"));
        Assert.assertFalse(customerService.isPhoneNumberValid("027563578@"));

    }

    @Test
    void isBSNFormatValid() {

        Assert.assertTrue(customerService.isBSNFormatValid("302110501"));
        Assert.assertTrue(customerService.isBSNFormatValid("265478224"));
        Assert.assertTrue(customerService.isBSNFormatValid("429922760"));
        Assert.assertTrue(customerService.isBSNFormatValid("083753321"));
        Assert.assertTrue(customerService.isBSNFormatValid("043003357"));
        Assert.assertTrue(customerService.isBSNFormatValid("039527554"));
        Assert.assertTrue(customerService.isBSNFormatValid("110871042"));
        Assert.assertTrue(customerService.isBSNFormatValid("373736459"));
        Assert.assertTrue(customerService.isBSNFormatValid("352480695"));
        Assert.assertTrue(customerService.isBSNFormatValid("672270225"));

        Assert.assertFalse(customerService.isBSNFormatValid("01234567"));
        Assert.assertFalse(customerService.isBSNFormatValid("123456789"));
        Assert.assertFalse(customerService.isBSNFormatValid("012-34567"));
        Assert.assertFalse(customerService.isBSNFormatValid("01234567A"));
    }

}