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
import royalstacks.app.backingBean.AccountDetailsBackingBean;
import royalstacks.app.model.*;
import royalstacks.app.service.*;

import javax.swing.text.html.Option;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountDetailsBackingBean.class)
class AccountDetailsControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;


/*    @Test
    void accountDetailsHandler() throws Exception{
        //ARRANGE

        Customer customer = new Customer();
        Transaction transaction = new Transaction();

        customer.setUserid(1);
        Account account = new PrivateAccount();
        String accountNumber =  "NL10ROYA0000100027";
        account.setAccountNumber(accountNumber);
        account.setAccountId(1);
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        customer.setAccount(accounts);



        given(accountService.getAccountByAccountNumber(accountNumber)).willReturn(Optional.of(account));
        given(userService.findByUserId(customer.getUserid())).willReturn(customer);



        mvc.perform(get("/accountdetails")
                .sessionAttr("userid", customer.getUserid())
                .param("accountNumber", accountNumber)
        ).andExpect(status().isOk());


    }*/

    @Test
    void goToTransactionHandler() {


    }
}