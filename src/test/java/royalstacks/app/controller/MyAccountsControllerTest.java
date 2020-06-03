package royalstacks.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class MyAccountsControllerTest {



    @Test
    void openAccountHandler() {
        //ARRANGE
        MyAccountsController myAccountsController = new MyAccountsController();

        //ACT
        ModelAndView result = myAccountsController.openAccountHandler();


        //ASSERT
        String expected = "openaccount";
        assertEquals(expected , result.getViewName());

    }

    @Test
    void setupMyAccounts() {



    }
}