package royalstacks.app.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.Account;
import royalstacks.app.model.PrivateAccount;
import royalstacks.app.service.AccountService;

@Controller
public class AccountDetails {
    AccountService accountService;

    public AccountDetails() {
    }

    @GetMapping("/accountdetails")
    public ModelAndView accountDetailsHandler(){
        //Account object, take it from the database
        Account account = new PrivateAccount();

        //list from account holders
        //if account obj is private then give to a field accounttype: private, else accountype business

        ModelAndView mav = new ModelAndView("/accountdetails");
        mav.addObject("account",account);
        return mav;
    }
}
