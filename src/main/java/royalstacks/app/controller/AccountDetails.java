package royalstacks.app.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountDetailsBackingBean;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.Customer;
import royalstacks.app.model.PrivateAccount;
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
    public ModelAndView accountDetailsHandler(Model model,  @SessionAttribute("userid") int userId) {

        ModelAndView mav = new ModelAndView("/accountdetails");
        String accountNummer =  "NL31ROYA0000001201";
        Optional<Account> account = accountService.getAccountByAccountNumber(accountNummer);
        Account myAccount = null;
        if(account.isPresent())
            myAccount = account.get();
        Set<Customer> accountholders =  myAccount.getAccountHolders();





        //TODO: get transactions corresponding to this account from nosql DB en show only ten last transactio
        mav.addObject("account",myAccount);
        mav.addObject("AccountHolderlist",accountholders);

        return mav;


    }



    private String getAccountType (Account account){
        String accountType = null;
        if (account instanceof BusinessAccount)
            return accountType = "Business Account";
        else
            return accountType = "Private Account";
        }



}

