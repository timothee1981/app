package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import java.util.*;

@Controller
public class AccountOverviewController implements GetCustomer {
    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;


    public AccountOverviewController() {super();
    }

    @GetMapping("/openaccount")
    public ModelAndView openAccountHandler(){

        ModelAndView mav = new ModelAndView("openaccount");
        OpenAccountBackingBean bb = new OpenAccountBackingBean();
        mav.addObject("account",bb);

        return mav;
    }




    @GetMapping("/accountOverview")
    public void setupAccountOverview(Model model, @SessionAttribute("userid") int userId){

        Customer customer =(Customer) userService.findByUserId(userId);
        Iterator<Account> accounts = customer.getAccount().iterator();
        List<Account> myAccounts = new ArrayList<>();
        while(accounts.hasNext()){
            myAccounts.add(accounts.next());
        }
        Collections.sort(myAccounts);
        model.addAttribute("customer",customer);
        model.addAttribute("list",myAccounts);
    }




    @Override
    public Customer getCustomerByUserId(int userId) {
        return (Customer) userService.findByUserId(userId);
    }
}
