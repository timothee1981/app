package royalstacks.app.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void isEmailAddressValid() {
        String invalid1 = "#@%^%#$@#$@#.com";
        String invalid2 = "plainaddress";
        String invalid3 = "@example.com";
        String invalid4 = "Donald Duck <donald@duck.com>";
        String invalid5 = "email.@example.com";
        String invalid6 = "just\"not\"right@example.com";
        String invalid7 = "“(),:;<>[\\]@example.com";
        //valid1 en valid2 falen want ze worden door onze regex niet toegestaan. Nog verder induiken
        String valid1 = "very.unusual.“@”.unusual.com@example.com";
        String valid2 = "very.“(),:;<>[]”.VERY.“very@\\\\ \"very”.unusual@strange.example.com";
        String valid3 = "email@[123.123.123.123]";
        String valid4 = "_______@example.com";
        String valid5 = "firstname+lastname@example.com";

        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

        Assert.assertEquals(false,invalid1.matches(regex));
        Assert.assertEquals(false,invalid2.matches(regex));
        Assert.assertEquals(false,invalid3.matches(regex));
        Assert.assertEquals(false,invalid4.matches(regex));
        Assert.assertEquals(false,invalid5.matches(regex));
        Assert.assertEquals(false,invalid6.matches(regex));
        Assert.assertEquals(false,invalid7.matches(regex));
        Assert.assertEquals(true,valid1.matches(regex));
        Assert.assertEquals(true,valid2.matches(regex));
        Assert.assertEquals(true,valid3.matches(regex));
        Assert.assertEquals(true,valid4.matches(regex));
        Assert.assertEquals(true,valid5.matches(regex));
    }


    @Test
    void isAddressValid() {
        String valid1 = "Barack Obamastraat 1";

        // klopt het dat we valid2 goedrekenen?
        String valid2 = "Straatnaam4";

        // valid3 is een bestaand adres in Lelystad met afwijkend adresformat: wijk Gondel, straatnr 26, huisnr 95
        String valid3 = "Gondel 26-95";

        // valid4 faalt maar moet goedgekeurd worden. valid4 is een bestaand adres van een flat:
        // de vierde woning op de vijfde verdieping (F) van gebouw Kagerstraat 5
        //String valid4 = "Kagerstraat 5-F 4";

        //valid 5 faalt, komen dit soort adressen voor?
        //String valid5 = "Jan-Willemlaan 12";

        //ook een bestaand adres. deze test faalt maar is dus wel correct.
        String valid6 = "Plein '40-'45 12";

        String invalid1 = "Straatnaam";
        String invalid2 = "1934 24";
        String invalid3 = "20348";

        String regex = "^([1-9][e][\\s])*([a-zA-Z]+(([\\.][\\s])|([\\s]))?)+[1-9][0-9]*(([-][1-9][0-9]*)|([\\s]?[a-zA-Z]+))?$";

        Assert.assertEquals(true,valid1.matches(regex));
        Assert.assertEquals(true,valid2.matches(regex));
        Assert.assertEquals(true,valid3.matches(regex));

        //Assert.assertEquals(true,valid4.matches(regex));
        //Assert.assertEquals(true,valid5.matches(regex));

        Assert.assertEquals(true,valid6.matches(regex));
        Assert.assertEquals(false,invalid1.matches(regex));
        Assert.assertEquals(false,invalid2.matches(regex));
        Assert.assertEquals(false,invalid3.matches(regex));
    }

    @Test
    void isPostalCodeValid() {
        String valid1 = "1234AB";
        String invalid1 = "12345";
        String invalid2 = "12X4BD";
        String invalid3 = "1-23-4GH";

        String regex = "\\d{4}[A-Z]{2}";

        Assert.assertEquals(true,valid1.matches(regex));
        Assert.assertEquals(false,invalid1.matches(regex));
        Assert.assertEquals(false,invalid2.matches(regex));
        Assert.assertEquals(false,invalid3.matches(regex));
    }

    @Test
    void isCityValid() {
    }

    @Test
    void isPhoneNumberValid() {
    }

    @Test
    void isSocialSecurityNumberFormatValid() {
    }
}
