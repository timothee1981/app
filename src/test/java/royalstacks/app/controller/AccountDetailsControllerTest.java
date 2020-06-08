package royalstacks.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;
import royalstacks.app.model.User;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.LogInService;
import royalstacks.app.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountDetailsController.class)
class AccountDetailsControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;


    @Test
    void accountDetailsHandler() throws Exception{

        AccountDetailsController accountDetailsController =  new AccountDetailsController();
        String username = "";
        String password = "password";
        User user = new Customer();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserid(1);
        //given(service.findById(user.getUserid()));



        ModelAndView result = accountDetailsController.accountDetailsHandler(1,"NL");

        String expected = "accountdetails";

        assertEquals(expected,result.getViewName());
    }

    @Test
    void goToTransactionHandler() {


    }
}