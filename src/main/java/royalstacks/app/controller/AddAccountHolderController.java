package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.AccountHolderInviteBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.User;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

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

    @PostMapping("/addaccountholder")
    public ModelAndView addAccountHolderHandler(@ModelAttribute AccountHolderInviteBackingBean ibb,
                                                @SessionAttribute("userid") int userId,
                                                Model model) {
        ModelAndView mav = new ModelAndView("addaccountholder");

        //haal user op uit DB met behulp van backing bean
        Optional<User> OptionalUser = userService.findByUsername(ibb.getInviteeUsername());
        //check of user bestaat én een employee is (geen customer)


        if (userIsPresent && userIsCustomer && ibb.getVerificationCode().matches("\\d{5}")){

        }
    }

/*    public boolean isVerificationCodeValid(){
        this.verificationCode = this.verificationCode.trim();
        return this.verificationCode.matches("\\d{5}");
    }*/


}
