package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class AddAccountHolderController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;


    @GetMapping("/addaccountholder")
    public ModelAndView addAccountHolderHandler(@RequestParam(value = "accountNumber", required = false) String accountNumber,
                                                @SessionAttribute("userid") int userId, Model model) {

        ModelAndView mav = new ModelAndView("addaccountholder");

        Account anAccount = null;
        Optional<Account> account = accountService.getAccountByAccountNumber(accountNumber);
        if (account.isPresent()) {
            anAccount = account.get();
            mav.addObject("accountNumber", anAccount.getAccountNumber());
        }
        return mav;
    }


}
