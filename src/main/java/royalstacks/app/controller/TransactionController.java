package royalstacks.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.backingBean.CustomerBackingBean;
import royalstacks.app.backingBean.TransactionBackingBean;
import royalstacks.app.model.Transaction;
import royalstacks.app.service.AccountService;

@Controller
public class TransactionController {


    @GetMapping("/transaction")
    public ModelAndView signUpHandler(){
        ModelAndView mav = new ModelAndView("transaction");
        return mav;
    }

    @PostMapping("/transaction")
    public ModelAndView signUpHandler(@ModelAttribute TransactionBackingBean tbb){
        tbb.
        AccountService as = new AccountService();
        tbb.getFromAccount();
        Transaction t = tbb.Transaction();
        System.out.println(t);
        ModelAndView mav = new ModelAndView("transaction");
        return mav;
    }
}
