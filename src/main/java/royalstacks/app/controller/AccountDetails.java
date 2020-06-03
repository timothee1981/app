package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Account;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.Customer;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.CustomerService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class AccountDetails {
    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    public AccountDetails() {
    }

    @GetMapping("/accountdetails")
    public ModelAndView accountDetailsHandler(){


        //TODO:get account from DB with account ID
        Account account = accountService.getAccountById(354);   //for now dummy account
        //get accounttype so it can be shown which account which accounttype
        String accountType = getAccountType(account);
        //TODO: get current Date and Time


        Iterator<Customer> customerIterator = account.getAccountHolders().iterator();
        List<Customer> accountholders = new ArrayList<>();
        while(customerIterator.hasNext()){
            accountholders.add(customerIterator.next());
        }
        //each accountholder should be shown in html

       //TODO: get transactions corresponding to this account from nosql DB en show only ten last transaction

        ModelAndView mav = new ModelAndView("/accountdetails");
        mav.addObject("accountType",accountType);
        mav.addObject("account",account);
        mav.addObject("list",accountholders);

        return mav;
    }

    private String getAccountType(Account account) {
        String accountType;
        if(account instanceof BusinessAccount)
           return accountType =  "Business Account";
        else
           return accountType = "Private Account";
    }

    //TODO works, now has to be tested that it actually give what we want


    @GetMapping("transaction")
    public ModelAndView goToTransactionHandler(){
        ModelAndView mav = new ModelAndView("transaction");
        mav.addObject("account");
        return mav;

    }
}
