package royalstacks.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.*;
import royalstacks.app.service.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountDetailsController.class)
class AccountDetailsControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;


    @Test
    void accountDetailsHandler() throws Exception{
        //ARRANGE

        int userId = 1;
        Customer customer = new Customer(1,null, null, "-", "sdsd", null,"3513", "sds",
               " S", "city", null,
               null,null, false);

        customer.setPassword("Jsdsdsd798+");
        customer.setUsername("lklkeeeee");
        String accountNumber = "NL32ROYA0000000019";

        Account account = new PrivateAccount(null,0);
        account.setAccountNumber(accountNumber);
        customer.getAccount().add(account);
        given(userService.findByUserId(userId)).willReturn(customer);
        given(accountService.getAccountByAccountNumber(accountNumber)).willReturn(java.util.Optional.of(account));

        //ACT AND ASSERT

        mvc.perform(
                get("accountdetails").
                        sessionAttr("userId",userId).
                        param("account", accountNumber)
        ).andExpect(status().isOk())
                .andExpect(view().name("accountdetails"));



    }

    @Test
    void goToTransactionHandler() {


    }
}