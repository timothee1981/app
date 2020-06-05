package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountHolderInviteBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.BusinessAccount;
import royalstacks.app.model.Customer;
import royalstacks.app.model.User;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class AddAccountHolderController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;


    @GetMapping("/addaccountholder")
    public ModelAndView addAccountHolderHandler(@SessionAttribute("userid") int userId,
                                                @RequestParam(value = "accountNumber", required = false) String accountNumber) {
        // http://localhost/addaccountholder?accountNumber=NL32ROYA0000000019
        // System.out.println(accountNumber);
        ModelAndView mav = new ModelAndView("/addaccountholder");

        Optional<Account> OptionalAccount = accountService.getAccountByAccountNumber(accountNumber);
        if (OptionalAccount.isPresent()) {
            Account anAccount = OptionalAccount.get();
            mav.addObject("accountNumber", anAccount.getAccountNumber());
        }
        return mav;
    }


}
