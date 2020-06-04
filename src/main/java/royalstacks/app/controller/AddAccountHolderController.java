package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Account;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.List;

@Controller
public class AddAccountHolderController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;


/*    @GetMapping("/addaccountholder")
    public ModelAndView addAccountHolderHandler(Model model, @SessionAttribute("userid") int userId){
        ModelAndView mav = new ModelAndView("addaccountholder");

        List<Account> myAccounts = getAccountsFromUserId(userId);

        model.addAttribute("account", myAccounts);
        return mav;
    }*/


}
