package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import royalstacks.app.backingBean.OpenAccountBackingBean;
import royalstacks.app.model.Account;
import royalstacks.app.model.Customer;
import royalstacks.app.model.User;
import royalstacks.app.service.AccountService;
import royalstacks.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MyAccountsController {
    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;


    public MyAccountsController() {super();
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
        Iterator<Account> accounts = customer.getAccount().iterator();
        List<Account> myAccounts = new ArrayList<>();
        while(accounts.hasNext()){
            myAccounts.add(accounts.next());
        }
        model.addAttribute("customer",customer);
        model.addAttribute("list",myAccounts);
    }




}
