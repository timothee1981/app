package royalstacks.app.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Test
    void isUsernameFormatValid() {
        assertTrue(userService.isUsernameFormatValid("PietHein"));
        assertTrue(userService.isUsernameFormatValid("Piet"));
        assertTrue(userService.isUsernameFormatValid("KlaasJan88"));
        assertTrue(userService.isUsernameFormatValid("12345678901234567890"));
        assertTrue(userService.isUsernameFormatValid("Aart"));

        assertFalse(userService.isUsernameFormatValid(" PietHein"));
        assertFalse(userService.isUsernameFormatValid("A"));
        assertFalse(userService.isUsernameFormatValid("Ap"));
        assertFalse(userService.isUsernameFormatValid("123456789012345678901"));
        assertFalse(userService.isUsernameFormatValid("PietJan!"));
        assertFalse(userService.isUsernameFormatValid("!KlaasJan"));

    }

    @Test
    void isPasswordValid() {
        assertTrue(userService.isPasswordValid("aAbditDoti!2"));
        assertTrue(userService.isPasswordValid("!gggggggG2"));
        assertTrue(userService.isPasswordValid("!gggggggG2123456789012"));
        assertTrue(userService.isPasswordValid("123KlaasJan123!"));

        assertFalse(userService.isPasswordValid("123Abcde!"));
        assertFalse(userService.isPasswordValid("abcefgij123!"));
        assertFalse(userService.isPasswordValid("JAJAJAJAJA155&^"));
        assertFalse(userService.isPasswordValid("JAJAJAJAJA15"));
    }

    @Test
    void isNameValid() {
        assertTrue(userService.isNameValid("Klaas-Jan"));
        assertTrue(userService.isNameValid("Klaas Jan"));
        assertTrue(userService.isNameValid("KlaasJan"));
        assertTrue(userService.isNameValid("Klaas"));
        assertTrue(userService.isNameValid("'t Veld"));

        assertFalse(userService.isNameValid("Klaas99"));
        assertFalse(userService.isNameValid("Klaas!!"));
        assertFalse(userService.isNameValid(" Klaas"));
        assertFalse(userService.isNameValid("Klaas?"));
        assertFalse(userService.isNameValid("1"));
    }
}