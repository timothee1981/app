package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class MyAccounts {
    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;


    public MyAccounts() {super();
    }

    @GetMapping("/openaccount")
    public ModelAndView openAccountHandler(){

        ModelAndView mav = new ModelAndView("openaccount");
        OpenAccountBackingBean bb = new OpenAccountBackingBean();
        mav.addObject("account",bb);

        return mav;
    }





    @GetMapping("/myaccounts")
    public void setupMyAccounts(Model model, @SessionAttribute("userid") int userId){

        Customer customer =(Customer) userService.findByUserId(userId);
        List<Account> accounts = accountService.getAllAccounts();
        List<Account> myAccounts = new ArrayList<>();

        for(Account account: accounts){
            if(account.getAccountHolders().iterator().next().getUserid() == userId){
                myAccounts.add(account);
            }
        }
        model.addAttribute("customer",customer);
        model.addAttribute("list",myAccounts);


    }
}
