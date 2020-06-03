package royalstacks.app.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountDetailsBackingBean;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.*;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.CustomerService;
import royalstacks.app.service.UserService;

import java.util.*;

@Controller
public class AccountDetails {
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    public AccountDetails() {
    }

    @GetMapping("/accountdetails")
    public ModelAndView accountDetailsHandler( @SessionAttribute("userid") int userId, @RequestParam(value = "accountNumber",required = false)String accountNumber) {

        ModelAndView mav = new ModelAndView("/accountdetails");
        Customer customer = (Customer) userService.findByUserId(userId);
        Iterator<Account> accounts = customer.getAccount().iterator();
        List<Account> myAccounts = new ArrayList<>();
        while(accounts.hasNext()){
            myAccounts.add(accounts.next());
        }


        //TODO: get transactions corresponding to this account from nosql DB en show only ten last transactio
        mav.addObject("accounts",myAccounts);

        return mav;


    }

    //maak een post mappingkk






    private String getAccountType (Account account){
        String accountType = null;
        if (account instanceof BusinessAccount)
            return accountType = "Business Account";
        else
            return accountType = "Private Account";
        }



}

